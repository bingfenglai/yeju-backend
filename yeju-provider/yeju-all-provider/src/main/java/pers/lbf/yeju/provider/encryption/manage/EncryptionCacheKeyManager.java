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

package pers.lbf.yeju.provider.encryption.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.provider.encryption.config.EncryptionAesConfig;
import pers.lbf.yeju.provider.encryption.config.EncryptionRsaConfig;

/**
 * 密钥缓存key管理类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/29 21:16
 */
@Component
public class EncryptionCacheKeyManager {

    @Autowired
    private EncryptionAesConfig aesConfig;

    @Autowired
    private EncryptionRsaConfig rsaConfig;


    public String getRsaCacheKey(String account) {
        return rsaConfig.getKeyPrefix() + ":" + account + ":" + System.currentTimeMillis();
    }

    public String getAesCacheKey(String account) {
        return aesConfig.getKeyPrefix() + ":" + account + ":" + System.currentTimeMillis();
    }


}
