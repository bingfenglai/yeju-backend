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
package pers.lbf.yeju.provider.payment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.business.payment.PaymentAdvance;
import pers.lbf.yeju.provider.payment.dao.IPaymentAdvanceDao;
import pers.lbf.yeju.service.interfaces.payment.IPaymentAdvanceService;
import pers.lbf.yeju.service.interfaces.payment.pojo.CreatePaymentAdvanceArgs;
import pers.lbf.yeju.service.interfaces.payment.pojo.PaymentAdvanceInfoBean;
import pers.lbf.yeju.service.interfaces.payment.pojo.UpdateStatusByIdArgs;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/29 10:53
 */
@DubboService(interfaceClass = IPaymentAdvanceService.class, timeout = 10000, retries = 0)
@Slf4j
public class PaymentAdvanceServiceImpl implements IPaymentAdvanceService {

    @Autowired
    private IPaymentAdvanceDao paymentAdvanceDao;

    /**
     * 创建预扣款记录
     *
     * @param advanceArgs
     * @return 预扣记录id
     * @throws ServiceException
     */
    @Override
    public IResult<String> createPaymentAdvance(CreatePaymentAdvanceArgs advanceArgs) throws ServiceException {
        PaymentAdvance paymentAdvance = buildPaymentAdvance(advanceArgs);
        int insert = paymentAdvanceDao.insert(paymentAdvance);
        if (insert == 1) {
            return Result.ok(String.valueOf(paymentAdvance.getPaymentAdvanceId()));
        }
        throw new ServiceException();
    }

    private PaymentAdvance buildPaymentAdvance(CreatePaymentAdvanceArgs advanceArgs) {
        PaymentAdvance paymentAdvance = new PaymentAdvance();
        paymentAdvance.setTradeId(advanceArgs.getTradeId());

        paymentAdvance.setTransferOutAccountId(advanceArgs.getTransferOutAccountId());
        paymentAdvance.setTargetToAccountId(advanceArgs.getTargetToAccountId());

        paymentAdvance.setFree(advanceArgs.getFree());
        paymentAdvance.setStatus(advanceArgs.getStatus());
        paymentAdvance.setCreateTime(advanceArgs.getCreateTime());
        paymentAdvance.setCreateBy(advanceArgs.getCreateBy());

        return paymentAdvance;
    }

    @Override
    public IResult<PaymentAdvanceInfoBean> findPaymentAdvanceInfoById(Long id) throws ServiceException {
        return null;
    }

    /**
     * 更新预扣记录状态 支付成功后回写
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<Boolean> updateStatusById(UpdateStatusByIdArgs args) throws ServiceException {
        return null;
    }
}