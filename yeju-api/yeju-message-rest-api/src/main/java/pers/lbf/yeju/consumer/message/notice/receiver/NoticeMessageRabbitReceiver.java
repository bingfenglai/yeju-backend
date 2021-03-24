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
package pers.lbf.yeju.consumer.message.notice.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.consumer.message.notice.web.SystemNoticeWebsocketHandler;
import pers.lbf.yeju.service.interfaces.message.MessageDeliver;

import java.util.Map;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/3 22:37
 */
@Component
@Slf4j
@EnableAsync
public class NoticeMessageRabbitReceiver {

    @Autowired
    private SystemNoticeWebsocketHandler noticeHandler;

    @Autowired
    private MessageDeliver messageDeliverManager;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "${spring.rabbitmq.listener.notice.exchange.name}",
                    durable = "${spring.rabbitmq.listener.notice.exchange.durable}",
                    type = "${spring.rabbitmq.listener.notice.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.notice.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.notice.exchange.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload String msg,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {

        log.info("收到消息 {}", msg);


        messageDeliverManager.delive(msg);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);


    }
}
