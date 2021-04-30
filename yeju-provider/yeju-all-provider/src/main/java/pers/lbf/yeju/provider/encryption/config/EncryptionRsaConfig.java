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

package pers.lbf.yeju.provider.encryption.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/29 20:34
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "yeju.encryption.rsa")
public class EncryptionRsaConfig implements Serializable {

    private Integer secretKeySize;
    private Integer keyTimeout;
    private String keyPrefix;

    public Integer getSecretKeySize() {
        return secretKeySize;
    }

    public void setSecretKeySize(Integer secretKeySize) {
        this.secretKeySize = secretKeySize;
    }

    public Integer getKeyTimeout() {
        return keyTimeout;
    }

    public void setKeyTimeout(Integer keyTimeout) {
        this.keyTimeout = keyTimeout;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
