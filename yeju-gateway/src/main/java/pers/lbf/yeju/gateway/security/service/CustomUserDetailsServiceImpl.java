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
package pers.lbf.yeju.gateway.security.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**获取(更新)用户信息接口
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/10 23:05
 */
@Component
public class CustomUserDetailsServiceImpl implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {

    /**
     * @Description 获取用户详情信息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/14 15:30
     * @param s 用户账户
     * @return reactor.core.publisher.Mono<org.springframework.security.core.userdetails.UserDetails>
     */
    @Override
    public Mono<UserDetails> findByUsername(String s) {

        return null;
    }

    /**更新密码
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/14 15:29
     * @param userDetails 主体
     * @param s 密码
     * @return reactor.core.publisher.Mono<org.springframework.security.core.userdetails.UserDetails>
     */
    @Override
    public Mono<UserDetails> updatePassword(UserDetails userDetails, String s) {

        return null;
    }
}