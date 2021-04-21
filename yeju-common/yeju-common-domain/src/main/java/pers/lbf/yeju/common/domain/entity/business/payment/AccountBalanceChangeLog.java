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
 * 对于平台内部充值账户的余额转入转出记录,  交易费用记录  以月份作为分区标识
 * 账户余额变动记录表(TableBusinessAccountBalanceChangeLog)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-22 00:43:18
 */
@TableName("table_business_account_balance_change_log")
public class AccountBalanceChangeLog extends Model<AccountBalanceChangeLog> {
    /**
     * 主键
     */
    @TableId
    private Long logId;
    /**
     * 账户标识
     */
    private Long accountId;
    /**
     * 0充值
     * 1提现
     * 2付押金
     * 3付房租
     * 4收到房租
     */
    private Integer type;
    /**
     * 0银行卡充值
     * 1支付宝账户充值
     * 2微信账户充值
     * 3房租收入
     */
    private Integer source;
    /**
     * 转入转出金额
     */
    private Double sum;
    /**
     * 如果是第三方支付的话需要记录第三方流水号
     */
    private Long thirdPartySerialNumber;
    /**
     * 余额变动前总数
     */
    private Double originalBalance;
    /**
     * 变动之后的金额
     */
    private Double afterBalance;
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


    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Long getThirdPartySerialNumber() {
        return thirdPartySerialNumber;
    }

    public void setThirdPartySerialNumber(Long thirdPartySerialNumber) {
        this.thirdPartySerialNumber = thirdPartySerialNumber;
    }

    public Double getOriginalBalance() {
        return originalBalance;
    }

    public void setOriginalBalance(Double originalBalance) {
        this.originalBalance = originalBalance;
    }

    public Double getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(Double afterBalance) {
        this.afterBalance = afterBalance;
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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.logId;
    }
}