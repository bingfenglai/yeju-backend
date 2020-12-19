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
package pers.lbf.yeju.gateway.security.pojo;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import pers.lbf.yeju.gateway.security.enums.LoginWay;

import java.util.Collection;

/**存储登录对象信息的token
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 15:07
 */
public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    /**
     * web登录的验证码
     */
    private String verificationCode;

    /**
     * 验证码在缓存中的key
     */
    private String verificationCodeKey;

    /**
     * IP地址
     */
    private String host;

    /**
     * 登录方式
     */
    private LoginWay loginWay;



    public AuthenticationToken(Object principal, Object credentials, String host, String verificationCodeKey, String verificationCode){
        super(principal, credentials);
        this.verificationCode = verificationCode;
        this.host = host;
        this.verificationCodeKey = verificationCodeKey;
        loginWay = LoginWay.usernameAndPassword;
    }

    public AuthenticationToken(Object principal, Object credentials,String host,String verificationCodeKey){
        super(principal, credentials);
        this.host = host;
        this.loginWay = LoginWay.phoneNumberAndVerificationCode;
        this.verificationCodeKey = verificationCodeKey;
    }


    public AuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public String getVerificationCode() {

        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationCodeKey() {
        return verificationCodeKey;
    }

    public void setVerificationCodeKey(String verificationCodeKey) {
        this.verificationCodeKey = verificationCodeKey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public LoginWay getLoginWay() {
        return loginWay;
    }

    public void setLoginWay(LoginWay loginWay) {
        this.loginWay = loginWay;
    }
}
