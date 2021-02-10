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
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.auth.manager.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.auth.pojo.AuthorityInfoBean;
import pers.lbf.yeju.consumer.auth.pojo.LoginRepoBean;
import pers.lbf.yeju.consumer.auth.sender.SessionInitSender;
import reactor.core.publisher.Mono;

/**认证通过处理器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/10 22:51
 */
@Component
public class AuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {

    @Autowired
    private AuthorizationTokenManager authorityManager;

    @Autowired
    private SessionInitSender sessionInitSender;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    /**
     * 认证成功之后，返回授权token
     * @param webFilterExchange e
     * @param authentication authc
     * @return 1
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
        //令牌
        String token = "";

        //过期时间，单位 分钟
        Long expires = getTokenExpiresTime(exchange);
        String tokenPrefix = TokenConstant.getPrefixToken();
        IResult<Object> result;

        logger.info(authentication.getPrincipal().toString());


        AuthorityInfoBean authorityInfoBean = (AuthorityInfoBean) authentication.getPrincipal();


        try {
//            1 = authorityManager.getAuthorityInfoToken(authorityInfo);
//            httpHeaders.add("Authorization",tokenPrefix+1);
//            result = SimpleResult.ok("登录成功");
            token = authorityManager.getBuilder(authorityInfoBean, expires).build();
            LoginRepoBean loginRepoBean = new LoginRepoBean();
            loginRepoBean.setAccessToken(tokenPrefix+token);
            loginRepoBean.setExpiresAt(expires);
            result = Result.ok(loginRepoBean);

        } catch (Exception e) {
            logger.error("生成token发生错误，用户凭证：{}", authorityInfoBean.getPrincipal());
            logger.error(String.valueOf(e));

            result = SimpleResult.failed(AuthStatusEnum.GEN_TOKEN_FAIL);
        }
        dataBytes = JacksonUtils.toJsonBytes(result);

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        sessionInitSender.send(authorityInfoBean.getPrincipal(),null);
        return response.writeWith(Mono.just(bodyDataBuffer));


    }


    private Long getTokenExpiresTime(ServerWebExchange exchange){



        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        String userAgent = requestHeaders.getFirst("User-Agent");
        if ("client".equalsIgnoreCase(userAgent)) {
            return TokenConstant.AppTokenExpiresAt;
        }else {
            return TokenConstant.PcTokenExpiresAt;
        }

    }




}
