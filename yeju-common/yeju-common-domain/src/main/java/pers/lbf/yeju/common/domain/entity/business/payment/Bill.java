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

import java.io.Serializable;
import java.util.Date;

/**
 * 账单表，记录平台支付信息(TableBusinessbill)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-22 00:45:47
 */
@TableName("table_business_bill")
public class Bill extends Model<Bill> {
    /**
     * 交易流水号
     */

    private Long tradeId;
    /**
     * 主键
     */
    @TableId
    private Long billId;
    /**
     * 域支付id  对应第三方支付的预支付id
     */
    private Long domainPaymentId;
    /**
     * 支付状态
     * 0支付工单已创建
     * 1支付成功  支付不成功的不算进账单里面
     */
    private String billStatus;
    /**
     * 如果是第三方支付的话需要记录第三方流水号
     */
    private Long thirdPartySerialNumber;
    /**
     * 渠道0平台账户支付1支付宝支付2微信支付3网银
     */
    private String billMode;
    /**
     * 转出账户id
     */
    private Long transferOutAccountId;
    /**
     * 转入账户id
     */
    private Long ransferToAccountId;
    /**
     * 支付金额
     */
    private Double free;
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
    /**
     * 0其他
     * 1支付宝转入
     * 2微信转入
     * 3工商行转入
     * 4提现
     * 5平台补贴
     * 6交租金
     * 7交押金
     * 8系统代扣租金
     */
    private String billType;


    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getDomainPaymentId() {
        return domainPaymentId;
    }

    public void setDomainPaymentId(Long domainPaymentId) {
        this.domainPaymentId = domainPaymentId;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public Long getThirdPartySerialNumber() {
        return thirdPartySerialNumber;
    }

    public void setThirdPartySerialNumber(Long thirdPartySerialNumber) {
        this.thirdPartySerialNumber = thirdPartySerialNumber;
    }

    public String getBillMode() {
        return billMode;
    }

    public void setBillMode(String billMode) {
        this.billMode = billMode;
    }

    public Long getTransferOutAccountId() {
        return transferOutAccountId;
    }

    public void setTransferOutAccountId(Long transferOutAccountId) {
        this.transferOutAccountId = transferOutAccountId;
    }

    public Long getRansferToAccountId() {
        return ransferToAccountId;
    }

    public void setRansferToAccountId(Long ransferToAccountId) {
        this.ransferToAccountId = ransferToAccountId;
    }

    public Double getFree() {
        return free;
    }

    public void setFree(Double free) {
        this.free = free;
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

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.billId;
    }
}