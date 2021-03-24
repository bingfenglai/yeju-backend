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
package pers.lbf.yeju.consumer.auth.enums;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/28 18:29
 */
public enum AccessType {

    /**
     * 手机app访问
     */
    yejuApp("yeju-mobile-app", "mb"),

    browser(null, "pc");

    private final String userAgent;

    private final String value;

    AccessType(String userAgent, String value) {
        this.userAgent = userAgent;
        this.value = value;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getValue() {
        return value;
    }
}
