package pers.lbf.yeju.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户-角色关系表 N-1(RTAccountRole)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-12-13 00:15:19
 */
@TableName("r_t_account_role")
public class AccountRole extends Model<AccountRole> implements Serializable {
  // 账号主键
  @TableId
  private Long accountId;
  // 角色主键
  @TableId
  private Long roleId;
  /** 状态1启用0不启用 */
  private String status;
  // 创建时间
  private Date createTime;
  // 创建者
  private Long createBy;
  // 更新时间
  private Date updateTime;
  // 更改者
  private Long changedBy;
  // 字段版本
  @Version
  private Integer versions;
  // 删除标识
  @TableLogic
  private Integer isDelete;
  // 备注
  private String remark;
  // 系统账户
  private String accountNumber;
  // 手机号
  private String phoneNumber;

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
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

  public Integer getVersions() {
    return versions;
  }

  public void setVersions(Integer versions) {
    this.versions = versions;
  }

  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "AccountRole{" +
            "accountId=" + accountId +
            ", roleId=" + roleId +
            ", status='" + status + '\'' +
            ", createTime=" + createTime +
            ", createBy=" + createBy +
            ", updateTime=" + updateTime +
            ", changedBy=" + changedBy +
            ", remark='" + remark + '\'' +
            ", accountNumber='" + accountNumber + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
