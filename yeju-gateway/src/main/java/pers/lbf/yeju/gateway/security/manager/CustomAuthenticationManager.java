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
package pers.lbf.yeju.gateway.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.gateway.security.enums.LoginWay;
import pers.lbf.yeju.gateway.security.pojo.AuthenticationToken;
import pers.lbf.yeju.gateway.security.service.CustomUserDetailsServiceImpl;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**登录对象身份验证管理器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 15:27
 */
@Component
public class CustomAuthenticationManager extends AbstractUserDetailsReactiveAuthenticationManager {

    private final Scheduler scheduler = Schedulers.boundedElastic();

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    /**自定义认证方法
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/19 14:42
     * @param authentication token
     * @return reactor.core.publisher.Mono<org.springframework.security.core.Authentication>
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication){


        AuthenticationToken token = (AuthenticationToken) authentication;
        String account = token.getName();
        String key = token.getVerificationCodeKey();
        String host = token.getHost();
        String code;
        if (token.getLoginWay().equals(LoginWay.usernameAndPassword)){
            code = token.getVerificationCode();
        }



        return null;
    }

    /**
     * 获取用户信息
     * @param username u
     * @return userDetails
     */
    @Override
    protected Mono<UserDetails> retrieveUser(String username) {
        return userDetailsService.findByUsername(username);
    }
}
