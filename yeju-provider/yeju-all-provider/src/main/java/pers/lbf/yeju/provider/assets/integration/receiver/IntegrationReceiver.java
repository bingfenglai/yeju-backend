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

package pers.lbf.yeju.provider.assets.integration.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 积分添加消息接收者
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/25 17:21
 */
@Slf4j
@Component
@EnableAsync
public class IntegrationReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.integration.add.queue.name}",
                    durable = "${spring.rabbitmq.listener.integration.add.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.integration.add.exchange.name}",
                    durable = "${spring.rabbitmq.listener.integration.add.exchange.durable}",
                    type = "${spring.rabbitmq.listener.integration.add.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.integration.add.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.integration.add.exchange.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload String jsonString,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {

        log.debug("积分服务 消费端 收到 积分服务添加 消息: ");
        log.debug("准备 入库");

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        log.debug("确认  入库");

        channel.basicAck(deliveryTag, false);
    }
}
