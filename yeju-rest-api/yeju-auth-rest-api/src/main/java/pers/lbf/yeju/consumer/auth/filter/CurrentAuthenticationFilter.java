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
package pers.lbf.yeju.consumer.auth.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/20 21:23
 */

public class CurrentAuthenticationFilter extends AuthenticationWebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        return super.filter(exchange, chain);
    }


    public Mono<Void> authenticate(ServerWebExchange exchange, WebFilterChain chain, Authentication token) {

        return Mono.empty();
    }





    @Override
    protected Mono<Void> onAuthenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        return super.onAuthenticationSuccess(authentication, webFilterExchange);
    }



    /**
     * Creates an instance
     *
     * @param authenticationManager the authentication manager to use
     */
    public CurrentAuthenticationFilter(ReactiveAuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * Creates an instance
     *
     * @param authenticationManagerResolver the authentication manager resolver to use
     * @since 5.2
     */
    public CurrentAuthenticationFilter(ReactiveAuthenticationManagerResolver<ServerHttpRequest> authenticationManagerResolver) {
        super(authenticationManagerResolver);
    }
}
