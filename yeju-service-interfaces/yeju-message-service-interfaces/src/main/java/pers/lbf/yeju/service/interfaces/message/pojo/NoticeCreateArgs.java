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
package pers.lbf.yeju.service.interfaces.message.pojo;

import pers.lbf.yeju.common.core.args.ICreateArgs;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 创建通知 参数
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/3 22:47
 */
public class NoticeCreateArgs implements Serializable, ICreateArgs {

    /**
     * 通知标题
     */
    @NotNull(message = "通知标题不能为空")
    private String noticeTitle;
    /**
     * 通知正文
     */

    @NotNull(message = "通知正文不能为空")
    private String content;
    /**
     * 通知类型0其他1通知2公告
     */
    @NotNull(message = "通知类型不能为空")
    private String noticeType;
    /**
     * 通知状态0关闭1正常
     */
    private Integer status;
    /**
     * 公告开始时间
     */

    private Date startTime;
    /**
     * 公告结束时间
     */
    private Date endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 谁发
     */
    private Long beFrom;
    /**
     * 发给谁
     */
    @NotNull(message = "公告范围不能为空")
    private Long sendTo;

    private Date createTime;
    /**
     * 创建者
     */
    private String createBy;

    /**
     * 接收者类型 1全部 2群组 3 个人
     */
    private String receiverType;

    @NotNull(message = "通知生效日期范围不能为空")
    private String[] dateRange;

    @Override
    public String toString() {
        return "NoticeCreateArgs{" +
                "noticeTitle='" + noticeTitle + '\'' +
                ", content='" + content + '\'' +
                ", noticeType='" + noticeType + '\'' +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", remark='" + remark + '\'' +
                ", beFrom=" + beFrom +
                ", sendTo=" + sendTo +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", receiverType='" + receiverType + '\'' +
                ", dateRange=" + Arrays.toString(dateRange) +
                '}';
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public String[] getDateRange() {
        return dateRange;
    }

    public void setDateRange(String[] dateRange) {
        this.dateRange = dateRange;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getBeFrom() {
        return beFrom;
    }

    public void setBeFrom(Long beFrom) {
        this.beFrom = beFrom;
    }

    public Long getSendTo() {
        return sendTo;
    }

    public void setSendTo(Long sendTo) {
        this.sendTo = sendTo;
    }
}
