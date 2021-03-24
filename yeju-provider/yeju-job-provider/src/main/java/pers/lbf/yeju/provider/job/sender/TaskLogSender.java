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
package pers.lbf.yeju.provider.job.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.base.mq.sender.BaseRabbitMQSender;
import pers.lbf.yeju.provider.job.config.JobLogExchangeConfig;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/28 9:13
 */
@Component
@Slf4j
public class TaskLogSender extends BaseRabbitMQSender {

    @Autowired
    private JobLogExchangeConfig config;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(Object message, Map<String, Object> properties) throws RuntimeException {
        log.debug("发送任务调度日志");
        super.rabbitTemplate = rabbitTemplate;
        super.send(message, properties, config);
    }
}
