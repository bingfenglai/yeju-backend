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
package pers.lbf.yeju.service.interfaces.customer.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import pers.lbf.yeju.common.blur.anotations.Blur;
import pers.lbf.yeju.common.blur.constants.ParameterConstants;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/8 13:09
 */
public class SimpleCustomerInfoBean implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long customerId;
    /**
     * 客户姓名
     */
    @Blur(ParameterConstants.NAME)
    private String customerName;
    /**
     * 性别0男1女，详见属性表
     */
    private String gender;

    /**
     * 手机号
     */
    @Blur(ParameterConstants.PHONE_NUMBER)
    private String phoneNumber;
    /**
     * 手机区号，比如中国是+86，详见属性表
     */
    private String phoneNumberPrefix;

    /**
     * 客户认证状态0未认证1已认证
     */
    private String customerStatus;
    /**
     * 所在省、自治州
     */
    private Long province;

    /**
     * 所在城市
     */
    private Long city;

    /**
     * 客户头像地址
     */
    private String avatar;
    /**
     * 安全邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return "SimpleCustomerInfoBean{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberPrefix=" + phoneNumberPrefix +
                ", customerStatus='" + customerStatus + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}
