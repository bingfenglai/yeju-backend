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
package pers.lbf.yeju.common.domain.entity.tarde;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易评价表，记录房源交易评价信息。实时评价记录在mongodb数据库。每天同步评论到mysql。以访问者IP最后一位作为(TableBusinessTradingEvaluation)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-22 20:52:05
 */
@TableName("table_business_trading_evaluation")
public class TradeEvaluation extends Model<TradeEvaluation> {
    /**
     * 评价主键
     */
    @TableId
    private Long evaluationId;

    private Long parentId;
    /**
     * 交易记录主键
     */
    private Long tradingId;


    /**
     * 评价内容
     */
    private String content;
    /**
     * 评价时间
     */
    private Date commentTime;
    /**
     * 字段创建时间
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
     * 描述符合度1很不符合2不符合3基本相符4比较相符5非常相符
     */
    private Integer descriptiveCoincidence;
    /**
     * 房东服务满意度1很不满意2不满意3基本满意4比较满意5非常满意
     */
    private Integer landlordService;
    /**
     * 房客行为评价1非常不满意2不满意3一般4比较满意5非常满意
     */
    private Integer tenantBehavior;
    /**
     * 评价类型0其他1房客评价2房东评价
     */
    private Integer evaluationType;
    /**
     * 评价状态0正常1待回访2回访结果已提交3评价可信4评价不可信
     * 详情见数据字典
     */
    private String evaluationStatus;
    /**
     * 评价点赞数（认同度）
     */
    private Long evaluationLikeCount;
    /**
     * 字段版本
     */
    @Version
    private Integer versionNumber;
    /**
     * 删除标识
     */
    @TableLogic
    private Integer isDelete;

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTradingId() {
        return tradingId;
    }

    public void setTradingId(Long tradingId) {
        this.tradingId = tradingId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
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

    public Integer getDescriptiveCoincidence() {
        return descriptiveCoincidence;
    }

    public void setDescriptiveCoincidence(Integer descriptiveCoincidence) {
        this.descriptiveCoincidence = descriptiveCoincidence;
    }

    public Integer getLandlordService() {
        return landlordService;
    }

    public void setLandlordService(Integer landlordService) {
        this.landlordService = landlordService;
    }

    public Integer getTenantBehavior() {
        return tenantBehavior;
    }

    public void setTenantBehavior(Integer tenantBehavior) {
        this.tenantBehavior = tenantBehavior;
    }

    public Integer getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(String evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public Long getEvaluationLikeCount() {
        return evaluationLikeCount;
    }

    public void setEvaluationLikeCount(Long evaluationLikeCount) {
        this.evaluationLikeCount = evaluationLikeCount;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.tradingId;
    }
}