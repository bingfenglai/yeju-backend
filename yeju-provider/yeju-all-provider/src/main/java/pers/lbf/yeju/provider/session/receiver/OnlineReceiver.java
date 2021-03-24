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
package pers.lbf.yeju.provider.session.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.service.interfaces.session.ISessionService;
import pers.lbf.yeju.service.interfaces.session.pojo.OnlineInfoBean;

import java.util.Map;

/**
 * session 初始化消息接收者
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/6 18:18
 */
@Component
@Slf4j
public class OnlineReceiver {

    @Autowired
    @Qualifier(value = "sessionService")
    private ISessionService sessionService;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.online.queue.name}",
                    durable = "${spring.rabbitmq.listener.online.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.online.exchange.name}",
                    durable = "${spring.rabbitmq.listener.online.exchange.durable}",
                    type = "${spring.rabbitmq.listener.online.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.online.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.online.exchange.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload OnlineInfoBean onlineInfoBean,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {

        log.info("登录online服务 消费端 收到 会话 消息: {}", onlineInfoBean.getSessionId());
        log.info("准备 online {}", onlineInfoBean.getSessionId());
        sessionService.addOnline(onlineInfoBean);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        log.info(" 添加 online {} 成功", onlineInfoBean.getSessionId());
        channel.basicAck(deliveryTag, false);
    }


}
