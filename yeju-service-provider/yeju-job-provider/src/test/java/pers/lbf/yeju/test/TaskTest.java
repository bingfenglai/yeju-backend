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
package pers.lbf.yeju.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.job.scheduler.YejuJobScheduler;
import pers.lbf.yeju.provider.job.task.HelloTask;
import pers.lbf.yeju.provider.start.YejuJobProviderApplication;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;
import pers.lbf.yeju.service.interfaces.job.pojo.JobTriggerInfoBean;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/23 23:27
 */
@SpringBootTest(classes = YejuJobProviderApplication.class)
@RunWith(SpringRunner.class)
public class TaskTest {

    private static final JobInfoBean jobInfoBean;

    private static final JobTriggerInfoBean triggerInfoBean;

    static {
        jobInfoBean = new JobInfoBean();
        jobInfoBean.setJobName("yeju_task");
        jobInfoBean.setJobGroup("yeju_job_default_group");
        jobInfoBean.setCronExpression("0/3 * * * * ?");
        jobInfoBean.setInvokeTargetStr(HelloTask.class.getName());
        jobInfoBean.setExecute(false);

        triggerInfoBean = new JobTriggerInfoBean();
        triggerInfoBean.setName("yeju_trigger");
        triggerInfoBean.setGroup("yeju_trigger_group");

    }

    @Test
    public void test1() {
        try {
            YejuJobScheduler.createOneJob(jobInfoBean, triggerInfoBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        YejuJobScheduler.pauseJob(jobInfoBean.getJobName(), jobInfoBean.getJobGroup());

        YejuJobScheduler.restoreJob(jobInfoBean.getJobName(), jobInfoBean.getJobGroup());

        YejuJobScheduler.modifyJobTime(jobInfoBean, triggerInfoBean, "10/1 * * * * ? ");

        YejuJobScheduler.runAJob(jobInfoBean.getJobName(), jobInfoBean.getJobGroup());


    }

    @Test
    public void test2() {
        //YejuJobScheduler.runAJob(jobInfoBean.getJobName());
    }


    public static void main(String[] args) {
        System.out.println(HelloTask.class.getName());
    }
}
