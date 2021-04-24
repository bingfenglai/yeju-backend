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
package pers.lbf.yeju.provider.product.collection.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.Collection;
import pers.lbf.yeju.provider.product.collection.constants.CollectionStatusConstants;
import pers.lbf.yeju.provider.product.collection.dao.ICollectionDao;
import pers.lbf.yeju.service.interfaces.product.IProductCollectionService;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseCollectionCreateArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseCollectionUpdateArgs;

/**
 * 房源收藏服务实现
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/24 21:06
 */
@DubboService(interfaceClass = IProductCollectionService.class, timeout = 10000, retries = 0)
@Slf4j
public class ProductCollectionServiceImpl implements IProductCollectionService {

    @Autowired
    private ICollectionDao collectionDao;

    @Override
    public IResult<Boolean> create(HouseCollectionCreateArgs args) throws ServiceException {
        Collection collection = createArgsToCollection(args);
        int i = collectionDao.insert(collection);
        return Result.ok(i == 1);
    }

    private Collection createArgsToCollection(HouseCollectionCreateArgs args) {
        Collection collection = new Collection();
        collection.setCustomerId(args.getCustomerId());
        collection.setHouseId(args.getHouseId());
        collection.setStatus(args.getStatus());
        collection.setCreateTime(args.getCreateTime());
        collection.setCreateBy(args.getCreateBy());
        return collection;
    }

    @Override
    public IResult<Boolean> updateById(HouseCollectionUpdateArgs args) throws ServiceException {
        Collection collection = createArgsToCollection(args);
        collection.setCollectionId(args.getCollectionId());
        int i = collectionDao.updateById(collection);
        return Result.ok(i == 1);
    }

    @Override
    public IResult<Boolean> cancelCollectionById(Long collectionId) throws ServiceException {
        Boolean flag = collectionDao.changeCollectionStatusById(collectionId, CollectionStatusConstants.CANCELED_COLLECT);
        return Result.ok(flag);
    }
}