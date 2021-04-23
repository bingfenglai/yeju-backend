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

package pers.lbf.yeju.provider.trade.sender;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import pers.lbf.yeju.base.mq.sender.BaseRabbitMQSender;
import pers.lbf.yeju.base.mq.sender.Sender;
import pers.lbf.yeju.provider.trade.config.TradeMqExchangeConfig;

/**
 * 房源状态更改消息发送者
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 16:41
 */
@Sender
@Slf4j
public class TradingSender extends BaseRabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TradeMqExchangeConfig config;

    @Async
    public void send(Object message) throws RuntimeException {
        String jsonMsg = JSONObject.toJSONString(message);
        super.send(jsonMsg, null, config);
        
    }


}
