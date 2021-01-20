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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pers.lbf.yeju.common.util.FileUtils;
import pers.lbf.yeju.common.util.Rsa2Utils;

import java.util.Map;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/22 12:32
 */
public class Rsa2Test {

    private String privateKey;
    private String publicKey;

    @Before
    public void init() {
        Map<String, String> map = Rsa2Utils.initRSAKey(2048);
        Assert.assertNotNull(map.get("publicKey"));
        Assert.assertNotNull(map.get("privateKey"));
        FileUtils.writeFile("rsa2PublicKey.rsa",map.get("publicKey"));
        FileUtils.writeFile("rsa2PrivateKey.rsa",map.get("privateKey"));
        this.privateKey = Rsa2Utils.getPrivateKey();
        this.publicKey = Rsa2Utils.getPublicKey();
    }




    @Test
    public void test1(){
        String privateKey = Rsa2Utils.getPrivateKey();
        System.out.println(privateKey);

        String s = Rsa2Utils.buildRSAEncryptByPrivateKey("hehe", privateKey);
        System.out.println(s);
        String s1 = Rsa2Utils.buildRSADecryptByPublicKey(s, Rsa2Utils.getPublicKey());
        System.out.println(s1);
    }

    @Test
    public void test2() {
        String s = Rsa2Utils.buildRSAEncryptByPublicKey("hehe", publicKey);
        String s1 = Rsa2Utils.buildRSADecryptByPrivateKey(s,privateKey);
        System.out.println(s1);
    }

    @Test
    public void test3() {
        String s = Rsa2Utils.buildRSAEncryptByPrivateKey("hehe", privateKey);
        String s1 = Rsa2Utils.buildRSADecryptByPublicKey(s, publicKey);
        System.out.println(s1);
    }

    @Test
    public void test4() {
        String s = Rsa2Utils.buildRSASignByPrivateKey("hehe", privateKey);
        boolean flag = Rsa2Utils.buildRSAverifyByPublicKey("hehe", publicKey, s);
        System.out.println(flag);
    }


}
