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

package pers.lbf.yeju.provider.mail.revicer;

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
import pers.lbf.yeju.service.interfaces.mail.IMailService;
import pers.lbf.yeju.service.interfaces.mail.pojo.SimpleMailArgs;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/26 16:06
 */
@Component
@Slf4j
@EnableAsync
public class EmailMessageReceiver {

    @Autowired
    private IMailService mailService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.email.queue.name}",
                    durable = "${spring.rabbitmq.listener.email.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.email.exchange.name}",
                    durable = "${spring.rabbitmq.listener.email.exchange.durable}",
                    type = "${spring.rabbitmq.listener.email.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.email.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.email.exchange.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload String jsonMsg,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {

        log.info("收到邮件消息 {}", jsonMsg);
        JSONObject jsonObject = JSONObject.parseObject(jsonMsg);
        
        SimpleMailArgs args = new SimpleMailArgs();
        args.setType(jsonObject.getString("type"));
        args.setContent(jsonObject.getString("content"));
        args.setSubject(jsonObject.getString("subject"));
        args.setSendTo(jsonObject.getString("sendTo"));
        mailService.send(args);

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }


}
