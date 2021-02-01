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

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/1 16:49
 */
public enum FileStatus implements IStatus {
    /**
     * 文件不存在
     */
    NOT_Found("fs001","文件不存在");

    String message;
    String code;

    FileStatus(String msg, String code) {
        this.message = msg;
        this.code = code;
    }


    /**
     * 状态消息
     *
     * @return msg
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 状态编码
     *
     * @return code
     */
    @Override
    public String getCode() {
        return code;
    }
}
