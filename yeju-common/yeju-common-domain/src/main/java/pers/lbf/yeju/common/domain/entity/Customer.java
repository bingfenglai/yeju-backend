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
package pers.lbf.yeju.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息表，实名认证完成后搬table_business_customer_valid.所属账户超半年未登录系统，客户信(TableBusinessCustomer)实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-08 13:11:09
 */
@TableName("table_business_customer")
public class Customer extends Model<Customer> implements Serializable {
    protected static final long serialVersionUID = -56425136744170145L;
    /**
     * 主键
     */
    @TableId
    protected Long customerId;
    /**
     * 账户id
     */
    protected Long accountId;
    /**
     * 客户姓名
     */
    protected String customerName;
    /**
     * 性别0男1女，详见属性表
     */

    /**
     * 性别值 1男2女3未知
     */
    protected String gender;

    /**
     * 手机号
     */
    protected String phoneNumber;
    /**
     * 手机区号，比如中国是+86，详见属性表
     */
    protected String phoneNumberPrefix;

    /**
     * 客户认证状态0未认证1已认证
     */
    protected String customerStatus;
    /**
     * 所在省、自治州
     */
    protected Long province;

    /**
     * 所在城市
     */
    protected Long city;

    /**
     * 客户头像地址
     */
    protected String avatar;
    /**
     * 安全邮箱
     */
    protected String email;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 更改者
     */
    protected Long changedBy;
    /**
     * 备注
     */
    protected String remark;
    /**
     * 字段版本
     */
    @Version
    protected Integer versionNumber;
    /**
     * 删除标识
     */
    @TableLogic
    protected Integer isDelete;
    /**
     * 完成认证时所在月份，table_business_customer_valid分区标识
     */
    protected Integer monthCertified;
    /**
     * 客户信息添加时的月份
     */
    protected Integer monthAdded;
    /**
     * 客户信息过时时的月份。历史表的分区依据
     */
    protected Integer monthOutmoded;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberPrefix() {
        return phoneNumberPrefix;
    }

    public void setPhoneNumberPrefix(String phoneNumberPrefix) {
        this.phoneNumberPrefix = phoneNumberPrefix;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getMonthCertified() {
        return monthCertified;
    }

    public void setMonthCertified(Integer monthCertified) {
        this.monthCertified = monthCertified;
    }

    public Integer getMonthAdded() {
        return monthAdded;
    }

    public void setMonthAdded(Integer monthAdded) {
        this.monthAdded = monthAdded;
    }

    public Integer getMonthOutmoded() {
        return monthOutmoded;
    }

    public void setMonthOutmoded(Integer monthOutmoded) {
        this.monthOutmoded = monthOutmoded;
    }

}