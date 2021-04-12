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

package pers.lbf.yeju.consumer.message.privated.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.service.interfaces.message.MessageDeliver;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/7 11:05
 */
@Component
@Slf4j
public class PrivateMessageRabbitReceiver {

    @Autowired
    @Qualifier("privateMessageDeliveryManager")
    private MessageDeliver messageDeliver;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "${spring.rabbitmq.listener.private-message.exchange.name}",
                    durable = "${spring.rabbitmq.listener.private-message.exchange.durable}",
                    type = "${spring.rabbitmq.listener.private-message.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.private-message.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.private-message.exchange.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload String keyMsg,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {

        log.info("收到消息 {}", keyMsg);

        messageDeliver.delive(keyMsg);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);


    }
}
