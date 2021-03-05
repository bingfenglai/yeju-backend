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
package pers.lbf.yeju.provider.dict.type.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.domain.entity.DataDictionaryType;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.dict.type.dao.IDataDictionaryTypeDao;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryTypeService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryTypeBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 数据字典类型服务类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/16 17:06
 */
@DubboService(interfaceClass = IDataDictionaryTypeService.class)
public class DataDictionaryTypeServiceImpl implements IDataDictionaryTypeService {

    @DubboReference
    private IDataDictionaryInfoService dataDictionaryInfoService;

    @Autowired
    private IDataDictionaryTypeDao dataDictionaryTypeDao;

    @Cacheable(cacheNames = "DataDictionaryTypeService:type:list", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleDataDictionaryTypeBean> findPage(Long currentPage, Long size) throws ServiceException {
        Page<DataDictionaryType> page = PageUtil.getPage(DataDictionaryType.class, currentPage, size);
        Page<DataDictionaryType> dataDictionaryTypePage = dataDictionaryTypeDao.selectPage(page, null);

        List<DataDictionaryType> dataDictionaryTypeList = dataDictionaryTypePage.getRecords();

        List<SimpleDataDictionaryTypeBean> result = new LinkedList<>();
        IResult<Map<String, String>> dictMapResult = dataDictionaryInfoService.getDictMap("dict_status");
        Map<String, String> map = dictMapResult.getData();
        for (DataDictionaryType type : dataDictionaryTypeList) {
            SimpleDataDictionaryTypeBean bean = typeToSimpleBean(type);
            bean.setStatusStr(map.get(bean.getStatus().toString()));
            result.add(bean);
        }


        return PageResult.ok(dataDictionaryTypePage.getTotal(), currentPage, size, result);
    }

    /**
     * 查询所有可用字典类型的id name label
     *
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<List<SimpleDataDictionaryTypeBean>> findAll() throws ServiceException {
        return null;
    }

    private SimpleDataDictionaryTypeBean typeToSimpleBean(DataDictionaryType type) {
        SimpleDataDictionaryTypeBean simpleDataDictionaryTypeBean = new SimpleDataDictionaryTypeBean();
        simpleDataDictionaryTypeBean.setId(type.getDataDictionaryTypeId());
        simpleDataDictionaryTypeBean.setName(type.getName());
        simpleDataDictionaryTypeBean.setType(type.getType());
        simpleDataDictionaryTypeBean.setStatus(type.getStatus());
        simpleDataDictionaryTypeBean.setCreateTime(type.getCreateTime());
        simpleDataDictionaryTypeBean.setRemark(type.getRemark());
        return simpleDataDictionaryTypeBean;
    }

}
