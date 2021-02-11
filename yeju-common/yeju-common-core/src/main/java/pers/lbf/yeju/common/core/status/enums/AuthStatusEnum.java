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
package pers.lbf.yeju.common.core.status.enums;

import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/8 19:43
 */
public enum AuthStatusEnum implements IStatus, Serializable {
    /**
     * 生成token失败
     */
    GEN_TOKEN_FAIL("生成token失败", "A0002"),

    /**
     * 没有携带token，或者token不合法
     */
    NO_TOKEN("令牌已过期，请重新登录", "A0001"),

    /**
     * 用户名或密码错误
     */
    authentication_failed("用户名或密码错误", "A0003"),

    /**
     * 注销成功
     */
    logout_success("注销成功","A0004"),

    NO_ACCOUNT("账号或密码错误","A0005"),

    account_cannot_be_empty("账号不能为空","A0006"),

    unauthorized("无权限访问该资源！","A0007"),

    passwordCanNotBeBlank("密码不能为空","A0008" ),

    accountCannotBeEmpty("账号不能为空","A0009" ),

    verificationCodeError("验证码错误","A0010" ),

    accountHasExpired("账户已过期","A0011" ),

    accountIsFrozen("账户已冻结","A0012" ),

    accountIsNotActivated("账户未启用","A0013" );

    private String message;
    private String code;


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    AuthStatusEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "AuthStatus{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
