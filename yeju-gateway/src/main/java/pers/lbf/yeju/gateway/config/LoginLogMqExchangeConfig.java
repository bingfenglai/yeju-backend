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

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2021/1/3 22:11
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq.listener.login.log.exchange")
public class LoginLogMqExchangeConfig implements Serializable {

  private Boolean durable;
  private String name;
  private String type;
  private Boolean ignoreDeclarationExceptions;
  private String key;


    public Boolean getDurable() {
        return durable;
    }

    public void setDurable(Boolean durable) {
        this.durable = durable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIgnoreDeclarationExceptions() {
        return ignoreDeclarationExceptions;
    }

    public void setIgnoreDeclarationExceptions(Boolean ignoreDeclarationExceptions) {
        this.ignoreDeclarationExceptions = ignoreDeclarationExceptions;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "LoginLogMqExchangeConfig{" +
                "durable=" + durable +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", ignoreDeclarationExceptions=" + ignoreDeclarationExceptions +
                ", key='" + key + '\'' +
                '}';
    }
}
