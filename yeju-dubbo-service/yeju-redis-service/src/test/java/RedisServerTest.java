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

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import pers.lbf.yeju.redis.service.interfaces.IRedisService;
import pers.lbf.yeju.redisServer.start.RedisServer;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/4 16:57
 */

@SpringBootTest(classes = RedisServer.class)
public class RedisServerTest {

    @DubboReference
    private IRedisService redisService;


    @Test
    public void test1(){

        try {
            redisService.addCacheObject("name","zhangming");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Object name = redisService.getCacheObject("name");
            System.out.println(name.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        try {
            redisService.addCacheMapValue("1","a","b");
            redisService.addCacheMapValue("1","b","c");
            Object a = redisService.getCacheMapValue("1", "a");
            System.out.println(a.toString());
            Assert.state(a.toString().equals("b"),"failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
