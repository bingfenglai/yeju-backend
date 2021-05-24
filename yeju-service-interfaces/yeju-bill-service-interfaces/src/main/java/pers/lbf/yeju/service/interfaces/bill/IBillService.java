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

package pers.lbf.yeju.service.interfaces.bill;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.bill.pojo.BillQueryArgs;
import pers.lbf.yeju.service.interfaces.bill.pojo.CreateBillArgs;
import pers.lbf.yeju.service.interfaces.bill.pojo.SimpleBillInfoBean;

/**
 * 账单服务接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/28 20:37
 */
public interface IBillService {

    /**
     * 支付完工之后创建客户账单
     *
     * @param createBillArgs
     * @return
     * @throws ServiceException
     */
    IResult<String> createBill(CreateBillArgs createBillArgs) throws ServiceException;

    /**
     * 分页查询接口
     *
     * @param billQueryArgs
     * @return
     * @throws ServiceException
     */
    IResult<SimpleBillInfoBean> queryPage(BillQueryArgs billQueryArgs) throws ServiceException;


}
