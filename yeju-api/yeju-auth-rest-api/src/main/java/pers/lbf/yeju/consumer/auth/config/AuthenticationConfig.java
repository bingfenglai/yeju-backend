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

package pers.lbf.yeju.consumer.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/30 15:57
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "yeju.authentication")
public class AuthenticationConfig {

    private Boolean allowMultiAuthentication;

    private Boolean enableFormEncryption;
    private Boolean enableVerificationCodeCheck;
    private Boolean enableSessionCheck;


    public Boolean getAllowMultiAuthentication() {
        return allowMultiAuthentication;
    }

    public void setAllowMultiAuthentication(Boolean allowMultiAuthentication) {
        this.allowMultiAuthentication = allowMultiAuthentication;
    }

    public Boolean getEnableFormEncryption() {
        return enableFormEncryption;
    }

    public void setEnableFormEncryption(Boolean enableFormEncryption) {
        this.enableFormEncryption = enableFormEncryption;
    }

    public Boolean getEnableVerificationCodeCheck() {
        return enableVerificationCodeCheck;
    }

    public void setEnableVerificationCodeCheck(Boolean enableVerificationCodeCheck) {
        this.enableVerificationCodeCheck = enableVerificationCodeCheck;
    }

    public Boolean getEnableSessionCheck() {
        return enableSessionCheck;
    }

    public void setEnableSessionCheck(Boolean enableSessionCheck) {
        this.enableSessionCheck = enableSessionCheck;
    }
}
