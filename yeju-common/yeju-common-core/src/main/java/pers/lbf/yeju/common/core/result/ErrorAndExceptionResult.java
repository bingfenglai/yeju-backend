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
package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/20 15:58
 */
public class ErrorAndExceptionResult extends BaseResult<Object> implements IResult<Object>, Serializable {

    private String path;

    private Boolean success = false;

    public static ErrorAndExceptionResult getInstance(IStatus statusEnum, String path) {
        return new ErrorAndExceptionResult(statusEnum, path);
    }

    public static ErrorAndExceptionResult getInstance(String code, String message, String path) {
        return new ErrorAndExceptionResult(code, message, path);
    }


    private ErrorAndExceptionResult(IStatus statusEnum, String path) {
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
        this.path = path;

    }

    public ErrorAndExceptionResult(String code, String message, String path) {
        this.code = code;
        this.message = message;
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorAndExceptionResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
