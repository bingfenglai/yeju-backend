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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.yeju.consumer.authrestapi.start.AuthRestApi;
import pers.lbf.yeju.consumer.base.log.message.sender.OperationLogMessageSender;
import pers.lbf.yeju.logserver.interfaces.dto.AddOperationLogDTO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/13 21:52
 */
@SpringBootTest(classes = AuthRestApi.class)
public class MqTest {

    @Autowired
    private OperationLogMessageSender sender;

    @Test
    public void testMessage(){
        AddOperationLogDTO logDTO = new AddOperationLogDTO();
        logDTO.setTitle("hehe");
        sender.send(logDTO,null);
    }
}
