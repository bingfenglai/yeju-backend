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

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/5 15:43
 */

public class AccountDetailsInfoBean implements Serializable {

    /** 账号 */
    private String accountNumber;

    /**
     * 手机号
     */
    private String phoneNumber;

    /** 账户所属者主键 */
    private Long subjectId;

    /** 最后一次登录地址 */
    private String lastLoginAddress;
    /** 最后一次登录时间 */
    private Date lastLoginDate;
    /** 账户状态0未启用1启用2冻结，详见属性表 */
    private String accountStatus;
    /** 账户等级，详见数据走到吗 */
    private String accountLevel;
    /** 账户类型，0内部账户1客户账户，详见属性表 */
    private String accountType;

    @Override
    public String toString() {
        return "AccountDetailsInfoBean{" +
                "accountNumber='" + accountNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", subjectId=" + subjectId +
                ", lastLoginAddress='" + lastLoginAddress + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", accountStatus='" + accountStatus + '\'' +
                ", accountLevel='" + accountLevel + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getLastLoginAddress() {
        return lastLoginAddress;
    }

    public void setLastLoginAddress(String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(String accountLevel) {
        this.accountLevel = accountLevel;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
