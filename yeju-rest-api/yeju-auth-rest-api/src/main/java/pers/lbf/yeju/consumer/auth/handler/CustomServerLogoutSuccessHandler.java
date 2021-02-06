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
package pers.lbf.yeju.consumer.auth.handler;

import com.alibaba.nacos.common.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import reactor.core.publisher.Mono;

/**注销登录成功处理器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 14:52
 */
@Component
public class CustomServerLogoutSuccessHandler implements ServerLogoutSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomServerLogoutSuccessHandler.class);

    /**登出成功后续处理
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/14 15:02
     * @param webFilterExchange e
     * @param authentication authc
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {


        String principal = (String) authentication.getPrincipal();
        logger.info("账号 {} 注销成功",principal);

        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        IResult<Object> result = SimpleResult.ok(AuthStatusEnum.logout_success.getMessage());

        DataBuffer buffer = response.bufferFactory().wrap(JacksonUtils.toJsonBytes(result));

        return response.writeWith(Mono.just(buffer));
    }
}
