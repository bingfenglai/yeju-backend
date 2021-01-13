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
package pers.lbf.yeju.provider.currency.log.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.logserver.interfaces.ILoginLogService;
import pers.lbf.yeju.logserver.interfaces.dto.AddLoginLogDTO;

import java.util.Map;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/3 22:37
 */
@Component
@Slf4j
public class LoginLogRabbitReceiver {

    @Autowired
    private ILoginLogService loginLogService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.login-log.queue-name}",
                    durable="${spring.rabbitmq.listener.login-log.queue-durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.login-log.exchange-name}",
                    durable="${spring.rabbitmq.listener.login-log.exchange-durable}",
                    type= "${spring.rabbitmq.listener.login-log.exchange-type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.login-log.exchange-ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.login-log.key}"
    )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload AddLoginLogDTO loginDTO,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {

        log.info("登录日志服务 消费端 收到 登录日志 消息: {}",loginDTO.getAccount());
        log.info("准备 {} 入库", loginDTO.getAccount());
        loginLogService.addLog(loginDTO);
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        log.info("确认 {} 入库",loginDTO.getAccount());
        channel.basicAck(deliveryTag, false);
    }
}
