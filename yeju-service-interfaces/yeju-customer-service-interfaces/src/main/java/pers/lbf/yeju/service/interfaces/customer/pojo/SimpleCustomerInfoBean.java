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

    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 性别0男1女，详见属性表
     */
    private Long gender;
    /**
     * 性别值
     */
    private String genderValue;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 手机区号，比如中国是+86，详见属性表
     */
    private Long phoneNumberPrefix;
    /**
     * 手机区号值
     */
    private String phoneNumberPrefixValue;
    /**
     * 客户认证状态0未认证1已认证
     */
    private String customerStatus;
    /**
     * 所在省、自治州
     */
    private Long province;
    /**
     * 所在省、自治州值
     */
    private String provinceValue;
    /**
     * 所在城市
     */
    private Long city;
    /**
     * 所在城市值
     */
    private String cityValue;
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
                "customerName='" + customerName + '\'' +
                ", gender='" + gender + '\'' +
                ", genderValue='" + genderValue + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberPrefix=" + phoneNumberPrefix +
                ", phoneNumberPrefixValue='" + phoneNumberPrefixValue + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", province=" + province +
                ", provinceValue='" + provinceValue + '\'' +
                ", city=" + city +
                ", cityValue='" + cityValue + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getPhoneNumberPrefix() {
        return phoneNumberPrefix;
    }

    public void setPhoneNumberPrefix(Long phoneNumberPrefix) {
        this.phoneNumberPrefix = phoneNumberPrefix;
    }

    public String getPhoneNumberPrefixValue() {
        return phoneNumberPrefixValue;
    }

    public void setPhoneNumberPrefixValue(String phoneNumberPrefixValue) {
        this.phoneNumberPrefixValue = phoneNumberPrefixValue;
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

    public String getProvinceValue() {
        return provinceValue;
    }

    public void setProvinceValue(String provinceValue) {
        this.provinceValue = provinceValue;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getCityValue() {
        return cityValue;
    }

    public void setCityValue(String cityValue) {
        this.cityValue = cityValue;
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
