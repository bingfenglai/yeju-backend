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
package pers.lbf.yeju.provider.dict.info.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.domain.entity.DataDictionaryInfo;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.dict.info.dao.IDataDictionaryInfoDao;
import pers.lbf.yeju.provider.dict.type.dao.IDataDictionaryTypeDao;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.DictQueryArgs;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryInfoBean;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/13 15:00
 */
@DubboService(interfaceClass = IDataDictionaryInfoService.class)
@Slf4j
public class DataDictionaryInfoServiceImpl implements IDataDictionaryInfoService {

    @Autowired
    private IDataDictionaryInfoDao dataDictionaryInfoDao;

    @Autowired
    private IDataDictionaryTypeDao dictionaryTypeDao;

    /**
     * 通过类型名称查找对应的值
     *
     * @param type 类型名 如gender
     * @return list
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/18 12:48
     */
    @Override
    @Cacheable(cacheNames = "dataDictionaryInfoService:type:list", key = "#type")
    public IResult<List<SimpleLabelAndValueBean>> findLabelAndValueByType(String type) throws ServiceException {
        LinkedList<SimpleLabelAndValueBean> result = new LinkedList<>();
        if (type == null || "".equals(type)) {
            return Result.ok(result);
        }

        result = dataDictionaryInfoDao.selectLabelAndValueByType(type);

        return Result.ok(result);
    }

    @Override
    @Cacheable(cacheNames = "dataDictionaryInfoService:type:map", key = "#type")
    public IResult<Map<String, String>> getDictMap(String type) throws ServiceException {
        Map<String, String> map = new HashMap<>(2);
        IResult<List<SimpleDataDictionaryInfoBean>> result =
                this.findSimpleDataDictionaryByDictType(type);

        List<SimpleDataDictionaryInfoBean> data = result.getData();

        if (data.size() > 0) {
            for (SimpleDataDictionaryInfoBean bean : data) {
                map.put(bean.getDictionaryValue(), bean.getDictionaryLabel());
            }
        }

        return Result.ok(map);
    }

    @Override
    @Cacheable(cacheNames = "dataDictionaryInfoService:id:info", key = "#id")
    public IResult<String> findDataDictionaryLabelById(Long id) throws ServiceException {
        DataDictionaryInfo dataDictionaryInfo = dataDictionaryInfoDao.selectById(id);
        if (dataDictionaryInfo == null) {
            throw ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);
        }
        return Result.ok(dataDictionaryInfo.getDictionaryLabel());
    }

    @Override
    @Cacheable(cacheNames = "dataDictionaryInfoService:id:type", key = "#id")
    public IResult<List<SimpleDataDictionaryInfoBean>> findSimpleDataDictionaryByDictTypeId(Long id) throws ServiceException {
        QueryWrapper<DataDictionaryInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", id);

        List<DataDictionaryInfo> dataDictionaryInfoList = dataDictionaryInfoDao.selectList(queryWrapper);

        if (dataDictionaryInfoList.size() > 0) {
            List<SimpleDataDictionaryInfoBean> result = new LinkedList<>();
            for (DataDictionaryInfo dataDictionaryInfo : dataDictionaryInfoList) {
                SimpleDataDictionaryInfoBean bean = new SimpleDataDictionaryInfoBean();
                bean.setDictionaryLabel(dataDictionaryInfo.getDictionaryLabel());
                bean.setDictionaryValue(dataDictionaryInfo.getDictionaryValue());
                bean.setCssClass(dataDictionaryInfo.getCssClass());
                bean.setListClass(dataDictionaryInfo.getListClass());
                bean.setDefaultFlags(dataDictionaryInfo.getIsDefault() == 1);
                bean.setStatus(dataDictionaryInfo.getStatus());
                result.add(bean);
            }

            return Result.ok(result);
        }
        throw ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);

    }

    @Cacheable(cacheNames = "dataDictionaryInfoService:list:infoBean", key = "#type")
    @Override
    public IResult<List<SimpleDataDictionaryInfoBean>> findSimpleDataDictionaryByDictType(String type) throws ServiceException {
        if (type != null) {

            Long typeId = dictionaryTypeDao.selectOneByType(type);
            log.debug("type_id {}", typeId);

            return this.findSimpleDataDictionaryByDictTypeId(typeId);
        } else {
            throw ServiceException.getInstance("type不能为空", ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

    }

    @Cacheable(cacheNames = "dataDictionaryInfoService:list:infoBean", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleDataDictionaryInfoBean> findPage(Long currentPage, Long size) throws ServiceException {
        Page<DataDictionaryInfo> page = PageUtil.getPage(DataDictionaryInfo.class, currentPage, size);
        Page<DataDictionaryInfo> dataDictionaryInfoPage = dataDictionaryInfoDao.selectPage(page, null);

        List<DataDictionaryInfo> dataDictionaryInfoList = dataDictionaryInfoPage.getRecords();

        List<SimpleDataDictionaryInfoBean> result = new LinkedList<>();

        for (DataDictionaryInfo info : dataDictionaryInfoList) {
            SimpleDataDictionaryInfoBean bean = infoToSimpleBean(info);
            result.add(bean);
        }


        return PageResult.ok(dataDictionaryInfoPage.getTotal(), currentPage, size, result);
    }

    @Override
    @Cacheable(cacheNames = "dataDictionaryInfoService:list:infoBean", keyGenerator = "yejuKeyGenerator")
    public PageResult<SimpleDataDictionaryInfoBean> findPage(DictQueryArgs args) {
        Page<DataDictionaryInfo> page = PageUtil.
                getPage(DataDictionaryInfo.class, args.getCurrentPage(), args.getSize());

        QueryWrapper<DataDictionaryInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", args.getDataTypeId());

        List<SimpleDataDictionaryInfoBean> result = new LinkedList<>();

        Page<DataDictionaryInfo> dataDictionaryInfoPage = dataDictionaryInfoDao.selectPage(page, queryWrapper);
        for (DataDictionaryInfo info : dataDictionaryInfoPage.getRecords()) {
            SimpleDataDictionaryInfoBean bean = this.infoToSimpleBean(info);
            result.add(bean);
        }
        return PageResult.ok(dataDictionaryInfoPage.getTotal(),
                dataDictionaryInfoPage.getCurrent(), dataDictionaryInfoPage.getSize(), result);
    }


    private SimpleDataDictionaryInfoBean infoToSimpleBean(DataDictionaryInfo info) {
        SimpleDataDictionaryInfoBean bean = new SimpleDataDictionaryInfoBean();
        bean.setId(info.getDataDictionaryInfoId());
        bean.setDictionaryLabel(info.getDictionaryLabel());
        bean.setDictionaryValue(info.getDictionaryValue());
        bean.setCssClass(info.getCssClass());
        bean.setListClass(info.getListClass());
        bean.setDefaultFlags(info.getIsDefault() == 1);
        bean.setStatus(info.getStatus());
        bean.setCreateTime(info.getCreateTime());
        bean.setRemark(info.getRemark());
        bean.setSort(info.getSort());

        return bean;
    }
}
