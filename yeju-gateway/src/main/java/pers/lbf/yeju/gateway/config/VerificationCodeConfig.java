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
package pers.lbf.yeju.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**验证码配置加载类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/24 14:02
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "verification.code")
public class VerificationCodeConfig {

    private boolean enable;

    private Integer length;

    public boolean isEnable() {

        return enable;
    }

    public void setEnable(String enable) {
        this.enable = "true".equals(enable);
    }


    @Override
    public String toString() {
        return "VerificationCodeConfig{" +
                "enable=" + enable +
                ", length=" + length +
                '}';
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
