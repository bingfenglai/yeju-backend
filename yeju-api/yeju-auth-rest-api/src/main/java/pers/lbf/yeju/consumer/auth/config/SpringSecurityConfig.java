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
package pers.lbf.yeju.consumer.auth.config;

import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import pers.lbf.yeju.base.security.authorization.config.IgnoreWhiteProperties;
import pers.lbf.yeju.base.security.authorization.manager.CustomAuthorizationManager;
import pers.lbf.yeju.consumer.auth.converter.CustomServerFormLoginAuthenticationConverter;
import pers.lbf.yeju.consumer.auth.handler.AuthenticationFailHandler;
import pers.lbf.yeju.consumer.auth.handler.AuthenticationSuccessHandler;
import pers.lbf.yeju.consumer.auth.handler.CustomServerLogoutHandler;
import pers.lbf.yeju.consumer.auth.handler.CustomServerLogoutSuccessHandler;
import pers.lbf.yeju.consumer.auth.manager.CustomAuthenticationManager;

import java.util.LinkedList;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/10 21:25
 */
@EnableWebFluxSecurity
@Configuration
@Primary
public class SpringSecurityConfig {

    private final Logger log = LoggerFactory.getLogger(SpringSecurityConfig.class);

    /**
     * 放行白名单
     */
    @Autowired
    private IgnoreWhiteProperties ignoreWhiteProperties;

    /**
     * 认证成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 认证失败处理器
     */
    @Autowired
    private AuthenticationFailHandler authenticationFailHandler;

    /**
     * 登录参数接收转换器
     */
    @Autowired
    private CustomServerFormLoginAuthenticationConverter authenticationConverter;

    /**
     * 鉴权管理器
     */
    @Autowired
    private CustomAuthorizationManager authorizationManager;

    /**
     * 认证管理器
     */

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    /**
     * 登出处理器
     */
    @Autowired
    private CustomServerLogoutHandler logoutHandler;

    /**
     * 登出成功处理器
     */
    @Autowired
    private CustomServerLogoutSuccessHandler logoutSuccessHandler;


    /**
     * 构建过滤器链
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        log.info("业务网关配置白名单：{}", ignoreWhiteProperties.getWhites().toString());


        String[] whites = ignoreWhiteProperties.getWhites().toArray(new String[0]);

        SecurityWebFilterChain chain = http
                .authorizeExchange()
                //无需进行权限过滤的请求路径
                .pathMatchers(ignoreWhiteProperties.getWhites().toArray(new String[0])).permitAll()
                //option 请求默认放行
                .pathMatchers(String.valueOf(HttpMethod.OPTIONS)).permitAll()
                //访问鉴权
                .anyExchange().access(authorizationManager)
                .and()
                //认证路径
                .formLogin().loginPage("/auth/login")
                //认证管理器
                .authenticationManager(authenticationManager)
                //认证成功
                .authenticationSuccessHandler(authenticationSuccessHandler)
                //登陆验证失败
                .authenticationFailureHandler(authenticationFailHandler)
                .and()
                //登出
                .logout().logoutUrl("/auth/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .logoutHandler(logoutHandler)
                //关闭跨域请求保护
                .and().csrf().disable()
                .httpBasic().disable()
                .build();


        http.addFilterAt(getAuthenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
        // 设置自定义登录参数转换器
        chain.getWebFilters()
                .filter(webFilter -> webFilter instanceof AuthenticationWebFilter)
                .subscribe(webFilter -> {
                    AuthenticationWebFilter filter = (AuthenticationWebFilter) webFilter;
                    filter.setServerAuthenticationConverter(authenticationConverter);
                });

        return chain;
    }

    /**
     * 注册用户信息验证管理器，可按需求添加多个按顺序执行
     *
     * @return ReactiveAuthenticationManager
     */
    //@Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();

        managers.add(authenticationManager);
        return new DelegatingReactiveAuthenticationManager(managers);
    }

    @Bean
    public AuthenticationWebFilter getAuthenticationWebFilter() {
        return new AuthenticationWebFilter(authenticationManager);
    }
}
