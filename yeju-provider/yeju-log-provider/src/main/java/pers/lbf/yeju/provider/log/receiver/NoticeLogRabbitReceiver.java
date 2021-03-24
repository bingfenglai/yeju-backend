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
package pers.lbf.yeju.provider.log.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.service.interfaces.log.IMessageDeliveryLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.MessageDeliveryLogCreateArgs;

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
public class NoticeLogRabbitReceiver {

    @Autowired
    private IMessageDeliveryLogService messageDeliveryLogService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.notice.log.queue.name}",
                    durable = "${spring.rabbitmq.listener.notice.log.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.notice.log.exchange.name}",
                    durable = "${spring.rabbitmq.listener.notice.log.exchange.durable}",
                    type = "${spring.rabbitmq.listener.notice.log.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.notice.log.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.notice.log.exchange.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload MessageDeliveryLogCreateArgs args,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {

        log.debug("消息投递日志服务 消费端 收到 消息投递日志 消息: {}", args.getMessageId());
        log.debug("准备 {} 入库", args.getMessageId());
        messageDeliveryLogService.addOneLog(args);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        log.debug("确认 {} 入库", args.getMessageId());
        channel.basicAck(deliveryTag, false);
    }
}
