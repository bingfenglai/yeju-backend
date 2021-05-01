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

package pers.lbf.yeju.encryption.service.interfaces.status;

import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/30 16:30
 */
public enum EncryptionServiceStatus implements IStatus {
    /**
     * 密钥已过期
     */
    keyHasExpired("密钥已过期", "ets01"),
    failedToDecryptEncryptedData("加密数据解析失败", "ets02");

    private String message;
    private String code;


    EncryptionServiceStatus(String message, String code) {
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
}
