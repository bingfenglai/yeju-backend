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

package pers.lbf.yeju.consumer.authorization.sender;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.base.mq.sender.BaseRabbitMQSender;
import pers.lbf.yeju.base.mq.sender.Sender;
import pers.lbf.yeju.consumer.authorization.config.AuthorizedSyncRabbitMqExchangeConfig;

/**
 * 同步权限信息消息发送者
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/11 23:33
 */
@Sender
public class SynchronousAuthorizedToAccountSender extends BaseRabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AuthorizedSyncRabbitMqExchangeConfig authorizedSyncRabbitMqExchangeConfig;

    public void send(Object message) {
        super.rabbitTemplate = rabbitTemplate;
        String s = JSONObject.toJSONString(message);
        super.send(s, null, authorizedSyncRabbitMqExchangeConfig);
    }
}
