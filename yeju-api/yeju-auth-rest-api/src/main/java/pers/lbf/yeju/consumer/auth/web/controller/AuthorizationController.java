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
package pers.lbf.yeju.consumer.auth.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.consumer.auth.manager.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.auth.pojo.LoginRepoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.AuthzSimpleInfoBean;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAuthorizationService;
import pers.lbf.yeju.service.interfaces.auth.pojo.AuthorizedCreatArgs;
import pers.lbf.yeju.service.interfaces.session.ISessionService;
import pers.lbf.yeju.service.interfaces.session.pojo.SessionDetails;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 权限信息接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/7 21:10
 */
@RestController
@RequestMapping("/authz")
@ApiModel(value = "权限信息接口",
        description = "获取用户认证后的个人信息")
@Api(tags = "权限信息服务")
public class AuthorizationController {

    @DubboReference
    private ISessionService sessionService;

    @DubboReference()
    private IAuthorizationService authzService;

    @Autowired
    private AuthorizationTokenManager tokenManager;


    @ApiOperation(value = "获取简单授权信息", notes = "AuthorizationController说明", httpMethod = "GET")
    @GetMapping("/simple/info")
    public Mono<IResult<AuthzSimpleInfoBean>> getAuthzSimpleInfo(ServerWebExchange webExchange) throws Exception {
        //获取token
        AuthorityInfoBean authorityInfo = getAuthorityInfoBeanByToken(webExchange);
        String principal = authorityInfo.getPrincipal();

        return Mono.just(authzService.findAuthzInfoByPrincipal(principal));
    }


    @ApiOperation(value = "获取账号主体信息", notes = "账号主题信息说明", httpMethod = "GET")
    @GetMapping("/getAuthzDetailInfo")
    public Mono<IResult<SessionDetails>> getAuthzDetailInfo(ServerWebExchange webExchange) throws Exception {
        //获取token
        AuthorityInfoBean authorityInfo = getAuthorityInfoBeanByToken(webExchange);

        String sessionId = authorityInfo.getSessionId();

        return Mono.just(sessionService.getSessionDetails(sessionId));
    }

    @ApiOperation(value = "刷新令牌", notes = "说明", httpMethod = "GET")
    @GetMapping("/refreshToken")
    public Mono<IResult<LoginRepoBean>> refreshToken(ServerWebExchange webExchange) throws Exception {
        // 获取令牌中的信息
        AuthorityInfoBean authorityInfo = getAuthorityInfoBeanByToken(webExchange);

        Long tokenExpiresTime = tokenManager.getTokenExpiresTime(webExchange);
        // 生成新的令牌
        String newToken = tokenManager.getBuilder(authorityInfo, tokenExpiresTime)
                .build();

        LoginRepoBean bean = new LoginRepoBean();
        bean.setExpiresAt(tokenExpiresTime);
        bean.setAccessToken(newToken);

        return Mono.just(Result.ok(bean))
                .doFinally(signalType -> {
                    sessionService.expired(authorityInfo.getPrincipal(), tokenExpiresTime, TimeUnit.SECONDS);
                }).toProcessor();
    }


    @ApiOperation(value = "对角色授权接口", notes = "对角色授权接口说明", httpMethod = "POST")
    @PostMapping("/authorize")
    public Mono<IResult<Boolean>> authorize(@RequestBody AuthorizedCreatArgs args) throws ServiceException {

        return Mono.just(authzService.authorizedToRole(args))
                .doOnSuccess(unused -> {
                    // 同步 r_t_account_resource表 优化查询

                });
    }


    private AuthorityInfoBean getAuthorityInfoBeanByToken(ServerWebExchange webExchange) throws Exception {
        String authorization = Objects.requireNonNull(webExchange.getRequest().getHeaders().get(TokenConstant.TOKEN_KEY)).get(0);
        return tokenManager.getAuthorityInfo(authorization);
    }
}
