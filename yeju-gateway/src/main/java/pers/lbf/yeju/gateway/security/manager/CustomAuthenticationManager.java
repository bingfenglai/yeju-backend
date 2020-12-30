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

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.authrestapi.interfaces.interfaces.IVerificationCodeService;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.gateway.config.VerificationCodeConfig;
import pers.lbf.yeju.gateway.exception.GatewayException;
import pers.lbf.yeju.gateway.security.enums.LoginWay;
import pers.lbf.yeju.gateway.security.pojo.AuthenticationToken;
import pers.lbf.yeju.gateway.security.pojo.AuthorityInfo;
import pers.lbf.yeju.gateway.security.pojo.LoginRequestToken;
import pers.lbf.yeju.gateway.security.service.CustomUserDetailsServiceImpl;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

/**登录对象身份验证管理器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 15:27
 */
@Component
@Slf4j
public class CustomAuthenticationManager extends AbstractUserDetailsReactiveAuthenticationManager {

    private final Scheduler scheduler = Schedulers.boundedElastic();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private VerificationCodeConfig verificationCodeConfig;

    @DubboReference
    private IVerificationCodeService verificationCodeService;

    public CustomAuthenticationManager(CustomUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
        super.setUserDetailsPasswordService(userDetailsService);
        super.setPasswordEncoder(this.passwordEncoder);

    }

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


        LoginRequestToken loginToken = (LoginRequestToken) authentication;
        String account = loginToken.getName();
        String key = loginToken.getVerificationCodeKey();
        String host = loginToken.getHost();
        String credentials = (String) loginToken.getCredentials();

        if (loginToken.getLoginWay().equals(LoginWay.usernameAndPassword)) {

            if (verificationCodeConfig.isEnable()) {
              String code = loginToken.getVerificationCode();
              IResult<Boolean> result = verificationCodeService.verify(key, code);

              if (!result.getData()) {
                  throw new GatewayException(AuthStatusEnum.verificationCodeError);
              }
            }


            Mono<UserDetails> userDetailsMono = retrieveUser(account);
            UserDetails userDetails = userDetailsMono.block();


            assert userDetails != null;
            boolean flag = passwordEncoder.matches(credentials, userDetails.getPassword());

            //密码不匹配
            if (!flag) {
                throw new GatewayException(AuthStatusEnum.NO_ACCOUNT);
            }

            //验证账户是否可用
            if (!userDetails.isEnabled()){
                throw new GatewayException(AuthStatusEnum.accountIsNotActivated);
            }
            if (!userDetails.isAccountNonExpired()){
                throw new GatewayException(AuthStatusEnum.accountHasExpired);
            }
            if (!userDetails.isAccountNonLocked()){
                throw new GatewayException(AuthStatusEnum.accountIsFrozen);
            }

            log.info("开始对用户 {} 授权",userDetails.getUsername());
            Mono<AuthorityInfo> authorityInfoMono = getAuthorityInfo(account);



            AuthorityInfo info = authorityInfoMono.block(Duration.ofSeconds(3));
            AuthenticationToken authenticationToken = new AuthenticationToken(info, credentials);
            authenticationToken.setDetails(info);

          return Mono.just(authenticationToken);
        }

        if (loginToken.getLoginWay().equals(LoginWay.phoneNumberAndVerificationCode)){

            IResult<Boolean> result = verificationCodeService.verify(account, credentials);

//            if (!result.getData()){
//                throw new GatewayException(AuthStatus.verificationCodeError);
//            }


            Mono<AuthorityInfo> authorityInfoMono = getAuthorityInfo(account);

            AuthorityInfo authorityInfo = authorityInfoMono.block();

            AuthenticationToken authenticationToken = new AuthenticationToken(authorityInfo, credentials);

            return Mono.just(authenticationToken);
        }

        return Mono.error(ServiceException::new);

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


    /**
     * 获取授权信息体
     * @param username
     * @return
     */
    private Mono<AuthorityInfo> getAuthorityInfo(String username){
        Mono<UserDetails> userDetailsMono = userDetailsService.findByUsername(username);

        UserDetails userDetails = userDetailsMono.block(Duration.ofSeconds(3));

        assert userDetails != null;
        AuthorityInfo info = getAuthorityInfoByUserDetails(userDetails);

        return Mono.just(info);



    }

    private AuthorityInfo getAuthorityInfoByUserDetails(UserDetails userDetails){
        AuthorityInfo info = new AuthorityInfo();

        info.setPrincipal(userDetails.getUsername());

        Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();
        if (grantedAuthorities != null&& grantedAuthorities.size() > 0) {
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
