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
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.domain.entity.DataDictionaryInfo;
import pers.lbf.yeju.provider.dict.info.dao.IDataDictionaryInfoDao;
import pers.lbf.yeju.provider.dict.type.dao.IDataDictionaryTypeDao;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryInfoBean;

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
@DubboService(interfaceClass = IDataDictionaryService.class)
@Slf4j
public class DataDictionaryServiceImpl implements IDataDictionaryService {

    @Autowired
    private IDataDictionaryInfoDao dataDictionaryInfoDao;

    @Autowired
    private IDataDictionaryTypeDao dictionaryTypeDao;

    @Override
    public IResult<Map<String, String>> getDictMap(String type) throws ServiceException {
        Map<String,String> map = new HashMap<>();
        IResult<List<SimpleDataDictionaryInfoBean>> result =
               this.findSimpleDataDictionaryByDictType(type);

        List<SimpleDataDictionaryInfoBean> data = result.getData();

        if (data.size()>0){
            for (SimpleDataDictionaryInfoBean bean : data) {
                map.put(bean.getDictionaryValue(),bean.getDictionaryLabel());
            }
        }

        return Result.ok(map);
    }

    @Override
    public IResult<String> findDataDictionaryLabelById(Long id) throws ServiceException {
        DataDictionaryInfo dataDictionaryInfo = dataDictionaryInfoDao.selectById(id);
        if (dataDictionaryInfo == null) {
            throw  ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);
        }
        return Result.ok(dataDictionaryInfo.getDictionaryLabel());
    }

    @Override
    public IResult<List<SimpleDataDictionaryInfoBean>> findSimpleDataDictionaryByDictTypeId(Long id) throws ServiceException {
        QueryWrapper<DataDictionaryInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id",id);

        List<DataDictionaryInfo> dataDictionaryInfoList = dataDictionaryInfoDao.selectList(queryWrapper);

        if (dataDictionaryInfoList.size()>0){
            List<SimpleDataDictionaryInfoBean> result = new LinkedList<>();
            for (DataDictionaryInfo dataDictionaryInfo : dataDictionaryInfoList) {
                SimpleDataDictionaryInfoBean bean = new SimpleDataDictionaryInfoBean();
                bean.setDictionaryLabel(dataDictionaryInfo.getDictionaryLabel());
                bean.setDictionaryValue(dataDictionaryInfo.getDictionaryValue());
                bean.setCssClass(dataDictionaryInfo.getCssClass());
                bean.setListClass(dataDictionaryInfo.getListClass());
                bean.setDefaultFlags(dataDictionaryInfo.getIsDefault()==1);
                bean.setStatus(dataDictionaryInfo.getStatus());
                result.add(bean);
            }

            return Result.ok(result);
        }
        throw  ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);

    }

    @Cacheable(cacheNames = "SimpleDataDictionaryInfoBean:list",key = "#type")
    @Override
    public IResult<List<SimpleDataDictionaryInfoBean>> findSimpleDataDictionaryByDictType(String type) throws ServiceException {
        if (type != null){

            if (dictionaryTypeDao==null){
                throw new RuntimeException("dao is null");
            }

            Long typeId = dictionaryTypeDao.selectOneByType(type);
            log.debug("type_id {}",typeId);

            return this.findSimpleDataDictionaryByDictTypeId(typeId);
        }else {
            throw ServiceException.getInstance("type不能为空",ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

    }
}
