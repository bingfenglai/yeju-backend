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
package pers.lbf.yeju.consumer.product.house.sender;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.base.mq.sender.BaseRabbitMQSender;
import pers.lbf.yeju.consumer.product.house.config.HouseCheckLogExchangeConfig;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/7 12:31
 */
@Component
public class HouseCheckLogSender extends BaseRabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private HouseCheckLogExchangeConfig exchangeConfig;


    public void send(Object message) throws RuntimeException {
        super.rabbitTemplate = this.rabbitTemplate;
        String s = JSONObject.toJSONString(message);
        super.send(s, null, exchangeConfig);
    }

}
