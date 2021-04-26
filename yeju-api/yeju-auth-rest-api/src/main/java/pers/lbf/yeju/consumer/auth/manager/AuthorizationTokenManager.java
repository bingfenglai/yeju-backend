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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.consumer.auth.builder.AuthorityInfoTokenBuilder;


/**
 * 认证令牌管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/12 23:45
 */
@Component
@Slf4j
@Primary
public class AuthorizationTokenManager extends pers.lbf.yeju.base.security.authorization.manager.AuthorizationTokenManager {

    @Autowired
    private AuthorityInfoTokenBuilder builder;

    public AuthorityInfoTokenBuilder getBuilder(AuthorityInfoBean authorityInfoBean, Long expireAt) {
        builder.setAuthorityInfo(authorityInfoBean);
        builder.setExpireAt(expireAt);
        return builder;
    }

    public AuthorityInfoTokenBuilder getBuilder(AuthorityInfoBean authorityInfoBean, ServerWebExchange exchange) {
        Long expireAt = this.getTokenExpiresTime(exchange);
        builder.setAuthorityInfo(authorityInfoBean);
        builder.setExpireAt(expireAt);
        return builder;
    }

    public Long getTokenExpiresTime(ServerWebExchange exchange) {

        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        String userAgent = requestHeaders.getFirst("yeju-client-yeju-agent");
        if ("yeju-client".equalsIgnoreCase(userAgent)) {
            return TokenConstant.AppTokenExpiresAt;
        } else {
            return TokenConstant.PcTokenExpiresAt;
        }

    }
}
