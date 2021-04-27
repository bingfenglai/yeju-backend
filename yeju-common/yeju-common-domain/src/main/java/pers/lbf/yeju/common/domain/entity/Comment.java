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

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论信息表,可以是针对【提问】的回答，亦可以是针对图文的评论。此表存于mongodb(TableBusinessComment)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-24 20:55:39
 */
@TableName("table_business_comment")
public class Comment extends Model<Comment> {
    /**
     * 主键
     */

    private Long commentId;
    /**
     * 评论者名字
     */
    private String reviewersName;
    /**
     * 被评论对象的主键（针对评论的评论时，则为评论的主键）
     */
    private Long commentedObjectId;
    /**
     * 内容
     */
    private String content;
    /**
     * 评论类型0图文评论1提问评论2评论的评论.详情见数据字典
     */
    private String commentType;
    /**
     * 图片评论路径
     */
    private String commentImagesUrl;
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


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getReviewersName() {
        return reviewersName;
    }

    public void setReviewersName(String reviewersName) {
        this.reviewersName = reviewersName;
    }

    public Long getCommentedObjectId() {
        return commentedObjectId;
    }

    public void setCommentedObjectId(Long commentedObjectId) {
        this.commentedObjectId = commentedObjectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getCommentImagesUrl() {
        return commentImagesUrl;
    }

    public void setCommentImagesUrl(String commentImagesUrl) {
        this.commentImagesUrl = commentImagesUrl;
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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.commentedObjectId;
    }
}