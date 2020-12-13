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
package pers.lbf.yeju.gateway.security.handler;

import com.alibaba.nacos.common.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.enumes.AuthStatus;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.gateway.security.manager.AuthorityManager;
import pers.lbf.yeju.gateway.security.pojo.AuthorityInfo;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**认证通过处理器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/10 22:51
 */
@Component
public class AuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {

    @Autowired
    private AuthorityManager authorityManager;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    /**
     * 认证成功之后，返回授权token
     * @param webFilterExchange e
     * @param authentication authc
     * @return token
     */
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication){

        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //设置body

        byte[] dataBytes={};
        String token = "";
        String tokenPrefix = "yeju_";
        SimpleResult result;
        AuthorityInfo authorityInfo = (AuthorityInfo) authentication.getPrincipal();
        try {
            token = authorityManager.getAuthorityInfoToken(authorityInfo);
            httpHeaders.add("Authorization",tokenPrefix+token);
            result = SimpleResult.ok("登录成功");

        } catch (Exception e) {
            logger.error("生成token发生错误，用户凭证：{}",authorityInfo.getPrincipal());
            logger.error(Arrays.toString(e.getStackTrace()));

            result = SimpleResult.faild(AuthStatus.GEN_TOKEN_FAIL);
        }
        dataBytes = JacksonUtils.toJsonBytes(result);

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));

    }




}
