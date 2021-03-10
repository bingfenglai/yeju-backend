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
package pers.lbf.yeju.base.mq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import pers.lbf.yeju.base.mq.config.BaseRabbitMqExchangeConfig;

import java.util.Map;
import java.util.UUID;

/**
 * 消息发送者抽象类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/6 17:49
 */
public abstract class BaseRabbitMQSender {

    private final Logger log = LoggerFactory.getLogger(BaseRabbitMQSender.class);

    protected RabbitTemplate rabbitTemplate;


    // 消息确认机制
    protected RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         * correlationData: 回调的相关数据，包含了消息ID
         * ack: ack结果，true代表ack，false代表nack
         * cause: 如果为nack，返回原因，否则为null
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            log.info("correlationData: {}", correlationData);
            log.info("isAck: {}", ack);
            if (!ack) {
                String id = correlationData.getId();
                log.info("消息 {} 处理失败", id);
            }
        }
    };


    // 消息返回机制
    RabbitTemplate.ReturnCallback returnCallback =
            (message, i, replyText, exchange, routingKey) -> {
                log.info("message: {}", message.toString());
                log.info("replyText: {}", replyText);
            };

    protected void send(Object message, Map<String, Object> properties, BaseRabbitMqExchangeConfig mqExchangeConfig) throws RuntimeException {
        if (mqExchangeConfig == null) {
            throw new RuntimeException("消息交换机配置不能为空");
        }

        MessageHeaders messageHeaders = new MessageHeaders(properties);
        //注意导包
        Message<Object> msg = MessageBuilder.createMessage(message, messageHeaders);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 ，保证全局唯一 ，这个是实际消息的ID
        //在做补偿性机制的时候通过ID来获取到这条消息进行重发
        String id = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(id);
        //exchange, routingKey, object, correlationData

        rabbitTemplate.convertAndSend(mqExchangeConfig.getName(), mqExchangeConfig.getKey(), msg, correlationData);
    }


}
