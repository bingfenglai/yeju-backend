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

import pers.lbf.yeju.common.core.args.ICreateArgs;
import pers.lbf.yeju.common.core.args.IUpdateArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.product.pojo.*;
import pers.lbf.yeju.service.interfaces.product.status.HouseStatusEnum;

/**
 * 房源服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/17 15:28
 */
public interface IHouseInfoService {


    IResult<Boolean> updateStatusById(Long houseId, HouseStatusEnum status) throws ServiceException;


    /**
     * 房源搜索接口
     *
     * @param searchArgs
     * @return
     * @throws ServiceException
     */
    PageResult<HouseInfoDoc> search(HouseSearchArgs searchArgs) throws ServiceException;

    IResult<Boolean> removeByIdFromElasticsearch(String id) throws ServiceException;

    /**
     * 拷贝 房源信息进可交易表、es数据库
     *
     * @param houseId
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> copyHouseInfoToTradable(Long houseId) throws ServiceException;

    /**
     * 房源综合查询接口
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    PageResult<SimpleHouseInfoBean> query(HouseInfoQueryArgs args) throws ServiceException;

    @Deprecated
    PageResult<SimpleHouseInfoBean> findPage(Long currentPage, Long size) throws ServiceException;

    /**
     * 根据房源id查询交易需要到的信息
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    IResult<HouseAboutTradeInfoBean> findAboutTradeInfoById(Long id) throws ServiceException;

    IResult<Boolean> addOne(ICreateArgs args) throws ServiceException;

    IResult<Boolean> update(IUpdateArgs args) throws ServiceException;

    IResult<Boolean> deleteBatch(String[] ids) throws ServiceException;

    IResult<Boolean> deleteById(Long id) throws ServiceException;

    IResult<HouseDetailsInfoBean> findDetailsByIdAndStatus(Long id, String houseStatus) throws ServiceException;


    IResult<HouseStatusEnum> getHouseStatusEnumById(Long id) throws ServiceException;


    /**
     * 房源信息审核操作 更改状态
     *
     * @param id          房源标识
     * @param houseStatus 房源状态
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/20 15:43
     */
    IResult<Boolean> verifyById(Long id, String houseStatus) throws ServiceException;

}
