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
package pers.lbf.yeju.consumer.auth.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.consumer.auth.config.SessionMqExchangeConfig;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/6 18:02
 */
@Component
public class SessionInitSender extends BaseRabbitMQSender{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SessionMqExchangeConfig mqExchangeConfig;


    public void send(Object message, Map<String, Object> properties) throws RuntimeException {
        super.rabbitTemplate = this.rabbitTemplate;
        super.send(message, properties, mqExchangeConfig);
    }
}
