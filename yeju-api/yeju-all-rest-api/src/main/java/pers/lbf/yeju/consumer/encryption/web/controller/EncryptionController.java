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
package pers.lbf.yeju.consumer.encryption.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.consumer.base.util.SubjectHelper;
import pers.lbf.yeju.encryption.service.interfaces.IEncryptionService;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetAesDisponsableKeyResponse;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetRsaPublicDisposableKeyResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/30 14:13
 */
@RestController
@RequestMapping("/encryption")
@Slf4j
@Api(tags = "临时密钥服务接口")
public class EncryptionController {

    @DubboReference
    private IEncryptionService encryptionService;


    @ApiOperation(value = "获取RSA2临时公钥", notes = "说明", httpMethod = "GET")
    @GetMapping("/rsa2")
    public Mono<IResult<GetRsaPublicDisposableKeyResponse>> getRSA2(ServerHttpRequest request) throws ServiceException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        String account = SubjectHelper.getSubjectAccount(request);

        return Mono.just(encryptionService.getRsaPublicDisposableKey(account));
    }


    @ApiOperation(value = "获取AES CBC 临时密钥", notes = "AES CBC 临时密钥说明", httpMethod = "GET")
    @GetMapping("/aes/cbc")
    public Mono<IResult<GetAesDisponsableKeyResponse>> get(ServerHttpRequest request, String clientPublicKey) throws ServiceException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        String account = SubjectHelper.getSubjectAccount(request);
        if (!YejuStringUtils.isNotNUllAndNotEmpty(clientPublicKey)) {
            throw ServiceException.getInstance("客户端公钥不能为空", ParameStatusEnum.invalidParameter.getCode());
        }

        return Mono.just(encryptionService.getAesDisponsableKey(account, clientPublicKey));
    }


}
