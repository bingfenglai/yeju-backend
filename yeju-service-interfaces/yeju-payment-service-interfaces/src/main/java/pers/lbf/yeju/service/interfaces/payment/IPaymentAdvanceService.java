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

package pers.lbf.yeju.service.interfaces.payment;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.payment.pojo.CreatePaymentAdvanceArgs;
import pers.lbf.yeju.service.interfaces.payment.pojo.PaymentAdvanceInfoBean;
import pers.lbf.yeju.service.interfaces.payment.pojo.UpdateStatusByIdArgs;

/**
 * 预付款服务接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/29 10:29
 */
public interface IPaymentAdvanceService {

    /**
     * 创建预扣款记录
     *
     * @param advanceArgs
     * @return 预扣记录id
     * @throws ServiceException
     */
    IResult<String> createPaymentAdvance(CreatePaymentAdvanceArgs advanceArgs) throws ServiceException;

    IResult<PaymentAdvanceInfoBean> findPaymentAdvanceInfoById(Long id) throws ServiceException;

    /**
     * 更新预扣记录状态 支付成功后回写
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> updateStatusById(UpdateStatusByIdArgs args) throws ServiceException;


}
