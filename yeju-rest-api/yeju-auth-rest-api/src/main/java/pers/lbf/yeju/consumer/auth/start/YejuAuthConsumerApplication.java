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
package pers.lbf.yeju.consumer.auth.start;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/** 认证服务消费者
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/4 17:02
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.consumer.auth")
@EnableWebFluxSecurity
@Slf4j
public class YejuAuthConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(YejuAuthConsumerApplication.class, args);
        log.info("############################################");
        log.info("#------------认证服务消费方启动成功！-----------#");
        log.info("############################################");
    }
}
