package pers.lbf.yeju.provider.account;/*
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

import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.provider.account.service.AccountServiceImpl;
import pers.lbf.yeju.provider.start.YejuCurrencyServiceProviderApplication;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleAccountDTO;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/17 9:50
 */
@SpringBootTest(classes = YejuCurrencyServiceProviderApplication.class)
public class AccountServiceTest {


    private final IAccountService accountService = new AccountServiceImpl();



    //@Test
    public void test1(){
        IResult<SimpleAccountDTO> hehe = accountService.findSimpleAccountByPrincipal("969391");
        System.out.println(hehe.toString());
    }
}
