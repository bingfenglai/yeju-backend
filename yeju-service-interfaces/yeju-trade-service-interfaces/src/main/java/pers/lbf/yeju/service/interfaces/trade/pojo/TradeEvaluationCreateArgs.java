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

package pers.lbf.yeju.service.interfaces.trade.pojo;

import pers.lbf.yeju.common.core.args.ICreateArgs;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 22:43
 */
public class TradeEvaluationCreateArgs implements ICreateArgs {


    private Long parentId;
    /**
     * 交易记录主键
     */
    @NotNull(message = "交易标识不能为空")
    private Long tradingId;

    /**
     * 评价内容
     */

    private String content;

    /**
     * 描述符合度1很不符合2不符合3基本相符4比较相符5非常相符
     */
    @NotNull(message = "描述符合度不能为空")
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
    @NotNull(message = "评价类型不能为空")
    private Integer evaluationType;
    /**
     * 评价状态0正常1待回访2回访结果已提交3评价可信4评价不可信
     * 详情见数据字典
     */
    private String evaluationStatus;

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

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
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

    public Long getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String account) {
        this.createBy = Long.valueOf(account);
    }

    @Override
    public void setCreateTime(Date date) {
        this.createTime = date;
    }
}
