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
package pers.lbf.yeju.provider.auth.session;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.auth.start.YejuAuthProviderApplication;
import pers.lbf.yeju.service.interfaces.auth.dto.SessionDetails;
import pers.lbf.yeju.service.interfaces.auth.interfaces.ISessionService;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/8 17:13
 */
@SpringBootTest(classes = YejuAuthProviderApplication.class)
@RunWith(SpringRunner.class)
public class SessionTest {

    @DubboReference
    private ISessionService sessionService;

    private String account = "969391";


    @Test
    public void test1(){
        SessionDetails sessionDetails = sessionService.initSession(account);
        System.out.println(sessionDetails.toString());
    }
}
