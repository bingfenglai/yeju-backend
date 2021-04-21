/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package pers.lbf.yeju.provider.basedata.district.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.data.District;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.basedata.district.dao.IDistrictDao;
import pers.lbf.yeju.provider.basedata.district.status.DistrictStatusEnum;
import pers.lbf.yeju.service.basedata.district.interfaces.IDistrictService;
import pers.lbf.yeju.service.basedata.district.pojo.*;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/17 20:06
 */
@DubboService(interfaceClass = IDistrictService.class, timeout = 10000, retries = 0)
@Slf4j
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private IDistrictDao districtDao;


    /**
     * 根据上一级id获取地域选项列表
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    @Cacheable(cacheNames = "district:options", key = "#id")
    public IResult<List<DistrictNameAndIdVO>> getDistrictNameAndIdListByParentId(Long id) throws ServiceException {
        List<DistrictNameAndIdVO> result = districtDao.findDistrictNameAndIdListByParentId(id);
        return Result.ok(result);
    }

    /**
     * 根据id查找对应的name 并返回name 、id
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    @Cacheable(cacheNames = "district:options:id", key = "#id")
    public IResult<DistrictNameAndIdVO> getDistrictNameAndIdListById(Long id) throws ServiceException {
        DistrictNameAndIdVO vo = districtDao.findDistrictNameAndIdListById(id);

        return Result.ok(vo);
    }

    @Override
    @CacheEvict(cacheNames = {
            "district:tree",
            "district:page",
            "district:tree:all",
            "district:directChildNode",
            "district:options"}, allEntries = true)
    public IResult<Boolean> create(DistrictCreateArgs args) throws ServiceException {
        District district = districtCreateArgsToDistrict(args);
        int insert = districtDao.insert(district);
        return Result.ok(insert == 1);
    }

    @Override
    @CacheEvict(cacheNames = {
            "district:tree",
            "district:page",
            "district:tree:all",
            "district:name",
            "district:directChildNode",
            "district:options",
            "district:options:id"}, allEntries = true)
    public IResult<Boolean> update(DistrictUpdateArgs args) throws ServiceException {
        District district = districtCreateArgsToDistrict(args);
        district.setChangedBy(args.getChangedBy());
        district.setUpdateTime(args.getUpdateTime());
        district.setDistrictId(args.getDistrictId());
        int i = districtDao.updateById(district);
        return Result.ok(i == 1);
    }


    private District districtCreateArgsToDistrict(DistrictCreateArgs args) {
        District district = new District();
        district.setParentId(args.getParentId());
        district.setName(args.getName());
        district.setType(args.getType());
        district.setHierarchy(args.getHierarchy());
        district.setCreateTime(args.getCreateTime());
        district.setCreateBy(args.getCreateBy());
        district.setRemark(args.getRemark());

        return district;
    }

    @Cacheable(cacheNames = "district:name", key = "#cityId")
    @Override
    public IResult<String> findCityNameByCityId(Long cityId) throws ServiceException {
        String cityName = districtDao.findCityNameByCityId(cityId);
        if (YejuStringUtils.isNotNull(cityName)) {
            return Result.ok(cityName);
        }

        throw ServiceException.getInstance(DistrictStatusEnum.NOT_FOUND);
    }

    @Cacheable(cacheNames = "district:page", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleDistrictInfoBean> query(DistrictQueryArgs args) throws ServiceException {
        Page<District> page = PageUtil.getPage(District.class, args.getCurrentPage(), args.getSize());
        QueryWrapper<District> queryWrapper = queryWrapperBuild(args);

        Page<District> districtPage = districtDao.selectPage(page, queryWrapper);
        List<SimpleDistrictInfoBean> result = new LinkedList<>();
        for (District district : districtPage.getRecords()) {
            SimpleDistrictInfoBean bean = districtToBean(district);
            result.add(bean);
        }

        return PageResult.ok(districtPage.getTotal(), districtPage.getCurrent(), districtPage.getSize(), result);
    }

    /**
     * 查询全国省市 以结果树返回
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @Cacheable(cacheNames = "district:tree", key = "#args")
    public IResult<List<SimpleDistrictInfoBean>> queryTree(DistrictQueryArgs args) throws ServiceException {
        QueryWrapper<District> queryWrapper = this.queryWrapperBuild(args);
        List<District> districts = districtDao.selectList(queryWrapper);

        List<SimpleDistrictInfoBean> allDistricts = new LinkedList<>();
        for (District district : districts) {
            SimpleDistrictInfoBean bean = districtToBean(district);
            List<Long> ids = districtDao.hasChildren(district.getDistrictId());
            bean.setHasChildren(ids.size() == 1);
            allDistricts.add(bean);
        }

        return Result.ok(allDistricts);

    }

    /**
     * 查询树 无参
     *
     * @return
     * @throws ServiceException
     */
    @Override
    @Cacheable(cacheNames = "district:tree:all")
    public IResult<List<SimpleDistrictInfoBean>> queryTree() throws ServiceException {
        return queryTree(new DistrictQueryArgs());
    }


    /**
     * 根据上一级地域id查找其直属地域
     *
     * @param parentId
     * @return city list
     * @throws ServiceException
     */
    @Override
    @Cacheable(value = "district:directChildNode", key = "#parentId")
    public IResult<List<SimpleDistrictInfoBean>> findDirectDistrictById(Long parentId) throws ServiceException {
        List<SimpleDistrictInfoBean> districtList = districtDao.findDirectDistrictById(parentId);

        for (SimpleDistrictInfoBean bean : districtList) {
            bean.setChildrenList(new LinkedList<>());
            List<Long> ids = districtDao.hasChildren(bean.getDistrictId());
            bean.setHasChildren(ids.size() == 1);
        }

        return Result.ok(districtList);
    }


    /**
     * 地域递归构建树结构方法
     *
     * @param parent
     * @param otherDistrict
     * @return
     */
    private SimpleDistrictInfoBean recursiveTree(SimpleDistrictInfoBean parent, List<SimpleDistrictInfoBean> otherDistrict) {
        for (SimpleDistrictInfoBean bean : otherDistrict) {
            if (bean.getParentId().equals(parent.getDistrictId())) {
                recursiveTree(bean, otherDistrict);
                parent.addChildren(bean);
                //多了如下一行

            }

        }

        return parent;
    }


    private SimpleDistrictInfoBean districtToBean(District district) {
        SimpleDistrictInfoBean simpleDistrictInfoBean = new SimpleDistrictInfoBean();
        simpleDistrictInfoBean.setCreateTime(district.getCreateTime());
        simpleDistrictInfoBean.setDistrictId(district.getDistrictId());
        simpleDistrictInfoBean.setParentId(district.getParentId());
        simpleDistrictInfoBean.setName(district.getName());
        simpleDistrictInfoBean.setType(district.getType());
        simpleDistrictInfoBean.setHierarchy(district.getHierarchy());
        simpleDistrictInfoBean.setRemark(district.getRemark());
        return simpleDistrictInfoBean;
    }

    private QueryWrapper<District> queryWrapperBuild(DistrictQueryArgs args) {
        QueryWrapper<District> queryWrapper = new QueryWrapper<>();

        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getName())) {
            queryWrapper.like("name", args.getName());
        }

        if (YejuStringUtils.isNotNull(args.getType())) {
            queryWrapper.eq("type", args.getType());
        }

        if (YejuStringUtils.isNotNull(args.getParentId())) {
            queryWrapper.eq("parent_id", args.getParentId());
        }

        return queryWrapper;
    }
}