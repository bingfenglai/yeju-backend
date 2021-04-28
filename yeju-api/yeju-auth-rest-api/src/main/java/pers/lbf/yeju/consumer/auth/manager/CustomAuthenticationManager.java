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


import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.auth.config.VerificationCodeConfig;
import pers.lbf.yeju.consumer.auth.enums.LoginWay;
import pers.lbf.yeju.consumer.auth.pojo.AuthenticationToken;
import pers.lbf.yeju.consumer.auth.pojo.LoginRequestToken;
import pers.lbf.yeju.consumer.auth.service.AsyncLoginLogService;
import pers.lbf.yeju.consumer.auth.service.CustomUserDetailsServiceImpl;
import pers.lbf.yeju.service.interfaces.log.pojo.AddLoginLogRequestBean;
import pers.lbf.yeju.service.interfaces.verificationcode.IVerificationCodeService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 登录对象身份验证管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 15:27
 */
@Component
@EnableAsync
@Primary
public class CustomAuthenticationManager extends AbstractUserDetailsReactiveAuthenticationManager {
    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationManager.class);
    private final Scheduler scheduler = Schedulers.boundedElastic();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private final CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private VerificationCodeConfig verificationCodeConfig;

    @Autowired
    private AsyncLoginLogService loginLogService;

    @DubboReference
    private IVerificationCodeService verificationCodeService;

    public CustomAuthenticationManager(CustomUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
        super.setUserDetailsPasswordService(userDetailsService);
        super.setPasswordEncoder(this.passwordEncoder);

    }

    /**
     * 自定义认证方法 认证逻辑
     *
     * @param authentication 1
     * @return reactor.core.publisher.Mono<org.springframework.security.core.Authentication>
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/19 14:42
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {


        LoginRequestToken loginToken = (LoginRequestToken) authentication;
        String account = loginToken.getName();
        String key = loginToken.getVerificationCodeKey();
        String host = loginToken.getHost();
        String credentials = (String) loginToken.getCredentials();


        AddLoginLogRequestBean addLoginLogRequestBean = new AddLoginLogRequestBean();
        addLoginLogRequestBean.setAccount(account);
        addLoginLogRequestBean.setSubjectName("");
        addLoginLogRequestBean.setIp(host);
        addLoginLogRequestBean.setAccentTime(new Date());


        if (loginToken.getLoginWay().equals(LoginWay.usernameAndPassword)) {
            log.debug("登录方式为账号+密码");
            // 开启验证码校验
            if (verificationCodeConfig.isEnable()) {
                log.debug("验证码校验已开启");
                String code = loginToken.getVerificationCode();
                IResult<Boolean> result = verificationCodeService.verify(key, code);

                if (!result.getData()) {
                    addLoginLogRequestBean.setLoginStatus(0);
                    addLoginLogRequestBean.setMessage(AuthStatusEnum.verificationCodeError.getMessage());
                    loginLogService.addLog(addLoginLogRequestBean);
                    throw new ServiceException(AuthStatusEnum.verificationCodeError);
                }
            }


            Mono<UserDetails> userDetailsMono = retrieveUser(account);
            UserDetails userDetails = userDetailsMono.block(Duration.ofSeconds(3));


            assert userDetails != null : new ServiceException(AuthStatusEnum.NO_ACCOUNT);


            boolean flag = passwordEncoder.matches(credentials, userDetails.getPassword());

            //密码不匹配
            if (!flag) {
                addLoginLogRequestBean.setLoginStatus(0);
                addLoginLogRequestBean.setMessage(AuthStatusEnum.NO_ACCOUNT.getMessage());
                loginLogService.addLog(addLoginLogRequestBean);
                throw new ServiceException(AuthStatusEnum.NO_ACCOUNT);
            }

            //验证账户是否可用
            if (!userDetails.isEnabled()) {
                addLoginLogRequestBean.setLoginStatus(0);
                addLoginLogRequestBean.setMessage(AuthStatusEnum.accountIsNotActivated.getMessage());
                loginLogService.addLog(addLoginLogRequestBean);
                throw new ServiceException(AuthStatusEnum.accountIsNotActivated);
            }
            if (!userDetails.isAccountNonExpired()) {
                addLoginLogRequestBean.setLoginStatus(0);
                addLoginLogRequestBean.setMessage(AuthStatusEnum.accountHasExpired.getMessage());
                loginLogService.addLog(addLoginLogRequestBean);
                throw new ServiceException(AuthStatusEnum.accountHasExpired);
            }
            if (!userDetails.isAccountNonLocked()) {
                addLoginLogRequestBean.setLoginStatus(0);
                addLoginLogRequestBean.setMessage(AuthStatusEnum.accountIsFrozen.getMessage());
                loginLogService.addLog(addLoginLogRequestBean);
                throw new ServiceException(AuthStatusEnum.accountIsFrozen);
            }

            log.info("开始对用户 {} 授权", userDetails.getUsername());
            Mono<AuthorityInfoBean> authorityInfoMono = getAuthorityInfo(account);


            AuthorityInfoBean info = authorityInfoMono.block(Duration.ofSeconds(3));
            AuthenticationToken authenticationToken = new AuthenticationToken(info, credentials);
            authenticationToken.setDetails(info);

            addLoginLogRequestBean.setLoginStatus(1);
            addLoginLogRequestBean.setMessage("登录成功");
            loginLogService.addLog(addLoginLogRequestBean);
            return Mono.just(authenticationToken);


        }

        if (loginToken.getLoginWay().equals(LoginWay.phoneNumberAndVerificationCode)) {
            log.debug("手机号验证码登录");
            IResult<Boolean> result = verificationCodeService.verify(key, credentials);

            if (!result.getData()) {
                throw new ServiceException(AuthStatusEnum.verificationCodeError);
            }


            Mono<AuthorityInfoBean> authorityInfoMono = getAuthorityInfo(account);

            AuthorityInfoBean authorityInfoBean = authorityInfoMono.block();

            AuthenticationToken authenticationToken = new AuthenticationToken(authorityInfoBean, credentials);

            return Mono.just(authenticationToken);
        }

        return Mono.error(ServiceException::new);

    }


    /**
     * 获取用户信息
     *
     * @param username u
     * @return userDetails
     */
    @Override
    protected Mono<UserDetails> retrieveUser(String username) {
        return userDetailsService.findByUsername(username);
    }

    /**
     * 获取授权信息体
     *
     * @param username
     * @return
     */
    private Mono<AuthorityInfoBean> getAuthorityInfo(String username) {
        Mono<UserDetails> userDetailsMono = userDetailsService.findByUsername(username);

        UserDetails userDetails = userDetailsMono.block(Duration.ofSeconds(3));

        assert userDetails != null;
        AuthorityInfoBean info = getAuthorityInfoByUserDetails(userDetails);

        return Mono.just(info);


    }

    private AuthorityInfoBean getAuthorityInfoByUserDetails(UserDetails userDetails) {
        AuthorityInfoBean info = new AuthorityInfoBean();

        info.setPrincipal(userDetails.getUsername());

        Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();
        if (grantedAuthorities != null && grantedAuthorities.size() > 0) {
            ArrayList<String> authorities = new ArrayList<>(grantedAuthorities.size());
            for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                String authority = grantedAuthority.getAuthority();
                authorities.add(authority);
            }
            info.setAuthorityList(authorities);
        }

        return info;

    }


}
