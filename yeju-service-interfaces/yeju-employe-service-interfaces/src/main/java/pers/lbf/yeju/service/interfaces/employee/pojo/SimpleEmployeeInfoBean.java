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
package pers.lbf.yeju.service.interfaces.employee.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/18 20:33
 */
public class SimpleEmployeeInfoBean implements Serializable {

    /** 员工姓名 */
    private String name;
    /** 性别0男1女，详见属性表 */
    private Long gender;
    /** 性别值 */
    private String genderValue;
    /** 手机号 */
    private String phoneNumber;
    /** 手机区号，比如中国是+86，详见属性表 */
    private Long phoneNumberPrefix;
    /** 手机区号值 */
    private String phoneNumberPrefixValue;
    /** 所属领导 */
    private Long leaderId;
    /** 头像地址 */
    private String avatar;
    /** 公司邮箱 */
    private String email;
    /** 员工状态1在职0离职 */
    private Long employeesStatus;
    /** 员工状态值,见属性表 */
    private String employeesStatusValue;
    /** 创建时间 */
    private Date createTime;
    /** 创建者 */
    private Long createBy;
    /** 更新时间 */
    private Date updateTime;
    /** 更改者 */
    private Long changedBy;

    /** 工号 */
    private Long employeesNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
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

    public Long getEmployeesStatus() {
        return employeesStatus;
    }

    public void setEmployeesStatus(Long employeesStatus) {
        this.employeesStatus = employeesStatus;
    }

    public String getEmployeesStatusValue() {
        return employeesStatusValue;
    }

    public void setEmployeesStatusValue(String employeesStatusValue) {
        this.employeesStatusValue = employeesStatusValue;
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

    public Long getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Long employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    @Override
    public String toString() {
        return "SimpleEmployeeInfoBean{" +
                "name='" + name + '\'' +
                ", genderValue='" + genderValue + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberPrefix=" + phoneNumberPrefix +
                ", phoneNumberPrefixValue='" + phoneNumberPrefixValue + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", employeesStatusValue='" + employeesStatusValue + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", employeesNumber=" + employeesNumber +
                '}';
    }
}
