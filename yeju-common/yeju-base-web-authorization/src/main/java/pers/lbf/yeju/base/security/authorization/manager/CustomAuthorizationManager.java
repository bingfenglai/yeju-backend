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
package pers.lbf.yeju.base.security.authorization.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import pers.lbf.yeju.base.security.authorization.config.SessionCheckConfig;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.service.interfaces.session.ISessionService;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * 鉴权管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 15:32
 */
@Component
@Primary
public class CustomAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final Logger log = LoggerFactory.getLogger(CustomAuthorizationManager.class);

    @Autowired
    private AuthorizationTokenManager tokenManager;

    @DubboReference
    private ISessionService sessionService;

    @Autowired
    private SessionCheckConfig sessionCheckConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        log.info("开始解析Token");
        log.info("开始从请求头中或许token");
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(TokenConstant.TOKEN_KEY);

        if (token == null) {
            log.info("没有获取到token");
            throw new ServiceException(AuthStatusEnum.NO_TOKEN);
        }

        AuthorityInfoBean authorityInfoBean = null;

        try {
            authorityInfoBean = tokenManager.getAuthorityInfo(token);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }


        if (authorityInfoBean == null) {
            throw new ServiceException(AuthStatusEnum.NO_TOKEN);
        }

        // 判断账户有没有被强制过期
        if (sessionCheckConfig.isSessionCheckEnabled()) {
            Boolean flag = sessionService.isExpired(authorityInfoBean.getSessionId()).getData();
            if (!flag) {
                throw new ServiceException(AuthStatusEnum.NO_TOKEN);
            }
        }


        List<String> authorityList = authorityInfoBean.getAuthorityList();

        log.info("开始对用户 {} 鉴权", authorityInfoBean.getPrincipal());
        if (authorityList == null || authorityList.size() == 0) {
            log.info("用户 {} 请求API校验 未通过", authorityInfoBean.getPrincipal());
            throw new ServiceException(AuthStatusEnum.unauthorized);
        }

        String path1 = request.getURI().getPath();
        log.info("用户请求路径：{}", path1);
        for (String s : authorityList) {
            if (antPathMatcher.match(s, path1.replace("/", ":").substring(1))) {
                log.info(String.format("用户请求API校验通过，GrantedAuthority:{%s}  Path:{%s} ", s, path1));
                return Mono.just(new AuthorizationDecision(true));
            }

        }
        log.info("用户 {} 请求API校验 不 通 过", authorityInfoBean.getPrincipal());
        throw new ServiceException(AuthStatusEnum.unauthorized);


    }


    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {

        return check(authentication, object)
                .filter(AuthorizationDecision::isGranted)
                .switchIfEmpty(Mono.defer(() -> {
                    IResult<Object> result = SimpleResult.failed(AuthStatusEnum.unauthorized);
                    String body = JSONObject.toJSONString(result);
                    return Mono.error(new AccessDeniedException(body));
                }))
                .flatMap(d -> Mono.empty());
    }
}
