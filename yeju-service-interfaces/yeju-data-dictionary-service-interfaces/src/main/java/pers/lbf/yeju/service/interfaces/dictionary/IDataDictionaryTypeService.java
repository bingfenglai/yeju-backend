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
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryTypeBean;

import java.util.List;

/** 数据字典类型
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/16 17:03
 */
public interface IDataDictionaryTypeService {

    PageResult<SimpleDataDictionaryTypeBean> findPage(Long currentPage, Long size) throws ServiceException;

    /** TODO
     * 查询所有可用字典类型的id name label
     * @return
     * @throws ServiceException
     */
    IResult<List<SimpleDataDictionaryTypeBean>> findAll() throws ServiceException;

}
