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

package pers.lbf.yeju.gateway.web.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.gateway.config.ApiAccessLimitConfig;
import pers.lbf.yeju.gateway.exception.GatewayException;
import pers.lbf.yeju.gateway.exception.status.ApiAccessStatus;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 接口防刷拦截器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/20 17:19
 */

public class ApiAccessLimitHandler implements GlobalFilter, Ordered {

    private static Logger log = LoggerFactory.getLogger(ApiAccessLimitHandler.class);

    @Autowired
    private ApiAccessLimitConfig apiAccessLimitConfig;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("接口防刷拦截");
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getURI().getPath();
        log.info("请求路径 {}", requestPath);
        log.info("接口防刷 {}", apiAccessLimitConfig.getPath().toString());

        for (int i = 0; i <= apiAccessLimitConfig.getPath().size(); i++) {
            // 如果当前访问的路径是需要进行接口防刷处理的路径
            if (antPathMatcher.match(apiAccessLimitConfig.getPath().get(i), requestPath.replace("/", ":").substring(1))) {
                doLimitCheck(request, apiAccessLimitConfig.getSecond().get(i));
            } else {
                log.info("不皮雷");
            }
        }

        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return -199;
    }

    public void doLimitCheck(ServerHttpRequest request, int limitSecond) throws ServiceException {
        log.info("执行接口防刷拦截校验");
        List<String> list = request.getHeaders().get(request.getPath().toString());
        boolean flag = false;
        if (list != null && list.size() > 0) {

            Long lastTime = Long.valueOf(list.get(0));
            Long timeInterval = System.currentTimeMillis() - lastTime;
            log.info("上一次访问时间 {} 时间差 {}", lastTime, timeInterval);
            flag = timeInterval <= limitSecond * 1000L;

        }

        request.getHeaders().set(request.getPath().toString(), String.valueOf(System.currentTimeMillis()));
        if (flag) {
            throw GatewayException.getInstance(ApiAccessStatus.limit);
        }

    }

}
