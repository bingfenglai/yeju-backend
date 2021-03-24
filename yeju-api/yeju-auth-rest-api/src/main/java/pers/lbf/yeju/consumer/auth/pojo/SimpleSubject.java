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
package pers.lbf.yeju.consumer.auth.pojo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * 简单账户对象
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/12 19:13
 */
public class SimpleSubject implements Serializable {

    /**
     * 抽象账号，可以是手机、系统账号、邮箱等
     */
    private String principal;

    /**
     * 访问凭证，可以是密码、授权码等
     */

    private String certificate;

    /**
     * 账户类型
     */
    private SubjectTypeEnum accountType;

    /**
     * 权限集合
     */
    protected List<SimpleGrantedAuthority> authorities;

    private String accountStatus;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }


    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public SubjectTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(SubjectTypeEnum accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "SimpleSubject{" +
                "Principal='" + principal + '\'' +
                ", certificate='" + certificate + '\'' +
                ", accountType=" + accountType +
                ", authorities=" + authorities +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}
