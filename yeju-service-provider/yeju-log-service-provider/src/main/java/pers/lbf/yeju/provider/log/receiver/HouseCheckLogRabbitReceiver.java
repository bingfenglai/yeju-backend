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

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.service.interfaces.log.IHouseCheckLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.HouseCheckLogCreateArgs;

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
public class HouseCheckLogRabbitReceiver {

    @Autowired
    private IHouseCheckLogService houseCheckLogService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.house-check.log.queue.name}",
                    durable = "${spring.rabbitmq.listener.house-check.log.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.house-check.log.exchange.name}",
                    durable = "${spring.rabbitmq.listener.house-check.log.exchange.durable}",
                    type = "${spring.rabbitmq.listener.house-check.log.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.house-check.log.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.house-check.log.exchange.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload String jsonString,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {
        HouseCheckLogCreateArgs log = (HouseCheckLogCreateArgs) JSONObject.parse(jsonString);
        houseCheckLogService.add(log);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }
}
