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

package pers.lbf.yeju.gateway.start;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 微服务网关
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description 业务网关模块
 * @date 2020/11/26 1:06
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.gateway")
@Slf4j
public class YejuGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(YejuGatewayApp.class, args);
        log.info("############################################");
        log.info("#------------  微服务网关启动成功！ -----------#");
        log.info("############################################");

    }

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }


}
