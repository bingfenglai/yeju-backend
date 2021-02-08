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

import pers.lbf.yeju.common.core.constant.ServiceStatusConstant;

/** 基础结果类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/8 0:45
 */
public abstract class BaseResult<T> implements IResult<T>{

    protected String code;

    protected String message;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 请求是否成功
     *
     * @return success flag
     */
    @Override
    public Boolean isSuccess() {
        return this.code.equals(ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE);
    }

    /**
     * 获取响应数据
     *
     * @return t
     */
    @Override
    public T getData() {
        return null;
    }
}
