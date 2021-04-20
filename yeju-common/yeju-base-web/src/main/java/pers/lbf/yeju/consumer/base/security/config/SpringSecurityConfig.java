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
package pers.lbf.yeju.consumer.base.security.config;

import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import pers.lbf.yeju.base.security.authorization.config.IgnoreWhiteProperties;
import pers.lbf.yeju.base.security.authorization.manager.CustomAuthorizationManager;

/**
 * 资源服务器安全配置
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/31 0:28
 */
@EnableWebFluxSecurity
@Configuration
@Primary
public class SpringSecurityConfig {

    private final Logger log = LoggerFactory.getLogger(SpringSecurityConfig.class);

    @Autowired
    private IgnoreWhiteProperties ignoreWhiteProperties;

    @Autowired
    private CustomAuthorizationManager authorizationManager;

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

        SecurityWebFilterChain chain = http
                .authorizeExchange()
                //无需进行权限过滤的请求路径
                .pathMatchers(ignoreWhiteProperties.getWhiteArrays()).permitAll()
                //option 请求默认放行
                .pathMatchers(String.valueOf(HttpMethod.OPTIONS)).permitAll()
                //访问鉴权
                .anyExchange().access(authorizationManager)
                .and()
                //认证路径
                .formLogin().loginPage("/auth/login")

                //关闭security默认的跨域请求保护,自定义实现
                .and().csrf().disable()
                .httpBasic().disable()
                .build();

        return chain;
    }

}
