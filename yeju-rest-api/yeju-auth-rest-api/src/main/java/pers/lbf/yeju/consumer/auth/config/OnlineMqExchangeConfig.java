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
import pers.lbf.yeju.base.mq.config.BaseRabbitMqExchangeConfig;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/6 18:04
 */
@ConfigurationProperties(prefix = "spring.rabbitmq.listener.online.exchange")
public class OnlineMqExchangeConfig extends BaseRabbitMqExchangeConfig {

    @Override
    public Boolean getDurable() {

        return this.durable;
    }

    @Override
    public void setDurable(Boolean durable) {
        this.durable = durable;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Boolean getIgnoreDeclarationExceptions() {
        return this.ignoreDeclarationExceptions;
    }

    @Override
    public void setIgnoreDeclarationExceptions(Boolean ignoreDeclarationExceptions) {
        this.ignoreDeclarationExceptions = ignoreDeclarationExceptions;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }
}
