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

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.start.YejuTradeProviderApplication;

import java.time.Duration;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/23 16:11
 */
@SpringBootTest(classes = YejuTradeProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class RedisLockTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void test() {
        Boolean flag = redisTemplate.opsForValue().setIfAbsent("laibf", 1, Duration.ofMinutes(2));

        log.info("第一次加锁" + flag);

        Boolean flag1 = redisTemplate.opsForValue().setIfAbsent("laibf", 1, Duration.ofMinutes(2));

        log.info("第二次加锁" + flag1);
    }
}
