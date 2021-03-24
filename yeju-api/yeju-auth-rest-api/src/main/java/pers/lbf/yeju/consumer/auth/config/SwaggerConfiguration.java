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

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.configuration.Swagger2WebFluxConfiguration;

import java.util.*;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/14 22:35
 */
@EnableOpenApi
@Configuration
@Slf4j
public class SwaggerConfiguration extends Swagger2WebFluxConfiguration {

    @Autowired
    private SwaggerPropertiesConfig config;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.OAS_30).pathMapping("/auth-consumer")
                // 定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(config.getEnable())
                .apiInfo(createApiInfo())
                .host(config.getTryHost())
                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(newHashSet("https", "http"))

                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(securitySchemes())

                // 授权信息全局应用
                .securityContexts(securityContexts());

    }

    private List<SecurityContext> securityContexts() {

        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(
                                Collections.singletonList(
                                        new SecurityReference(TokenConstant.TOKEN_KEY,
                                                new AuthorizationScope[]{
                                                        new AuthorizationScope("global",
                                                                "")})))
                        .build()
        );

    }

    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey(TokenConstant.TOKEN_KEY, TokenConstant.TOKEN_KEY, In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }

    public ApiInfo createApiInfo() {
        return new ApiInfoBuilder().title(config.getApplicationName() + " Api Doc")
                .description(config.getApplicationDescription())
                .contact(new Contact("赖 柄沣", null, "bingfengdev@aliyun.com"))
                .version("Application Version: " + config.getApplicationVersion() + ", Spring Boot Version: " + SpringBootVersion.getVersion())
                .build();
    }

}
