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
package pers.lbf.yeju.consumer.auth.service;


import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.consumer.auth.pojo.SubjectDetails;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleAccountDTO;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * 获取(更新)用户信息接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/10 23:05
 */
@Component
public class CustomUserDetailsServiceImpl implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    @DubboReference
    private IAccountService accountService;

    /**
     * @param s 用户账户
     * @return reactor.core.publisher.Mono<org.springframework.security.core.userdetails.UserDetails>
     * @Description 获取用户详情信息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/14 15:30
     */
    @Override
    public Mono<UserDetails> findByUsername(String s) throws ServiceException {

        boolean flag = YejuStringUtils.isEmpty(s);

        assert !flag : ServiceException.getInstance(
                AuthStatusEnum.account_cannot_be_empty.getMessage(),
                AuthStatusEnum.account_cannot_be_empty.getCode());

        IResult<SimpleAccountDTO> result = accountService.findSimpleAccountByPrincipal(s);
        SimpleAccountDTO simpleAccountDTO = result.getData();
        SubjectDetails userDetails = new SubjectDetails();

        ArrayList<SimpleGrantedAuthority> subAuthorities = new ArrayList<SimpleGrantedAuthority>();
        for (String s1 : simpleAccountDTO.getAuthorityStringList()) {
            subAuthorities.add(new SimpleGrantedAuthority(s1));
        }

        userDetails.setPrincipal(s);
        userDetails.setCertificate(simpleAccountDTO.getCertificate());
        userDetails.setAccountStatus(simpleAccountDTO.getAccountStatus());
        userDetails.setAccountType(simpleAccountDTO.getAccountType());
        userDetails.setAuthorities(subAuthorities);
        return Mono.just(userDetails);
    }


    /**
     * 更新密码
     *
     * @param userDetails 主体
     * @param s           密码
     * @return reactor.core.publisher.Mono<org.springframework.security.core.userdetails.UserDetails>
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/14 15:29
     */
    @Override
    public Mono<UserDetails> updatePassword(UserDetails userDetails, String s) {
        log.info("新密码：{}", s);
        return null;
    }
}
