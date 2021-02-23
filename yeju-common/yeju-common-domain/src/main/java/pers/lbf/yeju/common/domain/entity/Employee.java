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

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * 椰居平台员工表(TablePlatformEmployees)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-12-31 11:43:41
 */
@TableName("table_platform_employees")
public class Employee extends Model<Employee> {
    /**
     * 主键
     */
    @TableId
    private Long employeesId;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 性别0男1女，详见属性表
     */
    private Long gender;

    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 手机区号值
     */
    private String phoneNumberPrefix;
    /**
     * 所属领导
     */
    private Long leaderId;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 公司邮箱
     */
    private String email;
    /**
     * 员工状态1在职0离职
     */
    private Long employeeStatus;
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
    private Long changedBy;
    /**
     * 字段版本
     */
    private Integer versionNumber;
    /**
     * 删除标识
     */
    private Integer isDelete;
    /**
     * 员工信息添加时的月份，分区标识
     */
    private Integer monthAdded;
    /**
     * 员工离职时的月份。历史表的分区依据
     */
    private Integer monthOutmoded;
    /**
     * 工号
     */
    private Long employeesNumber;

    /**
     * 员工所属部门
     */
    private Long departmentId;


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(Long employeesId) {
        this.employeesId = employeesId;
    }

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

    public Long getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Long employeeStatus) {
        this.employeeStatus = employeeStatus;
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

    public Long getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Long employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

}
