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

import java.io.Serializable;
import java.util.Date;

/**
 * 任务信息类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/21 23:12
 */
public class JobInfoBean implements Serializable {

    /**
     * 任务出错补偿策略
     */
    private String misfirePolicy;


    private Long jobId;

    private Class invokeTarget;

    private String jobName;

    private String jobGroup;

    private String cronExpression;

    private String jobStatus;

    private Date createdDate;

    /**
     * 是否支持并发
     */
    private String concurrent;

    @Override
    public String toString() {
        return "JobInfoBean{" +
                "misfirePolicy='" + misfirePolicy + '\'' +
                ", jobId=" + jobId +
                ", invokeTarget=" + invokeTarget +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", createdDate=" + createdDate +
                ", concurrent='" + concurrent + '\'' +
                '}';
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Class getInvokeTarget() {
        return invokeTarget;
    }

    public void setInvokeTarget(Class invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    public String getJobName() {

        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }


}
