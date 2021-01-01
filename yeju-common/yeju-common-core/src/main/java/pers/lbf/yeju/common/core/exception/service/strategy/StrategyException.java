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
package pers.lbf.yeju.common.core.exception.service.strategy;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/23 13:30
 */
public class StrategyException extends ServiceException {

    public StrategyException(IStatus statusEnum) {
        super(statusEnum);
    }

    public StrategyException() {
    }

    public StrategyException(String message, String exceptionCode, Object[] params, String module) {
        super(message, exceptionCode, params, module);
    }

    public StrategyException(String message, String exceptionCode) {
        super(message, exceptionCode);
    }

    public StrategyException(String message, Throwable cause, String exceptionCode, Object[] params, String module) {
        super(message, cause, exceptionCode, params, module);
    }

    public StrategyException(Throwable cause, String message, String exceptionCode, Object[] params, String module) {
        super(cause, message, exceptionCode, params, module);
    }

    public StrategyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionCode, Object[] parmas, String module) {
        super(message, cause, enableSuppression, writableStackTrace, exceptionCode, parmas, module);
    }
}
