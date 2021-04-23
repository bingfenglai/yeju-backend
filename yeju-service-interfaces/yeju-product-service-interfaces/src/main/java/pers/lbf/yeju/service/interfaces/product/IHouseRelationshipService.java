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

package pers.lbf.yeju.service.interfaces.product;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseRelationshipCreateArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseRelationshipInfoBean;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseRelationshipQueryArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseRelationshipUpdateArgs;

/**
 * 房源-客户关系服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/23 8:22
 */
public interface IHouseRelationshipService {

    IResult<HouseRelationshipInfoBean> getRelationshipHouseCustomerByCustomerId(Long customerId) throws ServiceException;

    IResult<HouseRelationshipInfoBean> getRelationshipHouseCustomerByHouseId(Long houseId) throws ServiceException;

    PageResult<HouseRelationshipInfoBean> queryPage(HouseRelationshipQueryArgs args) throws ServiceException;

    IResult<Boolean> addRelationship(HouseRelationshipCreateArgs args) throws ServiceException;

    IResult<Boolean> updateRelationshipById(HouseRelationshipUpdateArgs args) throws ServiceException;

    IResult<Boolean> removeRelationshipById(Long id) throws ServiceException;

    /**
     * 获取与房源对应关系的客户标识
     *
     * @param houseId          房源标识
     * @param relationshipType 关系类型
     * @return customerId
     * @throws ServiceException
     */
    IResult<Long> getCustomerIdByHouseIdAndRelationshipType(Long houseId, String relationshipType) throws ServiceException;

}
