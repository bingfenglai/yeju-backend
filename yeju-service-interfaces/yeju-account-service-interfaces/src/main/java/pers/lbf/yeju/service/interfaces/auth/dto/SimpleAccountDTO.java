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
package pers.lbf.yeju.service.interfaces.auth.dto;

import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/17 9:04
 */
public class SimpleAccountDTO implements Serializable {

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
     * 账户状态
     */
    private String accountStatus;


    /**
     * 权限集合
     */
    private List<String> authorityStringList;


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

    @Override
    public String toString() {
        return "SimpleAccountDTO{" +
                "principal='" + principal + '\'' +
                ", accountType=" + accountType +
                ", accountStatus='" + accountStatus + '\'' +
                ", authorityStringList=" + authorityStringList +
                '}';
    }

    public SubjectTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(SubjectTypeEnum accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<String> getAuthorityStringList() {
        return authorityStringList;
    }

    public void setAuthorityStringList(List<String> authorityStringList) {
        this.authorityStringList = authorityStringList;
    }
}
