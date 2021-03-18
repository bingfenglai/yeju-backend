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

package pers.lbf.yeju.provider.auth.authz.receider;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAuthorizationService;
import pers.lbf.yeju.service.interfaces.auth.pojo.SynchronousAuthorizedCreateArgs;

import java.util.Map;

/**
 * 权限信息同步到账户-资源表消息接收者
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/14 11:32
 */
@Component
@Slf4j
public class AuthzSyncReceiver {

    @DubboReference
    private IAuthorizationService authorizationService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.authorization.queue.name}",
                    durable = "${spring.rabbitmq.listener.authorization.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.authorization.exchange.name}",
                    durable = "${spring.rabbitmq.listener.authorization.exchange.durable}",
                    type = "${spring.rabbitmq.listener.authorization.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.authorization.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.authorization.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload String jsonStringMessage,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {


        SynchronousAuthorizedCreateArgs args = (SynchronousAuthorizedCreateArgs) JSONObject.parse(jsonStringMessage);
        log.info("收到 授权同步 消息 ");
        authorizationService.synchronousAuthorizedToAccount(args);
        log.info("授权信息同步成功");

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }
}
