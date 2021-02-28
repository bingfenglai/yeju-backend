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
package pers.lbf.yeju.gateway.message.operationlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.base.mq.sender.BaseRabbitMQSender;
import pers.lbf.yeju.gateway.config.OperationLogMqExchangeConfig;

import java.util.Map;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/13 20:18
 */
@Component
@Slf4j
public class OperationLogMessageSender extends BaseRabbitMQSender {

    @Autowired
    private OperationLogMqExchangeConfig mqConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息发送方法
     *
     * @param message    消息对象
     * @param properties 消息信息存储结构
     * @return void
     * @throws RuntimeException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/13 20:19
     */
    public void send(Object message, Map<String, Object> properties) throws RuntimeException {
        super.rabbitTemplate = rabbitTemplate;
        super.send(message, properties, mqConfig);
    }

}
