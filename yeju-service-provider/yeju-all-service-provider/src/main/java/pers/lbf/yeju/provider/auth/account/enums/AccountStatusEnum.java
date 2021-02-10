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
package pers.lbf.yeju.provider.auth.account.enums;

import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/20 0:14
 */
public enum AccountStatusEnum implements IStatus, Serializable {

    /**
     * 账号不存在
     */
    accountDoesNotExist("账号不存在","ac001"),

    AccountOwnerTypeNotExist("账户所属者类型不存在","ac002");

    private String message;
    private String code;


    AccountStatusEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    /**
     * 状态消息
     *
     * @return msg
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 状态编码
     *
     * @return code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AccountStatus{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
