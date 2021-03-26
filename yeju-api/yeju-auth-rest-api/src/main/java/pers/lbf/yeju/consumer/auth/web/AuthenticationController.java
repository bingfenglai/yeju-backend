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
package pers.lbf.yeju.consumer.auth.web;

import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.auth.manager.AuthorizationTokenManager;
import pers.lbf.yeju.service.interfaces.auth.dto.VerityDTO;
import pers.lbf.yeju.service.interfaces.auth.enums.VerificationCodeTypeEnum;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IVerificationCodeService;
import pers.lbf.yeju.service.interfaces.session.ISessionService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

/**
 * 认证控制器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/7 20:36
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @DubboReference
    private IVerificationCodeService codeService;

    @DubboReference
    private ISessionService sessionService;

    @Autowired
    private AuthorizationTokenManager tokenManager;


    @GetMapping("/login")
    public Mono<IResult<Object>> login() throws Exception {
        return Mono.just(SimpleResult.failed(AuthStatusEnum.NO_TOKEN));
    }

    @ApiOperation(value = "登出方法", notes = "登出方法说明", httpMethod = "DELETE")
    @DeleteMapping("/logout")
    public Mono<Object> logout(ServerWebExchange webExchange) throws ServiceException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, ExpiredJwtException {
        //获取token
        String authorization = Objects.requireNonNull(webExchange.getRequest().getHeaders().get(TokenConstant.TOKEN_KEY)).get(0);

        if (authorization == null) {
            return Mono.empty();
        }

        AuthorityInfoBean authorityInfo = new AuthorityInfoBean();

        authorityInfo = tokenManager.getAuthorityInfo(authorization);

        String principal = authorityInfo.getPrincipal();
        return Mono.empty().doFinally(signalType -> {

            if (principal != null) {
                sessionService.destroySession(principal);
            }
        });
    }


    /**
     * @return pers.lbf.yeju.common.core.result.IResult
     * @Description 未登录调用
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/12 17:58
     */
    @GetMapping("/unauthc")
    public Mono<IResult<Object>> unauthc() throws Exception {

        return Mono.just(SimpleResult.failed(AuthStatusEnum.NO_TOKEN));
    }

    /**
     * 获取图片验证码接口
     *
     * @return reactor.core.publisher.Mono<pers.lbf.yeju.common.core.result.IResult < pers.lbf.yeju.authserver.interfaces.dto.VerityDTO < java.lang.Object>>>
     * @throws Exception e
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/28 15:25
     */
    @GetMapping("/code/image")
    public Mono<IResult<VerityDTO<String>>> getImageCode() throws Exception {

        IResult<VerityDTO<String>> result = codeService.getVerificationCode(VerificationCodeTypeEnum.PICTURE_VERIFICATION_CODE);

        return Mono.just(result);
    }


    /**
     * 获取手机验证码
     *
     * @param phoneNumber 手机号
     * @return reactor.core.publisher.Mono<pers.lbf.yeju.common.core.result.IResult < pers.lbf.yeju.authserver.interfaces.dto.VerityDTO < java.lang.String>>>
     * @throws Exception e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/28 16:48
     */
    @GetMapping("/code/phone/{phoneNumber}")
    public Mono<IResult<VerityDTO<String>>> getPhoneCode(
            @PathVariable
            @Valid @NotNull(message = "手机号不能为空") String phoneNumber) throws Exception {

        IResult<VerityDTO<String>> result = codeService.getVerificationCode(VerificationCodeTypeEnum.MOBILE_VERIFICATION_CODE);
        return Mono.just(result);
    }


}
