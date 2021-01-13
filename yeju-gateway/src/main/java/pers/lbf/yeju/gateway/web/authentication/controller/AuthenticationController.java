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
package pers.lbf.yeju.gateway.web.authentication.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.authrestapi.interfaces.dto.VerityDTO;
import pers.lbf.yeju.consumer.authrestapi.interfaces.enums.VerificationCodeTypeEnum;
import pers.lbf.yeju.consumer.authrestapi.interfaces.interfaces.IVerificationCodeService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**认证控制器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/7 20:36
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @DubboReference
    private IVerificationCodeService codeService;

    @GetMapping("/login")
    public Mono<IResult<Object>> login() throws Exception{
        return Mono.just(SimpleResult.faild(AuthStatusEnum.NO_TOKEN));
    }

    /**
     * @Description 未登录调用
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/12 17:58
     * @return pers.lbf.yeju.common.core.result.IResult
     */
    @GetMapping("/unauthc")
    public Mono<IResult<Object>> unauthc() throws Exception{

        return  Mono.just(SimpleResult.faild(AuthStatusEnum.NO_TOKEN));
    }

    /**获取图片验证码接口
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/28 15:25
     * @return reactor.core.publisher.Mono<pers.lbf.yeju.common.core.result.IResult<pers.lbf.yeju.authserver.interfaces.dto.VerityDTO<java.lang.Object>>>
     * @throws Exception e
     */
    @GetMapping("/code/image")
    public Mono<IResult<VerityDTO<String>>> getImageCode() throws Exception{

        IResult<VerityDTO<String>> result = codeService.getVerificationCode(VerificationCodeTypeEnum.PICTURE_VERIFICATION_CODE);

        return Mono.just(result);
    }


    /**
     * 获取手机验证码
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/28 16:48
     * @param phoneNumber 手机号
     * @return reactor.core.publisher.Mono<pers.lbf.yeju.common.core.result.IResult<pers.lbf.yeju.authserver.interfaces.dto.VerityDTO<java.lang.String>>>
     * @throws Exception e
     */
    @GetMapping("/code/phone/{phoneNumber}")
    public Mono<IResult<VerityDTO<String>>> getPhoneCode( @PathVariable
                                                              @Valid @NotNull(message = "手机号不能为空")
                                                                      String phoneNumber)
            throws Exception{
        IResult<VerityDTO<String>> result = codeService.getVerificationCode(VerificationCodeTypeEnum.MOBILE_VERIFICATION_CODE);
        return Mono.just(result);
    }

}
