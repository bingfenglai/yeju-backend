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

package pers.lbf.yeju.service.interfaces.payment.pojo;

import pers.lbf.yeju.common.core.args.ICreateArgs;

import java.util.Date;

/**
 * 预支付单创建参数
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/28 22:57
 */
public class CreatePaymentAdvanceArgs implements ICreateArgs {
    private Long tradeId;

    /**
     * 转出账户id
     */
    private Long transferOutAccountId;
    /**
     * 目标账户id
     */
    private Long targetToAccountId;

    /**
     * 最终应扣除金额
     */
    private Double free;
    /**
     * 0待扣1已扣2已返
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private Long createBy;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getTransferOutAccountId() {
        return transferOutAccountId;
    }

    public void setTransferOutAccountId(Long transferOutAccountId) {
        this.transferOutAccountId = transferOutAccountId;
    }

    public Long getTargetToAccountId() {
        return targetToAccountId;
    }

    public void setTargetToAccountId(Long targetToAccountId) {
        this.targetToAccountId = targetToAccountId;
    }

    public Double getFree() {
        return free;
    }

    public void setFree(Double free) {
        this.free = free;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String account) {
        this.createBy = Long.valueOf(account);
    }

    @Override
    public void setCreateTime(Date date) {
        this.createTime = date;
    }
}
