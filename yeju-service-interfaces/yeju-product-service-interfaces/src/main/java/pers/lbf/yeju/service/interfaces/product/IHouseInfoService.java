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
import pers.lbf.yeju.common.core.args.UpdateArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.product.pojo.SimpleHouseInfoBean;

/**
 * 房源服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/17 15:28
 */
public interface IHouseInfoService {

    PageResult<SimpleHouseInfoBean> findPage(Long currentPage, Long size) throws ServiceException;

    IResult<Boolean> addOne(ICreateArgs args) throws ServiceException;

    IResult<Boolean> update(UpdateArgs args) throws ServiceException;

    IResult<Boolean> deleteBatch(String[] ids) throws ServiceException;

    IResult<Boolean> deleteById(Long id) throws ServiceException;

    IResult<HouseDetailsInfoBean> findDetailsByIdAndStatus(Long id, String houseStatus) throws ServiceException;


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
