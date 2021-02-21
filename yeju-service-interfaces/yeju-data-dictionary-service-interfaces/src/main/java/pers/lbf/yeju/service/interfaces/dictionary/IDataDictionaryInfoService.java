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
package pers.lbf.yeju.service.interfaces.dictionary;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.DictQueryArgs;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryInfoBean;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;

import java.util.List;
import java.util.Map;

/**数据字典服务接口
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/13 14:54
 */
public interface IDataDictionaryInfoService {

    /** 通过类型名称查找对应的值
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/18 12:48
     * @param type 类型名 如gender
     * @return  list
     */
    IResult<List<SimpleLabelAndValueBean>> findLabelAndValueByType(String type) throws ServiceException;

    IResult<Map<String,String>>  getDictMap(String type) throws ServiceException;

    IResult<String> findDataDictionaryLabelById(Long id) throws ServiceException;

    IResult<List<SimpleDataDictionaryInfoBean>> findSimpleDataDictionaryByDictTypeId(Long id) throws ServiceException;

    IResult<List<SimpleDataDictionaryInfoBean>> findSimpleDataDictionaryByDictType(String type) throws ServiceException;

    PageResult<SimpleDataDictionaryInfoBean> findPage(Long currentPage, Long size) throws ServiceException;

    PageResult<SimpleDataDictionaryInfoBean> findPage(DictQueryArgs args);
}
