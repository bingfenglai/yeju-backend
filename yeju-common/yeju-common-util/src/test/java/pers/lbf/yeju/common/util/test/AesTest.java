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
import pers.lbf.yeju.common.util.AesUtils;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/5 16:35
 */
public class AesTest {

    @Test
    public void test() {
        String s = "welcome to use yeju system";
        try {
            String key = AesUtils.generateKey("444");
            String encryptData = AesUtils.encrypt(s, key);
            String newKey = AesUtils.generateKey("444");
            String decryptData = AesUtils.decrypt(encryptData, newKey);
            System.out.println("明文:  " + s);
            System.out.println("密文:  " + encryptData);
            System.out.println("解密后: " + decryptData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
