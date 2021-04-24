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
package pers.lbf.yeju.provider.start;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import pers.lbf.yeju.common.util.TimeUtils;
import pers.lbf.yeju.provider.job.scheduler.YejuBaseScheduler;
import pers.lbf.yeju.provider.job.scheduler.YejuJobScheduler;
import pers.lbf.yeju.provider.job.task.HelloTask;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;
import pers.lbf.yeju.service.interfaces.job.pojo.JobTriggerInfoBean;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 20:07
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.provider")
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "pers.lbf.yeju.provider.job.service")
@MapperScan(basePackages = {"pers.lbf.yeju.provider.**.dao"})
@Slf4j
public class YejuJobProviderApplication {


    public static void main(String[] args) {
        SpringApplication.run(YejuJobProviderApplication.class, args);

        log.info("############################################");
        log.info("#-------    定时任务服务启动成功！      --------#");
        log.info("############################################");

    }

    @PostConstruct
    private void task1() {
        JobInfoBean infoBean = new JobInfoBean();
        infoBean.setJobName("测试任务");
        infoBean.setJobGroup("yeju_job_default_group");
        infoBean.setCronExpression("0/15 * * * * ?");
        infoBean.setInvokeTargetStr(HelloTask.class.getName());
        infoBean.setExecute(true);
        Properties properties = new Properties();
        properties.put("name", "王礼");
        infoBean.setJobProperties(properties);
        JobTriggerInfoBean triggerInfoBean = new JobTriggerInfoBean();
        triggerInfoBean.setGroup(YejuBaseScheduler.TRIGGER_DEFAULT_GROUP);
        triggerInfoBean.setName(YejuBaseScheduler.TRIGGER_DEFAULT_NAME);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date futureTime = TimeUtils.getFutureTime(TimeUtils.MINUTE, 1);

        triggerInfoBean.setStartTime(futureTime);
        YejuJobScheduler.createOneJob(infoBean, triggerInfoBean, properties);
        YejuJobScheduler.startJobScheduler();


        //YejuJobScheduler.runAJob(infoBean.getJobName(), infoBean.getJobGroup());
        //YejuJobScheduler.runJob(infoBean, triggerInfoBean);
    }

}
