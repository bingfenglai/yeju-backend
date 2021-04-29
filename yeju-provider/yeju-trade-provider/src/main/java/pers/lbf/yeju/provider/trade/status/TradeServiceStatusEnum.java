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

package pers.lbf.yeju.provider.trade.status;

import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/23 15:49
 */
public enum TradeServiceStatusEnum implements IStatus {
    /**
     * 交易创建服务状态枚举
     */
    create_trade_fail_unkonw_house_status("创建交易失败，无法获取房源状态", "tss01"),

    create_trade_fail_house_un_tradable("交易创建失败，该房源无法进行交易", "tss02"),

    update_house_status_failed("更新房源状态失败", "tss03"),

    create_domain_payment_failed("创建预支付单失败", "tss04");

    private String message;
    private String code;


    TradeServiceStatusEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    /**
     * 状态消息
     *
     * @return msg
     */
    @Override
    public String getMessage() {
        return null;
    }

    /**
     * 状态编码
     *
     * @return code
     */
    @Override
    public String getCode() {
        return null;
    }
}
