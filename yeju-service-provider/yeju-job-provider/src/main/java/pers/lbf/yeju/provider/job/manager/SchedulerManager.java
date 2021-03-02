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
package pers.lbf.yeju.provider.job.manager;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.provider.job.scheduler.YejuBaseScheduler;
import pers.lbf.yeju.service.interfaces.job.ITaskSchedulerService;
import pers.lbf.yeju.service.interfaces.job.pojo.JobDetailsBean;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * 程序调度器管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/24 18:02
 */
@Component
public class SchedulerManager {

    @DubboReference
    private ITaskSchedulerService jobSchedulerService;

    @Autowired
    private JobManager jobManager;

    /**
     * 此方法将于系统启动初始化时执行一次
     * 将业务数据库当中的定时任务加载到定时任务上下文，
     * 并根据相应配置 创建启动定时任务
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/27 20:48
     */
    @PostConstruct
    private void init() {
        List<JobDetailsBean> jobDetailsBeanList = jobSchedulerService.findAll().getData();
        for (JobDetailsBean jobDetailsBean : jobDetailsBeanList) {
            jobDetailsBean.setExecute(Objects.equals(jobDetailsBean.getJobStatus(), "1"));
            jobManager.createJob(jobDetailsBean,
                    jobDetailsBean.getTriggerInfoBeanList().get(0), jobDetailsBean.getJobProperties());
        }
    }

    public void startScheduler() throws ServiceException {
        YejuBaseScheduler.startJobScheduler();
    }

    public void shutdownScheduler() throws ServiceException {
        YejuBaseScheduler.shutdownJobScheduler();
    }
}
