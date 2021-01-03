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
package pers.lbf.yeju.gateway.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.gateway.config.LoginLogMqExchangeConfig;

import java.util.Map;
import java.util.UUID;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/3 22:49
 */
@Component
@Slf4j
public class LoginLogSender {

    @Autowired
    private LoginLogMqExchangeConfig mqConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //确认机制
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         * correlationData: 回调的相关数据，包含了消息ID
         * ack: ack结果，true代表ack，false代表nack
         * cause: 如果为nack，返回原因，否则为null
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            System.err.println("ack: " + ack);
            if(!ack){
                //做一些补偿机制等
                System.err.println("异常处理....");

            }
        }
    };

  // 返回机制
  final RabbitTemplate.ReturnCallback returnCallback =
      (message, i, replyText, exchange, routingKey) -> {
        log.info("message: {}",message.toString());
        log.info("replyText: {}",replyText);
      };

    //发送消息方法调用: 构建Message消息
    public void send(Object message, Map<String, Object> properties) throws RuntimeException {
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
        assert mqConfig !=null;
        System.out.println(mqConfig.toString());
        rabbitTemplate.convertAndSend(mqConfig.getName(), mqConfig.getKey(), msg, correlationData);
    }

}
