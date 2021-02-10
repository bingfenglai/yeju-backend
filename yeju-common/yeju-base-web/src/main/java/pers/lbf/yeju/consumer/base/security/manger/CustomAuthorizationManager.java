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
package pers.lbf.yeju.consumer.base.security.manger;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.base.security.pojo.AuthorityInfo;
import reactor.core.publisher.Mono;

import java.util.List;

/**鉴权管理器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 15:32
 */
@Component
@Slf4j
public class CustomAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private AuthorizationTokenManager tokenManager;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        log.info("开始解析Token");
        log.info("开始从请求头中或许token");
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(TokenConstant.TOKEN_KEY);

        if (token==null){
            throw new ServiceException(AuthStatusEnum.NO_TOKEN);
        }

        AuthorityInfo authorityInfo = null;
        try {
             authorityInfo = tokenManager.getAuthorityInfo(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (authorityInfo == null){
            throw new ServiceException(AuthStatusEnum.tokenHasExpired);
        }

        List<String> authorityList = authorityInfo.getAuthorityList();
//        authorityList = new ArrayList<>();
//        authorityList.add("*:**");
        log.info("开始对用户 {} 鉴权",authorityInfo.getPrincipal());
        if (authorityList == null || authorityList.size() == 0) {
            log.info("用户 {} 请求API校验 未通过",authorityInfo.getPrincipal());
            throw new ServiceException(AuthStatusEnum.unauthorized);
        }

        String path1 = request.getURI().getPath();
        log.info("用户请求路径：{}",path1);
        for (String s : authorityList) {
            if (antPathMatcher.match(s, path1)) {
                log.info(String.format("用户请求API校验通过，GrantedAuthority:{%s}  Path:{%s} ", s, path1));
                return Mono.just(new AuthorizationDecision(true));
            }

        }
        log.info("用户 {} 请求API校验通过",authorityInfo.getPrincipal());
        return Mono.just(new AuthorizationDecision(true));


//        return mono.map(auth -> {
//            ServerWebExchange exchange = authorizationContext.getExchange();
//            ServerHttpRequest request = exchange.getRequest();
//
//            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
//            for (GrantedAuthority authority : authorities) {
//                String authorityAuthority = authority.getAuthority();
//                String path = request.getURI().getPath();
//                if (antPathMatcher.match(authorityAuthority, path)) {
//                    pers.lbf.yeju.common.log.info(String.format("用户请求API校验通过，GrantedAuthority:{%s}  Path:{%s} ", authorityAuthority, path));
//                    return new AuthorizationDecision(true);
//                }else {
//                    pers.lbf.yeju.common.log.info(String.format("用户请求API校验 未通过，GrantedAuthority:{%s}  Path:{%s} ", authorityAuthority, path));
//                }
//            }
//            return new AuthorizationDecision(false);
//        }).defaultIfEmpty(new AuthorizationDecision(false));

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
