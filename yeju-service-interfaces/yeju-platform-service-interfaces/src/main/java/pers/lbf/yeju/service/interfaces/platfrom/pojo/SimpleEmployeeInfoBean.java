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
package pers.lbf.yeju.service.interfaces.platfrom.pojo;

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
    private String gender;

    /** 手机号 */
    private String phoneNumber;

    /** 手机区号值 */
    private String phoneNumberPrefix;
    /** 所属领导 */
    private Long leaderId;
    /** 头像地址 */
    private String avatar;
    /** 公司邮箱 */
    private String email;
    /** 员工状态1在职0离职 */
    private String employeesStatusStr;

    private Long employeesStatus;


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

    private SimpleEmployeeInfoBean leader;

    private SimpleDepartmentInfoBean department;

    public Long getEmployeesStatus() {
        return employeesStatus;
    }

    public void setEmployeesStatus(Long employeesStatus) {
        this.employeesStatus = employeesStatus;
    }

    public SimpleDepartmentInfoBean getDepartment() {
        return department;
    }

    public void setDepartment(SimpleDepartmentInfoBean department) {
        this.department = department;
    }

    public SimpleEmployeeInfoBean getLeader() {
        return leader;
    }

    public void setLeader(SimpleEmployeeInfoBean leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmployeesStatusStr() {
        return employeesStatusStr;
    }

    public void setEmployeesStatusStr(String employeesStatusStr) {
        this.employeesStatusStr = employeesStatusStr;
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
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberPrefix='" + phoneNumberPrefix + '\'' +
                ", leaderId=" + leaderId +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", employeesStatusStr='" + employeesStatusStr + '\'' +
                ", employeesStatus=" + employeesStatus +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", changedBy=" + changedBy +
                ", employeesNumber=" + employeesNumber +
                ", leader=" + leader +
                ", department=" + department +
                '}';
    }
}
