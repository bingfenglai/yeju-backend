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
package pers.lbf.yeju.provider.log.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import pers.lbf.yeju.service.interfaces.log.pojo.LoginLogInfoBean;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/19 10:10
 */
public class SimpleLoginLogInfoBean implements LoginLogInfoBean, Serializable {

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long loginLogId;
    /**
     * 访问账户
     */
    private String account;
    /**
     * 访问者姓名
     */
    private String subjectName;
    /**
     * 访问ip地址
     */
    private String ip;
    /**
     * 访问状态0失败1成功
     */
    private Integer loginStatus;
    /**
     * 访问失败时记录原因
     */
    private String message;
    /**
     * 访问时间
     */
    private Date accentTime;

    @Override
    public String toString() {
        return "SimpleLoginLogInfoBean{" +
                "loginLogId=" + loginLogId +
                ", account='" + account + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", ip='" + ip + '\'' +
                ", loginStatus=" + loginStatus +
                ", message='" + message + '\'' +
                ", accentTime=" + accentTime +
                '}';
    }

    @Override
    public Long getLoginLogId() {
        return loginLogId;
    }

    @Override
    public void setLoginLogId(Long loginLogId) {
        this.loginLogId = loginLogId;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public Integer getLoginStatus() {
        return loginStatus;
    }

    @Override
    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Override
    public Date getAccentTime() {
        return accentTime;
    }

    @Override
    public void setAccentTime(Date accentTime) {
        this.accentTime = accentTime;
    }
}
