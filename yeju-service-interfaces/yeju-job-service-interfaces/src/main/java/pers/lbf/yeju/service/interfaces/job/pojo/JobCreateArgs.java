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
package pers.lbf.yeju.service.interfaces.job.pojo;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/2 9:02
 */
@ApiModel("创建新任务请求参数")
public class JobCreateArgs implements Serializable {

    @NotNull(message = "任务名称不能为空")
    private String taskName;
    /**
     * 任务组id
     */
    @NotNull(message = "任务组不能为空")
    private Long taskGroupId;
    /**
     * 调用目标字符串
     */
    @NotNull(message = "调用目标不能为空")
    private String invokeTarget;
    /**
     * cron任务执行表达式
     */
    @NotNull(message = "cron表达式不能为空")
    private String cronExpression;
    /**
     * 当任务出错时的执行策略1立即执行 2执行一次 3放弃执行
     */
    private Integer misfirePolicy;
    /**
     * 是否并发执行0否1是
     */
    @NotNull(message = "执行策略不能为空")
    private Integer concurrent;
    /**
     * 执行状态0暂停1正常
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    @NotNull(message = "任务触发器配置不能为空")
    private TriggerCreateArgs triggerCreateArgs;

    private TaskPropertiesCreateArgs taskPropertiesCreateArgs;

    public TaskPropertiesCreateArgs getTaskPropertiesCreateArgs() {
        return taskPropertiesCreateArgs;
    }

    public void setTaskPropertiesCreateArgs(TaskPropertiesCreateArgs taskPropertiesCreateArgs) {
        this.taskPropertiesCreateArgs = taskPropertiesCreateArgs;
    }

    public TriggerCreateArgs getTriggerCreateArgs() {
        return triggerCreateArgs;
    }

    public void setTriggerCreateArgs(TriggerCreateArgs triggerCreateArgs) {
        this.triggerCreateArgs = triggerCreateArgs;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(Long taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public String getInvokeTarget() {
        return invokeTarget;
    }

    public void setInvokeTarget(String invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getMisfirePolicy() {
        return misfirePolicy;
    }

    public void setMisfirePolicy(Integer misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    public Integer getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(Integer concurrent) {
        this.concurrent = concurrent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
