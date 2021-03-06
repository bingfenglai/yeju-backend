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
import org.junit.Test;
import pers.lbf.yeju.common.util.Md5Utils;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/1 14:12
 */
public class Md5Test {

    @Test
    public void testEncode() {
        String s1 = Md5Utils.encode("haha");

        String s2 = Md5Utils.encode("haha");

        Assert.assertEquals(s1, s2);

    }

    @Test
    public void testEncode2() {
        String filePath = "E:\\graduation project\\yeju_code\\yeju_dev\\yeju-common\\yeju-common-util\\rsa2PrivateKey.rsa";
        String s1 = Md5Utils.getFileMD5ByFilename(filePath);

        String s2 = Md5Utils.getFileMD5ByFilename("E:\\graduation project\\yeju_code\\yeju_dev\\yeju-common\\yeju-common-util\\rsa2PrivateKey.rsa");
        Assert.assertEquals(s1, s2);

    }

    @Test
    public void testEncode3() {
        //String s = Md5Utils.getFileMD5ByFilename("E:\\Pictures\\liuyf\\1 - 副本.png");
        //Assert.assertEquals(s,"39e8edf4eba284afe1cb47add95e8543");
    }
}
