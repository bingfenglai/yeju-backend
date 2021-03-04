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
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.domain.entity.TaskScheduler;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.job.constant.ScheduleConstants;
import pers.lbf.yeju.provider.job.dao.ITaskSchedulerDao;
import pers.lbf.yeju.provider.job.manager.JobManager;
import pers.lbf.yeju.provider.job.status.CronExpressionStatus;
import pers.lbf.yeju.provider.job.util.CronExpressionUtil;
import pers.lbf.yeju.service.interfaces.job.IJobGroupService;
import pers.lbf.yeju.service.interfaces.job.IJobPropertiesService;
import pers.lbf.yeju.service.interfaces.job.IJobTriggerService;
import pers.lbf.yeju.service.interfaces.job.ITaskSchedulerService;
import pers.lbf.yeju.service.interfaces.job.pojo.*;

import java.util.*;

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

    @Autowired
    private JobManager jobManager;

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
        queryWrapper.eq("status", Integer.valueOf(ScheduleConstants.Status.NORMAL.getValue()));

        // 1.查询任务信息
        List<TaskScheduler> taskSchedulers = taskSchedulerDao.selectList(queryWrapper);
        List<JobDetailsBean> result = new LinkedList<>();
        for (TaskScheduler taskScheduler : taskSchedulers) {
            JobDetailsBean jobInfoBean = taskSchedulerToJobDetailsBean(taskScheduler);

            // 2.查询任务对应的触发器          
            List<JobTriggerInfoBean> triggerInfoBeanList = triggerService.findTriggerInfoBeanListByJobId(taskScheduler.getTaskId()).getData();
            jobInfoBean.setTriggerInfoBeanList(triggerInfoBeanList);

            // 3. 查询任务执行所需参数 初始化数据
            Properties data = jobPropertiesService.findPropertiesByJobId(taskScheduler.getTaskId()).getData();
            jobInfoBean.setJobProperties(data);

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
        QueryWrapper<TaskScheduler> queryWrapper = new QueryWrapper<>();

        //queryWrapper.select("");


        Page<TaskScheduler> page = PageUtil.getPage(TaskScheduler.class, currentPage, size);

        Page<TaskScheduler> taskSchedulerPage = taskSchedulerDao.selectPage(page, queryWrapper);

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
        TaskScheduler taskScheduler = taskSchedulerDao.selectById(jobId);
        JobInfoBean jobInfoBean = taskSchedulerToJobInfoBean(taskScheduler);
        return Result.ok(jobInfoBean);

    }

    /**
     * 暂停任务
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Object> pauseJob(JobDetailsBean job) throws ServiceException {
        jobManager.pauseJob(job);
        changeStatus(job.getJobId(), Integer.valueOf(ScheduleConstants.Status.PAUSE.getValue()));
        return SimpleResult.ok();
    }

    /**
     * 恢复任务
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Object> resumeJob(JobInfoBean job) throws ServiceException {
        jobManager.resumeJob(job);
        changeStatus(job.getJobId(), Integer.valueOf(ScheduleConstants.Status.NORMAL.getValue()));
        return SimpleResult.ok();
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Object> deleteJob(JobDetailsBean job) throws ServiceException {
        jobManager.removeJob(job, job.getTriggerInfoBeanList().get(0));
        if (job.getJobId() != null) {
            taskSchedulerDao.deleteById(job.getJobId());
        }
        triggerService.deleteByJobId(job.getJobId());
        return SimpleResult.ok();
    }

    /**
     * 批量删除调度信息
     *
     * @param jobIds 需要删除的任务ID
     * @return 结果
     */
    @Override
    public IResult<Object> deleteJobByIds(Long[] jobIds) throws ServiceException {
        List<JobInfoBean> jobInfoBeans = taskSchedulerDao.findNameAndGroupNameByIds(jobIds);
        for (JobInfoBean jobInfoBean : jobInfoBeans) {
            List<JobTriggerInfoBean> triggerInfoBeanList = triggerService.findTriggerInfoBeanListByJobId(jobInfoBean.getJobId()).getData();
            for (JobTriggerInfoBean triggerInfoBean : triggerInfoBeanList) {
                jobManager.removeJob(jobInfoBean, triggerInfoBean);
                taskSchedulerDao.deleteById(jobInfoBean.getJobId());
            }

        }
        triggerService.deleteByJobIds(jobIds);

        return SimpleResult.ok();
    }

    /**
     * 任务调度状态修改
     *
     * @param jobId
     * @param newStatus
     * @return 结果
     */
    @Override
    public IResult<Object> changeStatus(Long jobId, Integer newStatus) throws ServiceException {

        taskSchedulerDao.updateStatusById(jobId, newStatus);
        return SimpleResult.ok();
    }


    /**
     * 立即运行任务
     *
     * @param job 调度信息
     * @return 结果
     */
    @Override
    public IResult<Object> run(JobInfoBean job) throws ServiceException {
        jobManager.runJob(job);
        changeStatus(job.getJobId(), Integer.valueOf(ScheduleConstants.Status.NORMAL.getValue()));
        return SimpleResult.ok();
    }

    /**
     * 新增任务
     *
     * @param args 调度信息
     * @return 结果
     */
    @Override
    public IResult<Object> create(JobCreateArgs args) throws ServiceException {
        TaskScheduler taskScheduler = jobCreateArgsToTaskScheduler(args);
        taskSchedulerDao.insert(taskScheduler);
        if (args.getTriggerCreateArgs() != null) {
            triggerService.create(args.getTriggerCreateArgs());
            JobInfoBean jobInfoBean = jobCreateArgsToInfoBean(args);
            JobTriggerInfoBean triggerInfoBean = triggerCreateArgsToJobTriggerInfoBean(args.getTriggerCreateArgs());
            jobManager.createJob(jobInfoBean, triggerInfoBean);
        } else {
            throw ServiceException.getInstance("任务触发器信息不能为空",
                    ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

        if (args.getTaskPropertiesCreateArgs() != null) {
            jobPropertiesService.create(args.getTaskPropertiesCreateArgs());
        }


        return SimpleResult.ok();
    }


    /**
     * 更新任务
     *
     * @param args 调度信息
     * @return 结果
     */

    @Override
    public IResult<Object> update(JobUpdateArgs args) throws ServiceException {
        JobInfoBean jobInfoBean = jobCreateArgsToInfoBean(args);
        TriggerCreateArgs triggerCreateArgs = args.getTriggerUpdateArgs();
        JobTriggerInfoBean triggerInfoBean = triggerCreateArgsToJobTriggerInfoBean(triggerCreateArgs);

        jobManager.removeJob(jobInfoBean, triggerInfoBean);
        jobManager.createJob(jobInfoBean, triggerInfoBean);

        TaskScheduler taskScheduler = jobUpdateArgsToTaskScheduler(args);
        taskSchedulerDao.updateById(taskScheduler);
        TriggerUpdateArgs triggerUpdateArgs = args.getTriggerUpdateArgs();
        triggerService.update(triggerUpdateArgs);

        return SimpleResult.ok();
    }


    private JobTriggerInfoBean triggerCreateArgsToJobTriggerInfoBean(TriggerCreateArgs triggerCreateArgs) {
        JobTriggerInfoBean jobTriggerInfoBean = new JobTriggerInfoBean();
        jobTriggerInfoBean.setName(triggerCreateArgs.getTriggerName());
        jobTriggerInfoBean.setGroup(triggerCreateArgs.getTriggerGroupName());
        jobTriggerInfoBean.setStartTime(triggerCreateArgs.getStartTime());
        jobTriggerInfoBean.setEndTime(triggerCreateArgs.getEndTime());
        jobTriggerInfoBean.setTimeZone(TimeZone.getTimeZone(triggerCreateArgs.getTimezone()));
        jobTriggerInfoBean.setDescription(triggerCreateArgs.getDescription());
        return jobTriggerInfoBean;
    }

    private JobInfoBean jobCreateArgsToInfoBean(JobCreateArgs args) {
        JobInfoBean jobInfoBean = new JobInfoBean();
        jobInfoBean.setMisfirePolicy(String.valueOf(args.getMisfirePolicy()));

        jobInfoBean.setInvokeTargetStr(args.getInvokeTarget());
        jobInfoBean.setJobName(args.getTaskName());
        jobInfoBean.setJobGroup(jobGroupService.findJobGroupNameByGroupId(args.getTaskGroupId()).getData());
        jobInfoBean.setCronExpression(args.getCronExpression());
        jobInfoBean.setJobStatus(String.valueOf(args.getStatus()));

        jobInfoBean.setConcurrent(String.valueOf(args.getConcurrent()));
        //jobInfoBean.setJobProperties();
        return jobInfoBean;
    }

    private TaskScheduler jobCreateArgsToTaskScheduler(JobCreateArgs args) {
        TaskScheduler taskScheduler = new TaskScheduler();
        taskScheduler.setTaskName(args.getTaskName());
        taskScheduler.setTaskGroupId(args.getTaskGroupId());
        taskScheduler.setInvokeTarget(args.getInvokeTarget());
        taskScheduler.setCronExpression(args.getCronExpression());
        taskScheduler.setMisfirePolicy(args.getMisfirePolicy());
        taskScheduler.setConcurrent(args.getConcurrent());
        taskScheduler.setStatus(args.getStatus());
        taskScheduler.setCreateTime(args.getCreateTime());
        taskScheduler.setRemark(args.getRemark());
        return taskScheduler;
    }


    private TaskScheduler jobUpdateArgsToTaskScheduler(JobUpdateArgs args) {
        TaskScheduler taskScheduler = new TaskScheduler();
        taskScheduler.setTaskId(args.getTaskId());
        taskScheduler.setTaskName(args.getTaskName());
        taskScheduler.setTaskGroupId(args.getTaskGroupId());
        taskScheduler.setInvokeTarget(args.getInvokeTarget());
        taskScheduler.setCronExpression(args.getCronExpression());
        taskScheduler.setMisfirePolicy(args.getMisfirePolicy());
        taskScheduler.setConcurrent(args.getConcurrent());
        taskScheduler.setStatus(args.getStatus());
        taskScheduler.setCreateTime(args.getCreateTime());

        taskScheduler.setUpdateTime(args.getUpdateTime());
        taskScheduler.setChangedBy(args.getChangedBy());
        taskScheduler.setRemark(args.getRemark());

        return taskScheduler;
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public IResult<Object> checkCronExpressionIsValid(String cronExpression) {
        boolean flag = CronExpressionUtil.isVailable(cronExpression);
        if (flag) {
            return SimpleResult.ok();
        } else {
            throw ServiceException.getInstance(CronExpressionStatus.Invalid);
        }
    }

    private TaskScheduler jobDetailsBeanToTaskScheduler(JobDetailsBean job) {
        TaskScheduler taskScheduler = new TaskScheduler();
        taskScheduler.setTaskName(job.getJobName());
        taskScheduler.setTaskId(job.getJobId());
        taskScheduler.setInvokeTarget(job.getInvokeTargetStr());
        taskScheduler.setCronExpression(job.getCronExpression());
        taskScheduler.setMisfirePolicy(Integer.valueOf(job.getMisfirePolicy()));
        taskScheduler.setConcurrent(Integer.valueOf(job.getConcurrent()));
        taskScheduler.setStatus(Integer.valueOf(job.getJobStatus()));
        taskScheduler.setCreateTime(new Date());
        return taskScheduler;
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