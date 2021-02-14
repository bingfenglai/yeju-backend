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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.service.interfaces.log.IOperationLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.AddOperationLogRequestBean;

import java.util.Map;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/3 22:37
 */
@Component
@Slf4j
public class OperationLogRabbitReceiver {

    @Autowired
    @Qualifier(value = "operationLogService")
    private IOperationLogService operationLogService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.operation.log.queue.name}",
                    durable="${spring.rabbitmq.listener.operation.log.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.operation.log.exchange.name}",
                    durable="${spring.rabbitmq.listener.operation.log.exchange.durable}",
                    type= "${spring.rabbitmq.listener.operation.log.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.operation.log.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.operation.log.exchange.key}"
    )
    )
    @RabbitHandler
    public void messageHandler(@Payload AddOperationLogRequestBean operateDTO,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {

        log.info("操作日志服务 消费端 收到 操作日志 消息: {}",operateDTO.getIp());
        log.info("准备 {} 入库", operateDTO.getIp());
        operationLogService.addOperationLog(operateDTO);
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        log.info("确认 {} 入库",operateDTO.getIp());
        channel.basicAck(deliveryTag, false);
    }
}
