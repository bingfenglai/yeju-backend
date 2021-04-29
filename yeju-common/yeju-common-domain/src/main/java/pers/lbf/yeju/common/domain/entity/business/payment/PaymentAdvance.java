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
package pers.lbf.yeju.common.domain.entity.business.payment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * (TableBusinessPaymentAdvance)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-22 00:46:38
 */
@TableName("table_business_payment_advance")
public class PaymentAdvance extends Model<PaymentAdvance> {
    /**
     * 主键
     */
    @TableId
    private Long paymentAdvanceId;

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
     * 支付流水号
     */
    private Long paymentId;
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
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更改者
     */
    private Long changedBy;
    /**
     * 备注
     */
    private String remark;
    /**
     * 字段版本
     */
    @TableLogic
    private Integer versionNumber;
    /**
     * 删除标识
     */
    @Version
    private Integer isDelete;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getPaymentAdvanceId() {
        return paymentAdvanceId;
    }

    public void setPaymentAdvanceId(Long paymentAdvanceId) {
        this.paymentAdvanceId = paymentAdvanceId;
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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Long changedBy) {
        this.changedBy = changedBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}