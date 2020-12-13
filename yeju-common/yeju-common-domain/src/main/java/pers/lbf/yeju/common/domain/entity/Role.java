package pers.lbf.yeju.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表(TableSystemRole)实体类
 *
 * @author makejava
 * @since 2020-12-13 00:23:00
 */
@TableName("table_system_role")
public class Role implements Serializable {
  private static final long serialVersionUID = -24978373309220169L;
  /** 主键 */
  @TableId
  private Long roleId;
  /** 角色名称 */
  private String roleName;
  /** 角色字符串 */
  private String roleCode;
  /** 角色状态索引 */
  private Long roleStatus;
  /** 角色状态值0未启用1启用，详见属性表 */
  private String roleStatusValue;
  /** 创建时间 */
  private Date createTime;
  /** 创建者 */
  private Long createBy;
  /** 更新时间 */
  private Date updateTime;
  /** 更改者 */
  private Long changedBy;
  /** 备注 */
  private String remark;
  /** 字段版本 */
  @Version
  private Integer versionNumber;
  /** 删除标识 */
  @TableLogic
  private Integer isDelete;

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  public Long getRoleStatus() {
    return roleStatus;
  }

  public void setRoleStatus(Long roleStatus) {
    this.roleStatus = roleStatus;
  }

  public String getRoleStatusValue() {
    return roleStatusValue;
  }

  public void setRoleStatusValue(String roleStatusValue) {
    this.roleStatusValue = roleStatusValue;
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

  @Override
  public String toString() {
    return "Role{" +
            "roleId=" + roleId +
            ", roleName='" + roleName + '\'' +
            ", roleCode='" + roleCode + '\'' +
            ", roleStatus=" + roleStatus +
            ", roleStatusValue='" + roleStatusValue + '\'' +
            ", createTime=" + createTime +
            ", createBy=" + createBy +
            ", updateTime=" + updateTime +
            ", changedBy=" + changedBy +
            ", remark='" + remark + '\'' +
            '}';
  }
}
