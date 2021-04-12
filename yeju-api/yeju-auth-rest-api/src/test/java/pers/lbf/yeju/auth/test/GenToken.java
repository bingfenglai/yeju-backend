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
package pers.lbf.yeju.auth.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.util.FileUtils;
import pers.lbf.yeju.consumer.auth.manager.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.auth.start.YejuAuthConsumerApplication;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/3 9:05
 */
@SpringBootTest(classes = YejuAuthConsumerApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class GenToken {
    @Autowired
    private AuthorizationTokenManager tokenManager;

    @Test
    public void test1() {
        AuthorityInfoBean authorityInfoBean = new AuthorityInfoBean();
        authorityInfoBean.setSessionId("9693911614612442339");
        authorityInfoBean.setPrincipal(String.valueOf(969392));
        authorityInfoBean.setTimeUnit(TimeUnit.DAYS);
        authorityInfoBean.setExpiration(666);
        authorityInfoBean.setAuthorityList(Collections.singletonList("*:**"));

        try {
            String token = tokenManager.getBuilder(authorityInfoBean, 666L)
                    .build();
            log.info(token);
            FileUtils.writeFile("E:\\graduation project\\yeju_code\\yeju_dev\\token", token);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
