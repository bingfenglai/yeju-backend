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
package pers.lbf.yeju.provider.message.notice.sender;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.base.mq.sender.BaseRabbitMQSender;
import pers.lbf.yeju.provider.message.notice.config.NoticeMqExchangeConfig;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/6 0:15
 */
@Component
@Slf4j
public class NoticeSender extends BaseRabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private NoticeMqExchangeConfig mqExchangeConfig;

    /**
     * 异步消息发送方法
     * 为了避免html格式问题 需要在发送前转为json字符串再进行发送
     *
     * @param message
     * @param properties
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/6 11:06
     */
    @Async
    public void send(Object message, Map<String, Object> properties) throws RuntimeException {

        super.rabbitTemplate = rabbitTemplate;
        log.info("发送前 {}", message.toString());
        String s = JSONObject.toJSONString(message);
        super.send(s, properties, mqExchangeConfig);
    }


}
