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

package pers.lbf.yeju.base.security.authorization.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 检查会话是否被强制清退配置类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/6 20:02
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "yeju")
public class SessionCheckConfig {

    private boolean sessionCheckEnabled = true;

    public boolean isSessionCheckEnabled() {
        return sessionCheckEnabled;
    }

    public void setSessionCheckEnabled(boolean sessionCheckEnabled) {
        this.sessionCheckEnabled = sessionCheckEnabled;
    }
}
