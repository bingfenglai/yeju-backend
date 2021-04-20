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

package pers.lbf.yeju.provider.test.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.start.YejuProviderApplication;
import pers.lbf.yeju.service.interfaces.message.privated.IPrivateMessageService;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/9 16:38
 */
@SpringBootTest(classes = YejuProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class PrivateMessageTest {

    @DubboReference
    private IPrivateMessageService privateMessageService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void test() {
//        String key = "yeju:message:private:unread:2:1380439006257795073";
//        String data = privateMessageService.pullMessage(key).getData();
//
//        log.info("消息 {}", data);
//        Assert.assertNotNull(data);
    }

    @Test
    public void test1() {
        String key = "hshshsh";
        redisTemplate.opsForValue().set(key, key);
        Boolean aBoolean = redisTemplate.hasKey(key);
        log.info("flag {}", aBoolean);
    }

    @Test
    public void test2() {
        List<String> data = privateMessageService.pullMessageByAccountId(2L).getData();
        log.info(data.toString());
    }


}
