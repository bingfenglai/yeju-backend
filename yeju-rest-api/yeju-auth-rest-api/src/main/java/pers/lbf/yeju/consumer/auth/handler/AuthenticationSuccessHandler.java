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
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.auth.config.AllowMultipleLoginsConfig;
import pers.lbf.yeju.consumer.auth.manager.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.auth.manager.OnlineManager;
import pers.lbf.yeju.consumer.auth.pojo.LoginRepoBean;
import pers.lbf.yeju.consumer.auth.sender.OnlineSender;
import pers.lbf.yeju.service.interfaces.auth.enums.SessionStatus;
import pers.lbf.yeju.service.interfaces.session.ISessionService;
import pers.lbf.yeju.service.interfaces.session.pojo.OnlineInfoBean;
import reactor.core.publisher.Mono;

/**
 * 认证通过处理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/10 22:51
 */
@Component
public class AuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {

    @Autowired
    private AuthorizationTokenManager authorityManager;

    @DubboReference
    private ISessionService sessionService;

    @Autowired
    private OnlineSender onlineSender;

    @Autowired
    private AllowMultipleLoginsConfig allowMultipleLoginsConfig;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    /**
     * 认证成功之后，返回授权token
     *
     * @param webFilterExchange e
     * @param authentication    authc
     * @return 1
     */
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {

        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //设置body


        byte[] dataBytes = {};
        //令牌
        String token = "";

        //过期时间，单位 秒
        Long expires = authorityManager.getTokenExpiresTime(exchange);
        IResult<Object> result;

        logger.info(authentication.getPrincipal().toString());


        AuthorityInfoBean authorityInfoBean = (AuthorityInfoBean) authentication.getPrincipal();

        logger.debug("是否允许多地登录 {}", allowMultipleLoginsConfig.isAllow());
        //允许多地登录开关
        if (!allowMultipleLoginsConfig.isAllow()) {
            // 先销毁会话（防止多地登录）
            sessionService.destroySession(authorityInfoBean.getPrincipal());
        }


        // 然后生成新的会话
        String sessionId = authorityInfoBean.getPrincipal() + System.currentTimeMillis();
        authorityInfoBean.setSessionId(sessionId);

        //Boolean success = sessionService.initSession(sessionId, authorityInfoBean.getPrincipal()).isSuccess();
        Boolean success = sessionService.initSession(sessionId, authorityInfoBean.getPrincipal(), expires).isSuccess();

        if (!success) {
            throw ServiceException.getInstance(SessionStatus.InitFailed);
        }

        try {

            token = authorityManager.getBuilder(authorityInfoBean, expires).build();
            LoginRepoBean loginRepoBean = new LoginRepoBean();
            loginRepoBean.setAccessToken(token);
            loginRepoBean.setExpiresAt(expires);
            result = Result.ok(loginRepoBean);

        } catch (Exception e) {
            logger.error("生成token发生错误，用户凭证：{}", authorityInfoBean.getPrincipal());
            logger.error(String.valueOf(e));

            result = SimpleResult.failed(AuthStatusEnum.GEN_TOKEN_FAIL);
        }
        dataBytes = JacksonUtils.toJsonBytes(result);

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(
                Mono.just(bodyDataBuffer)
                        .doFinally(signalType -> {
                            addOnline(request, authorityInfoBean);
                        })

        );


    }


    private void addOnline(ServerHttpRequest request, AuthorityInfoBean authorityInfoBean) {

        OnlineInfoBean onlineInfoBean = OnlineManager.getOnlineInfoBeanBuilder(authorityInfoBean.getPrincipal(), request)
                .build();

        onlineInfoBean.setSessionId(authorityInfoBean.getSessionId());
        onlineSender.send(onlineInfoBean, null);

    }


}
