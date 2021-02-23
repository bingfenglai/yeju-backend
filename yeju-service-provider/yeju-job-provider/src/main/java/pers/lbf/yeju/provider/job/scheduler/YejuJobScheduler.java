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
import pers.lbf.yeju.provider.job.constant.ScheduleConstants;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;

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

    /**
     * 根据job信息创建一个 作业 并且立即执行
     * 使用默认的触发器名和组
     *
     * @param jobInfoBean job info bean
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 0:27
     */
    public static void createOneJob(JobInfoBean jobInfoBean) throws SchedulerException {

        Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();

        //用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
        JobDetail jobDetail = JobBuilder.newJob(jobInfoBean.getInvokeTarget())
                .withIdentity(jobInfoBean.getJobName(),
                        jobInfoBean.getJobGroup())
                .build();
        jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, jobInfoBean);

        // 构建一个触发器，规定触发的规则
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TRIGGER_DEFAULT_NAME,
                        TRIGGER_DEFAULT_GROUP)
                .startNow() // 立即执行
                // 时间表达式
                .withSchedule(CronScheduleBuilder.cronSchedule(jobInfoBean.getCronExpression()))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        if (!scheduler.isStarted()) {
            scheduler.start();
        }

    }

    /**
     * TODO
     *
     * @param jobInfoBean
     * @param triggerNameGroup
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/23 16:54
     */
    public static void createOneJob(JobInfoBean jobInfoBean, String triggerNameGroup) throws SchedulerException {

    }


}
