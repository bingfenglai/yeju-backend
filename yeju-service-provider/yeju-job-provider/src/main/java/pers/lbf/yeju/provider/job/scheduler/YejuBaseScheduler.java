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
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.provider.job.exception.TaskException;
import pers.lbf.yeju.provider.job.status.TaskExecutionStatus;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/24 9:48
 */
@Slf4j
public class YejuBaseScheduler {
    public static final SchedulerFactory SCHEDULER_FACTORY = new StdSchedulerFactory();

    public static final String TRIGGER_DEFAULT_GROUP = "yeju_default_trigger_group";

    public static final String TRIGGER_DEFAULT_NAME = "yeju_default_trigger";

    public static final String JOB_GROUP = "yeju_default_job_group";

    public static Scheduler scheduler;

    static {
        try {
            scheduler = SCHEDULER_FACTORY.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        
    }


    /**
     * 启动当前节点任务调度程序
     * 所有执行了scheduler.scheduleJob(jobDetail, trigger)方法的定时任务
     * 都将被执行
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 17:25
     */
    public static void startJobScheduler() throws ServiceException {

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
     * 关闭调度器 当前节点任务将被停止
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 17:26
     */
    public static void shutdownJobScheduler() throws ServiceException {

        try {
            if (scheduler.isStarted()) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            log.error(String.valueOf(e));
            throw TaskException.getInstance(TaskExecutionStatus.FailedToShutdownTheScheduledTask);
        }

    }

}
