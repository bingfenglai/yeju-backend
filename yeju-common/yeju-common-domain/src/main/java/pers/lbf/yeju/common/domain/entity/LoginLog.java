package pers.lbf.yeju.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志表，仅保存最新三个月的登录日志(TableSystemLoginLog)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-12-29 11:05:41
 */
@TableName("table_system_login_log")
public class LoginLog extends Model<LoginLog> {
  /** 主键 */
  @TableId
  private Long loginLogId;
  /** 访问账户 */
  private String account;
  /** 访问者姓名 */
  private String subjectName;
  /** 访问ip地址 */
  private String ip;
  /** 访问状态0失败1成功 */
  private Integer loginStatus;
  /** 访问失败时记录原因 */
  private String message;
  /** 访问时间 */
  private Date accentTime;
  /** ip地址最后一位，表分区依据 */
  private Integer lastIpNumber;

  public Long getLoginLogId() {
    return loginLogId;
  }

  public void setLoginLogId(Long loginLogId) {
    this.loginLogId = loginLogId;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Integer getLoginStatus() {
    return loginStatus;
  }

  public void setLoginStatus(Integer loginStatus) {
    this.loginStatus = loginStatus;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getAccentTime() {
    return accentTime;
  }

  public void setAccentTime(Date accentTime) {
    this.accentTime = accentTime;
  }

  public Integer getLastIpNumber() {
    return lastIpNumber;
  }

  public void setLastIpNumber(Integer lastIpNumber) {
    this.lastIpNumber = lastIpNumber;
  }

  /**
   * 获取主键值
   *
   * @return 主键值
   */
  @Override
  protected Serializable pkVal() {
    return this.loginLogId;
  }
}
