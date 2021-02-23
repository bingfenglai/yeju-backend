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
package pers.lbf.yeju.service.interfaces.job;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/22 15:58
 */
public interface ITaskSchedulerService {

    /**
     * 获取quartz调度器的计划任务
     *
     * @param job 调度信息
     * @return 调度任务集合
     */
    IResult<List<JobInfoBean>> selectJobList(JobInfoBean job);

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    IResult<JobInfoBean> selectJobById(Long jobId);

    /**
     * 暂停任务
     *
     * @param job 调度信息
     * @return 结果
     */
    IResult<Boolean> pauseJob(JobInfoBean job) throws ServiceException;

    /**
     * 恢复任务
     *
     * @param job 调度信息
     * @return 结果
     */
    IResult<Boolean> resumeJob(JobInfoBean job) throws ServiceException;

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     * @return 结果
     */
    IResult<Boolean> deleteJob(JobInfoBean job) throws ServiceException;

    /**
     * 批量删除调度信息
     *
     * @param jobIds 需要删除的任务ID
     * @return 结果
     */
    IResult<Boolean> deleteJobByIds(Long[] jobIds) throws ServiceException;

    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     * @return 结果
     */
    IResult<Boolean> changeStatus(JobInfoBean job) throws ServiceException;

    /**
     * 立即运行任务
     *
     * @param job 调度信息
     * @return 结果
     */
    IResult<Boolean> run(JobInfoBean job) throws ServiceException;

    /**
     * 新增任务
     *
     * @param job 调度信息
     * @return 结果
     */
    IResult<Boolean> insertJob(JobInfoBean job) throws ServiceException;

    /**
     * 更新任务
     *
     * @param job 调度信息
     * @return 结果
     */
    IResult<Boolean> updateJob(JobInfoBean job) throws ServiceException;

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    IResult<Boolean> checkCronExpressionIsValid(String cronExpression);

}
