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
package pers.lbf.yeju.common.util.test;

import org.junit.Test;
import pers.lbf.yeju.common.pojo.Payload;
import pers.lbf.yeju.common.util.JwtUtils;
import pers.lbf.yeju.common.util.RsaUtils;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/1 11:53
 */
public class JwtTest {

    @Test
    public void test() {
        User user = new User();
        user.setAge("18");
        user.setName("里斯");

        try {
            String token = JwtUtils.generateTokenExpireInMinutes(user, RsaUtils.getPrivateKey("RSAPrivateKey.rsa"), 3);

            Payload<User> payload = JwtUtils.getInfoFromToken(token, RsaUtils.getPublicKey("RSAPublicKey.rsa"),User.class);
            if (payload.getUserInfo()==null){
                System.out.println(payload.toString());
                System.out.println("======null");
            }else {
                System.out.println("!!!!!!!!!!!!!!!!!!");
                System.out.println(payload.getUserInfo().toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
