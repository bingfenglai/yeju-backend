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
package pers.lbf.yeju.consumer.auth.util;

import org.springframework.http.server.reactive.ServerHttpRequest;
import pers.lbf.yeju.consumer.auth.enums.AccessType;

import java.util.Objects;

/**
 * 访问方式工具类，获取访问对象是通过浏览器访问还是APP访问
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/28 18:40
 */
public class AccessTypeUtil {

    private AccessTypeUtil() {

    }

    public static String accessType(ServerHttpRequest request) {
        String userAgent = Objects.requireNonNull(request.getHeaders().get("User-Agent")).get(0);
        if (AccessType.yejuApp.getUserAgent().equals(userAgent)) {
            return AccessType.yejuApp.getValue();
        } else {
            return AccessType.browser.getValue();
        }
    }
}
