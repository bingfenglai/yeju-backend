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
import pers.lbf.yeju.common.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/20 10:29
 */
public class TimeUtilsTest {

    @Test
    public void test() {
        Date tomorrow = TimeUtils.getTomorrow();

        System.out.println(tomorrow);
    }

    @Test
    public void test1() {
        Date futureTime = TimeUtils.getFutureTime(TimeUtils.MINUTE, 3);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(futureTime));
    }
}
