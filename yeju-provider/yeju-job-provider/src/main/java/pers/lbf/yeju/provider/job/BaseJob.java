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
package pers.lbf.yeju.provider.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.provider.base.util.SpringContextUtils;
import pers.lbf.yeju.provider.job.constant.ScheduleConstants;
import pers.lbf.yeju.provider.job.constant.TaskExecutionConstants;
import pers.lbf.yeju.provider.job.exception.TaskException;
import pers.lbf.yeju.provider.job.sender.TaskLogSender;
import pers.lbf.yeju.provider.job.status.TaskExecutionStatus;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;
import pers.lbf.yeju.service.interfaces.log.pojo.TaskLogCreateArgs;

import java.util.Date;

/**
 * 基础任务类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/22 20:54
 */
@Slf4j
public abstract class BaseJob implements Job {
    /**
     * 本地线程变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    private TaskLogSender logSender;

    @Override
    public void execute(JobExecutionContext context) throws ServiceException {
        JobInfoBean jobInfoBean = (JobInfoBean) context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES);
        TaskLogCreateArgs args = this.jobInfoToLogArgs(jobInfoBean);

        beforeExecute();
        try {

            doExecute(context);

        } catch (Exception e) {
            log.error(String.valueOf(e));
            args.setTaskLog(e.getMessage());
            args.setTaskStatus(TaskExecutionConstants.FAILED);
            throw TaskException.getInstance(e.getMessage(), TaskExecutionStatus.UNKNOWN.getCode());
        } finally {
            afterExecute(args);
        }
    }

    public void beforeExecute() {
        if (logSender == null) {
            try {
                logSender = SpringContextUtils.getBean(TaskLogSender.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Date startTime = new Date();
        threadLocal.set(startTime);
        log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        log.debug("任务开始执行时间：{}", startTime);
    }

    public void afterExecute(TaskLogCreateArgs args) throws ServiceException {
        Date stopTime = new Date();
        Date startTime = threadLocal.get();
        log.debug("任务执行结束时间：{}", stopTime);
        long time = startTime.getTime();
        long time1 = stopTime.getTime();
        long t = time1 - time;
        log.debug("任务耗时：{}", t);
        log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        args.setStartTime(startTime);
        args.setStopTime(stopTime);

        logSender.send(args, null);
    }

    protected abstract void doExecute(JobExecutionContext context) throws ServiceException;

    private TaskLogCreateArgs jobInfoToLogArgs(JobInfoBean bean) {

        TaskLogCreateArgs taskLogCreateArgs = new TaskLogCreateArgs();
        taskLogCreateArgs.setTaskName(bean.getJobName());
        taskLogCreateArgs.setTaskGroup(bean.getJobGroup());
        taskLogCreateArgs.setInvokeTarget(bean.getInvokeTargetStr());
        taskLogCreateArgs.setExceptionInfo(bean.getCronExpression());
        taskLogCreateArgs.setCreateTime(bean.getCreatedDate());
        return taskLogCreateArgs;

    }


}
