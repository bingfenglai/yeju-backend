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
package pers.lbf.yeju.provider.log.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/3 22:11
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq.listener.login-log")
public class LoginLogMqConfig implements Serializable {

    private String queueName;
    private Boolean queueDurable;
    private Boolean exchangeDurable;
    private String exchangeName;
    private String exchangeType;
    private Boolean exchangeIgnoreDeclarationExceptions;
    private String key;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Boolean getQueueDurable() {
        return queueDurable;
    }

    public void setQueueDurable(Boolean queueDurable) {
        this.queueDurable = queueDurable;
    }

    public Boolean getExchangeDurable() {
        return exchangeDurable;
    }

    public void setExchangeDurable(Boolean exchangeDurable) {
        this.exchangeDurable = exchangeDurable;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Boolean getExchangeIgnoreDeclarationExceptions() {
        return exchangeIgnoreDeclarationExceptions;
    }

    public void setExchangeIgnoreDeclarationExceptions(Boolean exchangeIgnoreDeclarationExceptions) {
        this.exchangeIgnoreDeclarationExceptions = exchangeIgnoreDeclarationExceptions;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "LoginLogMqConfig{" +
                "queueName='" + queueName + '\'' +
                ", queueDurable=" + queueDurable +
                ", exchangeDurable=" + exchangeDurable +
                ", exchangeName='" + exchangeName + '\'' +
                ", exchangeType='" + exchangeType + '\'' +
                ", exchangeIgnoreDeclarationExceptions=" + exchangeIgnoreDeclarationExceptions +
                ", key='" + key + '\'' +
                '}';
    }
}
