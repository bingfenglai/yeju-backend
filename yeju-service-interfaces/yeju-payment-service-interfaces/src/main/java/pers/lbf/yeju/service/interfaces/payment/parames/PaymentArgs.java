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

package pers.lbf.yeju.service.interfaces.payment.parames;

import pers.lbf.yeju.common.core.args.ICreateArgs;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付业务参数封装类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/29 11:36
 */
public class PaymentArgs implements ICreateArgs, Serializable {

    /**
     * 预支付单di
     */
    private Long paymentAdvanceId;

    /**
     * 账号
     */
    private Long accountNumber;

    private String payment;


    @Override
    public void setCreateBy(String account) {

    }

    @Override
    public void setCreateTime(Date date) {

    }
}
