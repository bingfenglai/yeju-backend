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
package pers.lbf.yeju.service.interfaces.log.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/29 11:10
 */
public class AddLoginLogRequestBean implements Serializable {

    /** 访问账户 */
    private String account;
    /** 访问者姓名 */
    private String subjectName;
    /** 访问ip地址 */
    private String ip;
    /** 访问状态0失败1成功 */
    private Integer loginStatus;
    /** 访问失败时记录原因 */
    private String message;
    /** 访问时间 */
    private Date accentTime;
    /** ip地址最后一位，表分区依据 */
    private Integer lastIpNumber;


    @Override
    public String toString() {
        return "addLoginLogDTO{" +
                "account='" + account + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", ip='" + ip + '\'' +
                ", loginStatus=" + loginStatus +
                ", message='" + message + '\'' +
                ", accentTime=" + accentTime +
                ", lastIpNumber=" + lastIpNumber +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getAccentTime() {
        return accentTime;
    }

    public void setAccentTime(Date accentTime) {
        this.accentTime = accentTime;
    }

    public Integer getLastIpNumber() {
        return lastIpNumber;
    }

    public void setLastIpNumber(Integer lastIpNumber) {
        this.lastIpNumber = lastIpNumber;
    }
}
