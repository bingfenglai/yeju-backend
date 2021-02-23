package pers.lbf.yeju.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 椰居平台部门表(TablePlatformDepartment)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-14 16:01:11
 */

@TableName("table_platform_department")
public class Department extends Model<Department> {
    //主键
    @TableId
    private Long departmentId;
    //父部门主键
    private Long parentDepartmentId;
    //部门名称
    private String name;
    //负责人id
    private Long leaderId;
    //手机号
    private String phoneNumber;
    //手机区号，比如中国是+86，详见属性表

    private Long phoneNumberPrefix;
    //部门（团队）邮箱
    private String email;
    //部门状态0未启用1启用
    private Long yejuDepartmentStatus;
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
    //更改者
    private Long changedBy;
    //字段版本
    @Version
    private Integer versionNumber;
    //删除标识
    @TableLogic
    private Integer isDelete;


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Long parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getYejuDepartmentStatus() {
        return yejuDepartmentStatus;
    }

    public void setYejuDepartmentStatus(Long yejuDepartmentStatus) {
        this.yejuDepartmentStatus = yejuDepartmentStatus;
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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.departmentId;
    }
}