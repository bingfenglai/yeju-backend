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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.start.YejuTradeProviderApplication;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 22:08
 */
@SpringBootTest(classes = YejuTradeProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class MongoTest {

    @Autowired
    private MongoTemplate template;

    @Test
    public void test() {
        TestMongoInfo testMongoInfo = new TestMongoInfo();
        testMongoInfo.setUsername("王五");
        testMongoInfo.setGender("男");
        template.save(testMongoInfo);
        List<TestMongoInfo> all = template.findAll(TestMongoInfo.class);
        for (TestMongoInfo mongoInfo : all) {
            System.out.println(mongoInfo.toString());
        }
    }
}
