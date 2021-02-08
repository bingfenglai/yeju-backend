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
package pers.laibf.yeju.common.core.test;

import org.junit.Assert;
import org.junit.Test;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.SimpleResult;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/8 11:39
 */
public class ResultBeanTest {

    @Test
    public void test1(){
        IResult<Object> result = SimpleResult.ok();

        Assert.assertEquals(result.isSuccess(),true);

        IResult<Object> result1 = SimpleResult.error();
        Assert.assertEquals(result1.isSuccess(),false);

    }
}
