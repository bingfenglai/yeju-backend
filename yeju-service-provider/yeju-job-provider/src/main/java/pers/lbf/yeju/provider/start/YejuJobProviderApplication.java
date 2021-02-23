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
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import pers.lbf.yeju.provider.job.scheduler.YejuJobScheduler;
import pers.lbf.yeju.provider.job.task.HelloTask;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 20:07
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.provider")
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "pers.lbf.yeju.provider.**.service")
@MapperScan(basePackages = {"pers.lbf.yeju.provider.**.dao"})
@Slf4j
public class YejuJobProviderApplication {


    public static void main(String[] args) {
        SpringApplication.run(YejuJobProviderApplication.class, args);

        log.info("############################################");
        log.info("#---------   定时任务启动成功！      ----------#");
        log.info("############################################");

    }

    @PostConstruct
    public void task1() {
        JobInfoBean infoBean = new JobInfoBean();
        infoBean.setJobName("测试任务");
        infoBean.setJobGroup("yeju_job_default_group");
        infoBean.setCronExpression("0/3 * * * * ?");
        infoBean.setInvokeTarget(HelloTask.class);
        try {
            YejuJobScheduler.createOneJob(infoBean);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
}
