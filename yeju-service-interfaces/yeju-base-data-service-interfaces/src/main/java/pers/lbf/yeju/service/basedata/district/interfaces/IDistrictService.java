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

package pers.lbf.yeju.service.basedata.district.interfaces;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.basedata.district.pojo.*;

import java.util.List;

/**
 * 城市服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/17 19:53
 */
public interface IDistrictService {

    
    /**
     * 根据上一级id获取地域选项列表
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    IResult<List<DistrictNameAndIdVO>> getDistrictNameAndIdListByParentId(Long id) throws ServiceException;

    /**
     * 根据id查找对应的name 并返回name 、id
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    IResult<DistrictNameAndIdVO> getDistrictNameAndIdListById(Long id) throws ServiceException;


    IResult<Boolean> create(DistrictCreateArgs args) throws ServiceException;

    IResult<Boolean> update(DistrictUpdateArgs args) throws ServiceException;


    IResult<String> findCityNameByCityId(Long cityId) throws ServiceException;

    PageResult<SimpleDistrictInfoBean> query(DistrictQueryArgs args) throws ServiceException;

    /**
     * 查询全国省市 以结果树返回
     *
     * @param args
     * @return tree
     * @throws ServiceException
     */
    IResult<List<SimpleDistrictInfoBean>> queryTree(DistrictQueryArgs args) throws ServiceException;

    /**
     * 查询树 无参
     *
     * @return
     * @throws ServiceException
     */
    IResult<List<SimpleDistrictInfoBean>> queryTree() throws ServiceException;

    /**
     * 根据上一级地域id查找其直属地域
     *
     * @param parentId
     * @return city list
     * @throws ServiceException
     */
    IResult<List<SimpleDistrictInfoBean>> findDirectDistrictById(Long parentId) throws ServiceException;


}
