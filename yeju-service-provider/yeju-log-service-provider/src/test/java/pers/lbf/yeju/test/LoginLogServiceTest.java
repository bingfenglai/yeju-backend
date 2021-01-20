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
package pers.lbf.yeju.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.provider.log.start.LogApplication;
import pers.lbf.yeju.service.interfaces.log.ILoginLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.LoginLogInfoBean;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/19 10:06
 */
@SpringBootTest(classes = LogApplication.class)
@Slf4j
public class LoginLogServiceTest {

    @DubboReference
    private ILoginLogService loginLogService;

    @Test
    public void testPageHelper(){
        PageResult<LoginLogInfoBean> pageResult = loginLogService.findList(1L,10L);
        log.info(pageResult.toString());
        log.info(String.valueOf(pageResult.getList().size()));
}
}
