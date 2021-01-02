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
package pers.lbf.yeju.test.mq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.yeju.provider.mqserver.start.MqServer;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/2 13:58
 */
@SpringBootTest(classes = MqServer.class)
public class MqServerTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void test1(){
       amqpTemplate.convertSendAndReceive("hehe","你好");
    }

//    @RabbitListener(queues = "hehe")
//    static class Listener{
//
//        @RabbitHandler
//        public void handler(String message){
//            System.out.println(message);
//        }
//    }
}
