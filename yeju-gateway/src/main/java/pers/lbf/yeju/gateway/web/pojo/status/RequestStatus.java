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
package pers.lbf.yeju.gateway.web.pojo.status;

import pers.lbf.yeju.common.core.status.insterfaces.Status;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/20 16:18
 */
public enum RequestStatus implements Status, Serializable {

    /**
     * 非法的请求
     */
    illegalRequest("请求非法，请勿恶意访问！","req01");

    private String message;
    private String code;

    RequestStatus(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "RequestStatus{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
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
}
