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

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.provider.job.scheduler.YejuJobScheduler;
import pers.lbf.yeju.provider.job.util.CronExpressionUtil;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;
import pers.lbf.yeju.service.interfaces.job.pojo.JobTriggerInfoBean;

import java.util.Properties;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/24 18:06
 */
@Component
@Slf4j
public class JobManager {


    /**
     * 创建无参定时任务
     *
     * @param jobInfoBean
     * @param triggerInfoBean
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/2 17:40
     */
    public void createJob(JobInfoBean jobInfoBean, JobTriggerInfoBean triggerInfoBean) throws ServiceException {

        YejuJobScheduler.createOneJob(jobInfoBean, triggerInfoBean);
    }

    /**
     * 创建有参定时任务
     *
     * @param jobInfoBean
     * @param triggerInfoBean
     * @param jobProperties
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/2 17:41
     */
    public void createJob(JobInfoBean jobInfoBean, JobTriggerInfoBean triggerInfoBean, Properties jobProperties) throws ServiceException {
        YejuJobScheduler.createOneJob(jobInfoBean, triggerInfoBean, jobProperties);
    }


    public void runJob(JobInfoBean jobInfoBean) throws ServiceException {
        if (jobInfoBean.getJobName() != null && jobInfoBean.getJobGroup() != null) {
            YejuJobScheduler.runAJob(jobInfoBean.getJobName(), jobInfoBean.getJobGroup());
        } else {
            throw ServiceException.getInstance("任务名称和任务组名不能为空",
                    ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

    }

    public void pauseJob(JobInfoBean jobInfoBean) throws ServiceException {
        YejuJobScheduler.pauseJob(jobInfoBean.getJobName(), jobInfoBean.getJobGroup());
    }

    public void resumeJob(JobInfoBean jobInfoBean) throws ServiceException {
        YejuJobScheduler.restoreJob(jobInfoBean.getJobName(), jobInfoBean.getJobGroup());
    }

    public void removeJob(JobInfoBean jobInfoBean, JobTriggerInfoBean triggerInfoBean) throws ServiceException {
        YejuJobScheduler.removeJob(jobInfoBean.getJobGroup(), jobInfoBean.getJobGroup(), triggerInfoBean.getName(), triggerInfoBean.getGroup());
    }

    public void modifyJobTime(JobInfoBean jobInfoBean, JobTriggerInfoBean triggerInfoBean, String newCronExpression) throws ServiceException {
        Assert.notNull(jobInfoBean.getJobName(), "job name is not be null");
        Assert.notNull(jobInfoBean.getJobGroup(), "job name is not be null");
        Assert.notNull(triggerInfoBean.getName(), "trigger name is not be null");
        Assert.notNull(triggerInfoBean.getGroup(), "trigger group is not be null");
        Assert.notNull(newCronExpression, "new cron expression cannot be null");

        if (!CronExpressionUtil.isVailable(newCronExpression)) {
            throw new ServiceException("cron表达式无效", ParameStatusEnum.invalidParameter.getCode());
        }

        YejuJobScheduler.modifyJobTime(jobInfoBean, triggerInfoBean, newCronExpression);
    }


}
