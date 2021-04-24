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

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 租期到期预警表.记录待通知缴纳租金的账号信息，失效搬历史。按照账户id后两位分区(TableBusinessWarningAccount)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-24 15:56:32
 */
@TableName("table_business_warning_job")
public class WarningJobInfo extends Model<WarningJobInfo> {
    @TableId
    private Long warningId;
    /**
     * 账户标识
     */
    private Long accountId;
    /**
     * 客户手机号
     */
    private String phoneNumber;
    /**
     * 绑定的邮箱
     */
    private String email;
    /**
     * 预警状态
     * 0待预警
     * 1已预警
     * 2预警前缴纳
     * 3预警后缴纳但未逾期
     * 4逾期未缴纳
     * 5逾期后缴纳
     */
    private String warningStatus;
    /**
     * 预警通知时间
     */
    private Date noticeTime;
    /**
     * 通知方式
     * 0 APP推送
     * 1短信
     * 2邮箱
     */
    private String noticeMode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更改者
     */
    private Long changedBy;
    /**
     * 备注
     */
    private String remark;
    /**
     * 字段版本
     */
    private Integer versionNumber;
    /**
     * 删除标识
     */
    private Integer isDelete;
    /**
     * 预警任务执行状态，是否成功1表示成功
     */
    private Integer success;
    /**
     * 分区标识
     */
    private String partitionNumber;


    public Long getWarningId() {
        return warningId;
    }

    public void setWarningId(Long warningId) {
        this.warningId = warningId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getPhnoeNumber() {
        return phoneNumber;
    }

    public void setPhnoeNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWarningStatus() {
        return warningStatus;
    }

    public void setWarningStatus(String warningStatus) {
        this.warningStatus = warningStatus;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticeMode() {
        return noticeMode;
    }

    public void setNoticeMode(String noticeMode) {
        this.noticeMode = noticeMode;
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

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getPartitionNumber() {
        return partitionNumber;
    }

    public void setPartitionNumber(String partitionNumber) {
        this.partitionNumber = partitionNumber;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.warningId;
    }
}