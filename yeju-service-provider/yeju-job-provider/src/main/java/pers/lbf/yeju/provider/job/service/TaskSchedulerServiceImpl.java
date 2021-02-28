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
package pers.lbf.yeju.provider.job.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.TaskScheduler;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.job.dao.ITaskSchedulerDao;
import pers.lbf.yeju.service.interfaces.job.IJobGroupService;
import pers.lbf.yeju.service.interfaces.job.IJobPropertiesService;
import pers.lbf.yeju.service.interfaces.job.IJobTriggerService;
import pers.lbf.yeju.service.interfaces.job.ITaskSchedulerService;
import pers.lbf.yeju.service.interfaces.job.pojo.JobDetailsBean;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;
import pers.lbf.yeju.service.interfaces.job.pojo.JobTriggerInfoBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/27 15:33
 */
@DubboService(interfaceClass = ITaskSchedulerService.class)
@Slf4j
public class TaskSchedulerServiceImpl implements ITaskSchedulerService {

    @Autowired
    private ITaskSchedulerDao taskSchedulerDao;

    @DubboReference
    private IJobGroupService jobGroupService;

    @DubboReference
    private IJobTriggerService triggerService;

    @DubboReference
    private IJobPropertiesService jobPropertiesService;

    /**
     * 查找所有定时任务 初始化用
     *
     * @return all job list
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/28 14:50
     */
    @Override
    public IResult<List<JobDetailsBean>> findAll() throws ServiceException {
        QueryWrapper<TaskScheduler> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<TaskScheduler> taskSchedulers = taskSchedulerDao.selectList(queryWrapper);
        List<JobDetailsBean> result = new LinkedList<>();
        for (TaskScheduler taskScheduler : taskSchedulers) {
            JobDetailsBean jobInfoBean = taskSchedulerToJobDetailsBean(taskScheduler);
            result.add(jobInfoBean);
        }

        return Result.ok(result);
    }

    /**
     * 获取quartz调度器的计划任务
     *
     * @param currentPage 当前页
     * @param size        每页显示条数
     * @return 调度任务集合
     */
    @Override
    public PageResult<JobInfoBean> findPage(Long currentPage, Long size) {
        Page<TaskScheduler> page = PageUtil.getPage(TaskScheduler.class, currentPage, size);
        Page<TaskScheduler> taskSchedulerPage = taskSchedulerDao.selectPage(page, null);

        List<TaskScheduler> taskSchedulerList = taskSchedulerPage.getRecords();
        List<JobInfoBean> result = new LinkedList<>();

        for (TaskScheduler taskScheduler : taskSchedulerList) {
            JobInfoBean jobInfoBean = this.taskSchedulerToJobInfoBean(taskScheduler);
            result.add(jobInfoBean);
        }

        return PageResult.ok(taskSchedulerPage.getTotal(), currentPage, size, result);
    }

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public IResult<JobInfoBean> selectJobById(Long jobId) {
        return null;
    }

    /**
     * 暂停任务
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Boolean> pauseJob(JobInfoBean job) throws ServiceException {
        return null;
    }

    /**
     * 恢复任务
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Boolean> resumeJob(JobInfoBean job) throws ServiceException {
        return null;
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Boolean> deleteJob(JobInfoBean job) throws ServiceException {
        return null;
    }

    /**
     * 批量删除调度信息
     *
     * @param jobIds 需要删除的任务ID
     * @return 结果
     */
    @Override
    public IResult<Boolean> deleteJobByIds(Long[] jobIds) throws ServiceException {
        return null;
    }

    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Boolean> changeStatus(JobInfoBean job) throws ServiceException {
        return null;
    }

    /**
     * 立即运行任务
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Boolean> run(JobInfoBean job) throws ServiceException {
        return null;
    }

    /**
     * 新增任务
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Boolean> insertJob(JobInfoBean job) throws ServiceException {
        return null;
    }

    /**
     * 更新任务
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Boolean> updateJob(JobInfoBean job) throws ServiceException {
        return null;
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public IResult<Boolean> checkCronExpressionIsValid(String cronExpression) {
        return null;
    }


    private JobInfoBean taskSchedulerToJobInfoBean(TaskScheduler taskScheduler) throws ServiceException {
        JobInfoBean jobInfoBean = new JobInfoBean();
        jobInfoBean.setMisfirePolicy(String.valueOf(taskScheduler.getMisfirePolicy()));
        jobInfoBean.setJobId(taskScheduler.getTaskId());

        jobInfoBean.setInvokeTargetStr(taskScheduler.getInvokeTarget());
        jobInfoBean.setJobName(taskScheduler.getTaskName());
        jobInfoBean.setJobGroup(getJobGroupName(taskScheduler.getTaskGroupId()));
        jobInfoBean.setCronExpression(taskScheduler.getCronExpression());
        jobInfoBean.setJobStatus(String.valueOf(taskScheduler.getStatus()));
        jobInfoBean.setCreatedDate(taskScheduler.getCreateTime());
        jobInfoBean.setJobProperties(getJobGroupProperties(taskScheduler.getTaskId()));
        jobInfoBean.setConcurrent(String.valueOf(taskScheduler.getConcurrent()));

        return jobInfoBean;
    }

    private String getJobGroupName(Long groupId) {
        if (groupId == null) {
            return "";
        }

        return jobGroupService.findJobGroupNameByGroupId(groupId).getData();
    }

    private Properties getJobGroupProperties(Long taskId) {

        return jobPropertiesService.findPropertiesByJobId(taskId).getData();
    }

    private JobDetailsBean taskSchedulerToJobDetailsBean(TaskScheduler taskScheduler) {
        JobDetailsBean jobDetailsBean = new JobDetailsBean();

        jobDetailsBean.setMisfirePolicy(String.valueOf(taskScheduler.getMisfirePolicy()));
        jobDetailsBean.setJobId(taskScheduler.getTaskId());
        jobDetailsBean.setInvokeTargetStr(taskScheduler.getInvokeTarget());
        jobDetailsBean.setJobName(taskScheduler.getTaskName());
        jobDetailsBean.setJobGroup(this.getJobGroupName(taskScheduler.getTaskGroupId()));
        jobDetailsBean.setCronExpression(taskScheduler.getCronExpression());
        jobDetailsBean.setJobStatus(String.valueOf(taskScheduler.getStatus()));
        jobDetailsBean.setCreatedDate(taskScheduler.getCreateTime());
        jobDetailsBean.setConcurrent(String.valueOf(taskScheduler.getConcurrent()));
        jobDetailsBean.setJobProperties(this.getJobGroupProperties(taskScheduler.getTaskId()));
        jobDetailsBean.setTriggerInfoBeanList(this.getTriggerInfoBeanListByJobId(taskScheduler.getTaskId()));
        return jobDetailsBean;
    }

    private List<JobTriggerInfoBean> getTriggerInfoBeanListByJobId(Long taskId) {
        return triggerService.findTriggerInfoBeanListByJobId(taskId).getData();
    }

}