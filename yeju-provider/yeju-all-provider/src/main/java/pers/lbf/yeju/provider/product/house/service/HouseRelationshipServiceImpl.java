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
package pers.lbf.yeju.provider.product.house.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.provider.product.house.dao.IRelationshipCustomerHouseDao;
import pers.lbf.yeju.service.interfaces.product.IHouseRelationshipService;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseRelationshipCreateArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseRelationshipInfoBean;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseRelationshipQueryArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseRelationshipUpdateArgs;

/**
 * 客户-房屋关系实现
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/23 8:33
 */
@DubboService(interfaceClass = IHouseRelationshipService.class, timeout = 10000, retries = 0)
@Slf4j
public class HouseRelationshipServiceImpl implements IHouseRelationshipService {

    @Autowired
    private IRelationshipCustomerHouseDao dao;

    @Override
    public IResult<HouseRelationshipInfoBean> getRelationshipHouseCustomerByCustomerId(Long customerId) throws ServiceException {
        return null;
    }

    @Override
    public IResult<HouseRelationshipInfoBean> getRelationshipHouseCustomerByHouseId(Long houseId) throws ServiceException {
        return null;
    }

    @Override
    public PageResult<HouseRelationshipInfoBean> queryPage(HouseRelationshipQueryArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> addRelationship(HouseRelationshipCreateArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> updateRelationshipById(HouseRelationshipUpdateArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> removeRelationshipById(Long id) throws ServiceException {
        int i = dao.deleteById(id);
        return Result.ok(i == 1);
    }

    /**
     * 获取与房源对应关系的客户标识
     *
     * @param houseId          房源标识
     * @param relationshipType 关系类型
     * @return customerId
     * @throws ServiceException e
     */
    @Override
    @Cacheable(cacheNames = "customer:id:hr")
    public IResult<Long> getCustomerIdByHouseIdAndRelationshipType(Long houseId, String relationshipType) throws ServiceException {
        Long landlordId = dao.getCustomerIdByHouseIdAndRelationshipType(houseId, relationshipType);
        return Result.ok(landlordId);
    }
}