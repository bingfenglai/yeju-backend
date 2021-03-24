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
package pers.lbf.yeju.consumer.auth.manager;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.consumer.auth.builder.OnlineInfoBeanBuilder;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/20 22:02
 */

public class OnlineManager {

    public static OnlineInfoBeanBuilder getOnlineInfoBeanBuilder(String principal, ServerWebExchange webExchange){
        ServerHttpRequest request = webExchange.getRequest();
        return getOnlineInfoBeanBuilder(principal,request);
    }

    public static OnlineInfoBeanBuilder getOnlineInfoBeanBuilder(String principal, ServerHttpRequest request){
        OnlineInfoBeanBuilder builder = new OnlineInfoBeanBuilder();
        builder.setPrincipal(principal);
        builder.setRequest(request);
        return builder;
    }

}
