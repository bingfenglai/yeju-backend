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
package pers.lbf.yeju.common.core.constant;

import java.io.Serializable;

/**
 * 令牌常量
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/22 13:31
 */
public class TokenConstant implements Serializable {

    /**
     * 令牌前缀
     */
    private static String prefixToken = "yeju_";

    /**
     * 分割符
     */
    private static String splitString = "_";

    public static final String TOKEN_KEY = "Authorization";

    /**
     * 默认的会话过期时间 30分钟
     */
    public static final Integer DEFAULT_SESSION_EXPIRES_MINUTE_AT = 30;
    
    /**
     * app 令牌过期时间 单位 秒
     */
    public static final Long AppTokenExpiresAt = 60 * 60 * 7L;

    public static final Long PcTokenExpiresAt = 60 * 10L;

    public static String getSplitString() {
        return splitString;
    }

    public static void setSplitString(String splitString) {
        TokenConstant.splitString = splitString;
    }

    public static String getPrefixToken() {
        return prefixToken;
    }

    public static void setPrefixToken(String prefixToken) {
        TokenConstant.prefixToken = prefixToken;
    }
}
