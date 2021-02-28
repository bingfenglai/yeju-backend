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
import java.util.Properties;

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

    private String invokeTargetStr;

    private String jobName;

    private String jobGroup;

    private String cronExpression;

    private String jobStatus;

    /**
     * 任务的创建时间
     */
    private Date createdDate;

    /**
     * 创建任务时是否立即执行
     */
    private boolean execute;

    /**
     * 是否支持并发
     */
    private String concurrent;

    /**
     * 任务运行时所需数据的容器
     */
    private Properties jobProperties;


    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getInvokeTargetStr() {
        return invokeTargetStr;
    }

    public void setInvokeTargetStr(String invokeTargetStr) {
        this.invokeTargetStr = invokeTargetStr;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isExecute() {
        return execute;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    public String getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent;
    }

    public Properties getJobProperties() {
        return jobProperties;
    }

    public void setJobProperties(Properties jobProperties) {
        this.jobProperties = jobProperties;
    }


    @Override
    public String toString() {
        return "JobInfoBean{" +
                "misfirePolicy='" + misfirePolicy + '\'' +
                ", jobId=" + jobId +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", createdDate=" + createdDate +
                ", execute=" + execute +
                ", concurrent='" + concurrent + '\'' +
                '}';
    }
}
