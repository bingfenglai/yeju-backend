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
package pers.lbf.yeju.provider.job.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.provider.job.BaseJob;
import pers.lbf.yeju.provider.job.bean.JobTriggerInfoBean;
import pers.lbf.yeju.provider.job.constant.ScheduleConstants;
import pers.lbf.yeju.provider.job.exception.TaskException;
import pers.lbf.yeju.provider.job.status.TaskExecutionStatus;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;

import java.util.LinkedList;
import java.util.List;

/**
 * 任务编排管理类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/22 21:14
 */
@Slf4j
public class YejuJobScheduler {

    public static final SchedulerFactory SCHEDULER_FACTORY = new StdSchedulerFactory();

    public static final String TRIGGER_DEFAULT_GROUP = "yeju_default_trigger_group";

    public static final String TRIGGER_DEFAULT_NAME = "yeju_default_trigger";

    public static final String JOB_GROUP = "yeju_default_job_group";

    /**
     * 根据job信息创建一个 作业
     * 使用默认的触发器名和组
     *
     * @param jobInfoBean job info bean
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 0:27
     */
    public static void createOneJob(JobInfoBean jobInfoBean) throws SchedulerException {
        JobTriggerInfoBean triggerInfoBean = new JobTriggerInfoBean();
        // 构建作业组的默认信息
        triggerInfoBean.setGroup(TRIGGER_DEFAULT_GROUP);
        triggerInfoBean.setName(TRIGGER_DEFAULT_NAME);
        createOneJob(jobInfoBean, triggerInfoBean);
    }

    /**
     * 根据作业信息和作业组信息创建一个任务
     *
     * @param jobInfoBean     作业信息
     * @param triggerInfoBean 作业组信息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 16:54
     */
    public static void createOneJob(JobInfoBean jobInfoBean, JobTriggerInfoBean triggerInfoBean) throws ServiceException {
        Scheduler scheduler = getScheduler();

        //用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
        JobDetail jobDetail = createJobDetail(jobInfoBean);

        // 此方式也可以用来注入任务所需参数值
        jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, jobInfoBean);

        // 构建一个触发器，规定触发的规则
        CronTrigger trigger = createTrigger(triggerInfoBean, jobInfoBean.getCronExpression());

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToCreateAScheduledTask);

        }

        try {
            if (!scheduler.isStarted() && jobInfoBean.isExecute()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToStartTheScheduledTask);
        }

    }


    /**
     * 修改任务的时间表达式
     * 使用默认组
     *
     * @param jobInfoBean
     * @param newCronExpression 新表达式
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 17:22
     */
    public static void modifyJobTime(JobInfoBean jobInfoBean, String newCronExpression) throws ServiceException {
        JobTriggerInfoBean triggerInfoBean = new JobTriggerInfoBean();
        triggerInfoBean.setName(TRIGGER_DEFAULT_NAME);
        triggerInfoBean.setGroup(TRIGGER_DEFAULT_GROUP);
        modifyJobTime(jobInfoBean, triggerInfoBean, newCronExpression);
    }

    /**
     * 修改任务的时间表达式
     *
     * @param jobInfoBean
     * @param triggerInfoBean   任务组属性
     * @param newCronExpression 新表达式
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:28
     */
    public static void modifyJobTime(JobInfoBean jobInfoBean, JobTriggerInfoBean triggerInfoBean, String newCronExpression) throws ServiceException {
        CronTrigger trigger = getTrigger(triggerInfoBean);
        String oldCronExpression = trigger.getCronExpression();

        if (!oldCronExpression.equals(newCronExpression)) {
            JobDetail jobDetail = getJobDetail(jobInfoBean.getJobName(), jobInfoBean.getJobGroup());

            if (jobDetail != null) {

                removeJob(jobInfoBean.getJobName(), jobInfoBean.getJobGroup(), triggerInfoBean.getName(), triggerInfoBean.getGroup());

            }

            Class objJobClass = jobDetail.getJobClass();

            jobInfoBean.setInvokeTarget(objJobClass);
            jobInfoBean.setJobStatus(ScheduleConstants.Status.PAUSE.getValue());

            createOneJob(jobInfoBean, triggerInfoBean);

        }


    }

    public static void removeJob(String jobName) throws ServiceException {
        removeJob(jobName, JOB_GROUP);
    }


    /**
     * 根据任务名移除任务
     *
     * @param jobName 任务名 具有唯一性
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 17:24
     */
    public static void removeJob(String jobName, String jobGroupName) throws ServiceException {
        removeJob(jobName, jobGroupName, TRIGGER_DEFAULT_NAME, TRIGGER_DEFAULT_GROUP);
    }

    public static void removeJob(String jobName, String jobGroupName,
                                 String triggerName, String triggerGroupName) throws ServiceException {

        Scheduler scheduler = getScheduler();

        JobKey jobKey = new JobKey(jobName, jobGroupName);
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);


        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToPauseJob);
        }
        try {
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedUnschedulerJob);

        }


        try {
            scheduler.deleteJob(jobKey);
            log.debug("移除定时任务 {} {} {} {}", jobName, jobGroupName, triggerName, triggerGroupName);
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToDeleteJob);
        }

    }


    /**
     * 启动当前节点所有任务
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 17:25
     */
    public static void startJobs() throws ServiceException {
        Scheduler scheduler = getScheduler();

        try {
            if (scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToStartTheScheduledTask);
        }
    }

    /**
     * 停止所有任务
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 17:26
     */
    public static void shutdownJobs() throws ServiceException {
        Scheduler scheduler = getScheduler();

        try {
            if (scheduler.isStarted()) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToShutdownTheScheduledTask);
        }

    }

    /**
     * 暂停一个默认任务组的任务
     *
     * @param jobName 任务名 具有唯一性
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 17:45
     */
    public static void pauseJob(String jobName) throws ServiceException {


        pauseJob(jobName, JOB_GROUP);
    }

    /**
     * 暂停任务
     *
     * @param jobName
     * @param jobGroup
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 20:59
     */
    public static void pauseJob(String jobName, String jobGroup) throws ServiceException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        Scheduler scheduler = getScheduler();
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToPauseJob);
        }
    }

    /**
     * 恢复默认作业组的任务
     *
     * @param jobName
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:01
     */
    public static void restoreJob(String jobName) throws ServiceException {

        restoreJob(jobName, JOB_GROUP);
    }

    /**
     * 恢复一个任务
     *
     * @param jobName
     * @param jobGroup
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:02
     */
    public static void restoreJob(String jobName, String jobGroup) throws ServiceException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        Scheduler scheduler = getScheduler();
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToRestoreJob);
        }
    }

    /**
     * 立即执行一个任务
     *
     * @param jobName
     * @param jobGroup
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:03
     */
    public static void runAJob(String jobName, String jobGroup) throws ServiceException {

        JobKey jobKey = new JobKey(jobName, jobGroup);
        Scheduler scheduler = getScheduler();
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToStartTheScheduledTask);
        }


    }

    /**
     * 立即执行默认任务组的一个任务
     *
     * @param jobName
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:05
     */
    public static void runAJob(String jobName) throws ServiceException {

        runAJob(jobName, JOB_GROUP);
    }

    /**
     * 获取正在执行的任务
     *
     * @return java.util.List<pers.lbf.yeju.provider.job.BaseJob>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:07
     */
    public static List<BaseJob> getAllJobInfosExecuted() throws ServiceException {
        List<BaseJob> jobList = new LinkedList<>();

        return jobList;
    }


    /**
     * 获取调度器
     *
     * @param
     * @return org.quartz.Scheduler
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:37
     */
    private static Scheduler getScheduler() throws ServiceException {

        Scheduler scheduler;
        try {
            scheduler = SCHEDULER_FACTORY.getScheduler();
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToGetScheduler);
        }

        return scheduler;
    }

    /**
     * 创建一个作业
     *
     * @param jobInfoBean 作业信息封装类
     * @return org.quartz.JobDetail
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:37
     */
    private static JobDetail createJobDetail(JobInfoBean jobInfoBean) throws ServiceException {

        return JobBuilder.newJob(jobInfoBean.getInvokeTarget())
                .withIdentity(jobInfoBean.getJobName(),
                        jobInfoBean.getJobGroup())
                .build();
    }

    /**
     * 构建任务触发器 立即启动
     *
     * @param triggerInfoBean 触发器属性类
     * @param cronExpression  时间表达式
     * @return org.quartz.Trigger
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:45
     */
    public static CronTrigger createTriggerAndStart(JobTriggerInfoBean triggerInfoBean, String cronExpression) {

        return TriggerBuilder.newTrigger()
                .withIdentity(triggerInfoBean.getName(),
                        triggerInfoBean.getGroup())
                .startNow() // 立即执行
                // 时间表达式
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }

    /**
     * 构建任务触发器 不触发
     *
     * @param triggerInfoBean 触发器属性类
     * @param cronExpression  时间表达式
     * @return org.quartz.Trigger
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 21:45
     */
    public static CronTrigger createTrigger(JobTriggerInfoBean triggerInfoBean, String cronExpression) {

        return TriggerBuilder.newTrigger()
                .withIdentity(triggerInfoBean.getName(),
                        triggerInfoBean.getGroup())
                // 时间表达式
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }

    /**
     * 根据相应属性获取触发器
     *
     * @param triggerInfoBean 触发器属性封装类
     * @return org.quartz.CronTrigger
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 22:01
     */
    private static CronTrigger getTrigger(JobTriggerInfoBean triggerInfoBean) throws ServiceException {

        TriggerKey triggerKey = new TriggerKey(triggerInfoBean.getName(), triggerInfoBean.getGroup());
        try {
            return (CronTrigger) getScheduler().getTrigger(triggerKey);
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToGetTrigger);
        }


    }

    private static JobDetail getJobDetail(String jobName, String jobGroup) throws ServiceException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        JobDetail jobDetail;
        try {
            jobDetail = getScheduler().getJobDetail(jobKey);
            return jobDetail;
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToGetJobDetail);
        }

    }


}
