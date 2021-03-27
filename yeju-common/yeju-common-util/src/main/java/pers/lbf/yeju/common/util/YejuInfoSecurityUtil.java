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
package pers.lbf.yeju.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/21 0:01
 */
public class YejuInfoSecurityUtil {
    private YejuInfoSecurityUtil() {

    }
    

    /**
     * 保留前几位明文
     *
     * @param str   明文
     * @param start
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/21 0:18
     */
    public static String left(String str, int start) {

        if (StringUtils.isBlank(str)) {
            return "";
        }
        String name = StringUtils.left(str, start);
        return StringUtils.rightPad(name, StringUtils.length(str), "*");
    }

    /**
     * 保留前几位，后几位
     *
     * @param str   明文
     * @param start 前几位明文
     * @param end   后几位明文
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/21 0:18
     */
    public static String around(String str, int start, int end) {

        if (StringUtils.isBlank(str)) {
            return "";
        }
        return StringUtils.left(str, start).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(str, end), StringUtils.length(str), "*"), "***"));
    }


    /**
     * 保留后几位
     *
     * @param str 明文
     * @param end 最后几位 ？
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/21 0:17
     */
    public static String right(String str, int end) {

        if (StringUtils.isBlank(str)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(str, end), StringUtils.length(str), "*");
    }

}
