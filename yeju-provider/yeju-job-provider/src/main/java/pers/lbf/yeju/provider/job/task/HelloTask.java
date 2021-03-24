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
package pers.lbf.yeju.provider.job.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import pers.lbf.yeju.provider.job.BaseJob;

import java.util.Date;

/**
 * 测试任务类
 * DisallowConcurrentExecution 该注解作用禁止同一个任务实例并发执行
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/22 21:10
 */
@Slf4j
@DisallowConcurrentExecution
public class HelloTask extends BaseJob {

    @Override
    protected void doExecute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String name = "";
        if (jobDataMap != null) {
            name = (String) jobDataMap.get("name");
        }
        log.info("当前时间 {} hello world! {}", new Date(), name);
    }
}
