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
package pers.lbf.yeju.gateway.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.yeju.common.util.FileUtils;
import pers.lbf.yeju.common.util.JwtUtils;
import pers.lbf.yeju.common.util.RsaUtils;
import pers.lbf.yeju.gateway.security.constant.TokenConstant;
import pers.lbf.yeju.gateway.security.pojo.AuthorityInfoBean;
import pers.lbf.yeju.gateway.start.YejuGatewayApp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/13 17:54
 */
@SpringBootTest(classes = YejuGatewayApp.class)
@Slf4j
public class TokenTest {


    /** TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/24 0:46
     * @return void
     */
    @Test
    public void generateTestToken() {

        AuthorityInfoBean authorityInfoBean = new AuthorityInfoBean();
        authorityInfoBean.setPrincipal("969391");
        List<String> list = new ArrayList<>();
        list.add("*:**");
        authorityInfoBean.setAuthorityList(list);
        String token = "";
        try {
             token = JwtUtils.generateTokenExpireInMinutes(authorityInfoBean,
                    RsaUtils.getPrivateKey("privateKey.txt"), 60 * 24 * 180);
             log.info("access 1 generated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtils.writeFile("token", TokenConstant.getPrefixToken() +token);
    }


    public String test2(String token){

        return token;
    }
}
