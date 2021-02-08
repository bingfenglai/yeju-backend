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
package pers.lbf.yeju.common.core.exception.service;

import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.core.status.insterfaces.IStatus;


/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/18 10:21
 */
public class ServiceException extends RuntimeException{

    /**
     * 错误消息提示
     */
    protected String message;

    /**
     * 异常编码
     */
    protected String exceptionCode;

    /**
     * 服务调用参数
     */
    protected Object[] params;

    /**
     * 异常所属模块
     */
    protected String module;


    public static ServiceException getInstance(String message, String exceptionCode) {
        return new ServiceException(message, exceptionCode);
    }

    public static ServiceException getInstance(IStatus status) {
        return new ServiceException(status);
    }

    public static ServiceException getInstance() {
        return new ServiceException();
    }

    public ServiceException(IStatus statusEnum){
        super(statusEnum.getMessage());
        this.exceptionCode = statusEnum.getCode();
        this.message = statusEnum.getMessage();

    }
    public ServiceException() {
        this.message = ServiceStatusEnum.UNKNOWN_ERROR.getMessage();
        this.exceptionCode = ServiceStatusEnum.UNKNOWN_ERROR.getCode();
    }

    public ServiceException(String message, String exceptionCode, Object[] params, String module) {
        super(message);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = params;
        this.module = module;
    }

    public ServiceException(String message, String exceptionCode){
        super(message);
        this.message = message;
        this.exceptionCode = exceptionCode;
    }

    public ServiceException(String message, Throwable cause, String exceptionCode, Object[] params, String module) {
        super(message, cause);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = params;
        this.module = module;
    }

    public ServiceException(Throwable cause, String message, String exceptionCode, Object[] params, String module) {
        super(cause);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = params;
        this.module = module;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionCode, Object[] parmas, String module) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = parmas;
        this.module = module;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
