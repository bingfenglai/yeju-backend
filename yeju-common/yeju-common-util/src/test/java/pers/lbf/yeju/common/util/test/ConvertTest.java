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
import pers.lbf.yeju.common.text.Convert;

import java.nio.charset.StandardCharsets;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/29 9:33
 */
public class ConvertTest {

    @Test
    public void test1(){
        byte[] bytes = "1,2,3".getBytes(StandardCharsets.UTF_8);
        String str = Convert.str(bytes, "");
        System.out.println(str);
    }
}
