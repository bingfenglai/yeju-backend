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
package pers.lbf.yeju.provider.basedata.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.data.Community;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.basedata.community.dao.ICommunityDao;
import pers.lbf.yeju.service.basedata.community.interfaces.ICommunityService;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityCreateArgs;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityQueryArgs;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityUpdateArgs;
import pers.lbf.yeju.service.basedata.community.pojo.SimpleCommunityInfoBean;
import pers.lbf.yeju.service.basedata.district.interfaces.IDistrictService;
import pers.lbf.yeju.service.basedata.district.pojo.SimpleAddressInfoBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 社区服务实现类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/19 21:40
 */
@DubboService(interfaceClass = ICommunityService.class, timeout = 10000, retries = 0)
@Slf4j
public class CommunityServiceImpl implements ICommunityService {

    @Autowired
    private ICommunityDao communityDao;

    @DubboReference
    private IDistrictService districtService;

    /**
     * 综合分页查询接口
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @Cacheable(cacheNames = "community:page", key = "#args")
    public PageResult<SimpleCommunityInfoBean> query(CommunityQueryArgs args) throws ServiceException {
        log.info("page {} size {}", args.getCurrentPage(), args.getSize());
        Page<Community> page = PageUtil.getPage(Community.class, args.getCurrentPage(), args.getSize());
        QueryWrapper<Community> queryWrapper = queryWrapperBuild(args);
        Page<Community> communityPage = communityDao.selectPage(page, queryWrapper);
        log.info("total {}", communityPage.getTotal());
        List<SimpleCommunityInfoBean> result = new LinkedList<>();
        for (Community community : communityPage.getRecords()) {
            SimpleCommunityInfoBean bean = communityToSimpleInfoBean(community);

            result.add(bean);
        }
        return PageResult.ok(communityPage.getTotal(), communityPage.getCurrent(), communityPage.getSize(), result);
    }


    /**
     * 添加一个社区
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @CacheEvict(cacheNames = "community:page", allEntries = true)
    public IResult<Boolean> create(CommunityCreateArgs args) throws ServiceException {
        Community community = createArgsToCommunity(args);
        int count = communityDao.insert(community);
        return Result.ok(count == 1);
    }

    private Community createArgsToCommunity(CommunityCreateArgs args) {
        Community community = new Community();
        community.setName(args.getName());
        community.setAreaId(args.getAreaId());
        community.setDetailedAddress(args.getDetailedAddress());
        community.setCreateTime(args.getCreateTime());
        community.setCreateBy(args.getCreateBy());
        community.setRemark(args.getRemark());
        return community;
    }

    /**
     * 批量创建
     *
     * @param argsList
     * @return
     * @throws ServiceException
     */
    @Override
    @CacheEvict(cacheNames = "community:page", allEntries = true)
    public IResult<Boolean> createBatch(List<CommunityCreateArgs> argsList) throws ServiceException {
        int i = 0;
        for (CommunityCreateArgs communityCreateArgs : argsList) {
            Community community = createArgsToCommunity(communityCreateArgs);
            communityDao.insert(community);
            i += 1;
        }
        return Result.ok(i == argsList.size());
    }

    /**
     * 更新指定Id的社区信息
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @CacheEvict(cacheNames = "community:page", allEntries = true)
    public IResult<Boolean> updateById(CommunityUpdateArgs args) throws ServiceException {
        Community community = createArgsToCommunity(args);
        community.setCommunityId(args.getCommunityId());
        community.setChangedBy(args.getChangedBy());
        community.setUpdateTime(args.getUpdateTime());
        int i = communityDao.updateById(community);

        return Result.ok(i == 1);
    }

    /**
     * 移除指定id社区
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    @CacheEvict(cacheNames = "community:page", allEntries = true)
    public IResult<Boolean> removeById(Long id) throws ServiceException {
        communityDao.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @throws ServiceException
     */
    @Override
    @CacheEvict(cacheNames = "community:page", allEntries = true)
    public IResult<Boolean> removeBatch(Set<Long> ids) throws ServiceException {
        communityDao.deleteBatchIds(ids);
        return Result.success();
    }

    /**
     * 根据小区id查找详细地址
     *
     * @param communityId
     * @throws ServiceException
     */
    @Override
    public IResult<String> findDetailsAddressById(Long communityId) throws ServiceException {
        String address = communityDao.findDetailsAddressById(communityId);
        return Result.ok(address);
    }


    private SimpleCommunityInfoBean communityToSimpleInfoBean(Community community) {
        SimpleCommunityInfoBean simpleCommunityInfoBean = new SimpleCommunityInfoBean();

        simpleCommunityInfoBean.setRemark(community.getRemark());
        simpleCommunityInfoBean.setCommunityId(community.getCommunityId());
        simpleCommunityInfoBean.setName(community.getName());
        simpleCommunityInfoBean.setDetailedAddress(community.getDetailedAddress());
        simpleCommunityInfoBean.setCreateTime(community.getCreateTime());

        SimpleAddressInfoBean addressInfo = new SimpleAddressInfoBean();
        addressInfo.setAreaId(community.getAreaId());
        addressInfo.setCityId(community.getCityId());
        addressInfo.setProvinceId(community.getProvinceId());
        if (community.getAreaId() != null) {
            addressInfo.setAreaName(districtService.findCityNameByCityId(community.getAreaId()).getData());
        }
        if (community.getCityId() != null) {
            addressInfo.setCityName(districtService.findCityNameByCityId(community.getCityId()).getData());

        }
        if (community.getProvinceId() != null) {
            addressInfo.setProvinceName(districtService.findCityNameByCityId(community.getProvinceId()).getData());
        }
        simpleCommunityInfoBean.setAddressInfo(addressInfo);

        return simpleCommunityInfoBean;

    }

    private QueryWrapper<Community> queryWrapperBuild(CommunityQueryArgs args) {
        QueryWrapper<Community> queryWrapper = new QueryWrapper<>();
        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getDetailedAddress())) {
            queryWrapper.like("detailed_address", args.getDetailedAddress());
        }

        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getName())) {
            queryWrapper.like("name", args.getName());

        }

        if (YejuStringUtils.isNotEmpty(args.getBetween()) && args.getBetween().length == 2) {
            String[] between = args.getBetween();
            queryWrapper.between("create_time", between[0], between[1]);
        }

        return queryWrapper;
    }
}