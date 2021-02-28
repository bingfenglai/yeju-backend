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

import org.quartz.JobExecutionContext;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.provider.job.BaseJob;

/**
 * session列表定时推送至消息队列的任务，每30秒执行一次
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/28 15:57
 */
public class SessionInfoPushTask extends BaseJob {
    

    @Override
    protected void doExecute(JobExecutionContext context) throws ServiceException {

    }
}
