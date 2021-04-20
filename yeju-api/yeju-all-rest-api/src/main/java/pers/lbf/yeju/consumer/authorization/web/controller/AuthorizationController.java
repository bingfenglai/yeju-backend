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
package pers.lbf.yeju.consumer.authorization.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.consumer.authorization.sender.SynchronousAuthorizedToAccountSender;
import pers.lbf.yeju.consumer.base.util.ArgsHelper;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAuthorizationService;
import pers.lbf.yeju.service.interfaces.auth.pojo.AuthorizedCreatArgs;
import pers.lbf.yeju.service.interfaces.auth.pojo.SynchronousAuthorizedCreateArgs;
import reactor.core.publisher.Mono;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/11 23:29
 */
@RestController
@RequestMapping("/authorization")
@Slf4j
@Api(tags = "授权服务")
public class AuthorizationController {

    @DubboReference
    private IAuthorizationService authorizationService;

    @Autowired
    private SynchronousAuthorizedToAccountSender syncSender;

    @ApiOperation(value = "对角色授权接口", notes = "对角色授权接口说明", httpMethod = "POST")
    @PostMapping("/authorize")
    public Mono<IResult<Boolean>> authorize(@RequestBody AuthorizedCreatArgs args, ServerWebExchange webExchange) throws ServiceException {
        log.info(args.toString());
        ArgsHelper.createArgsHelper(args, webExchange);
        log.info(args.toString());
        return Mono.just(authorizationService.authorizedToRole(args))
                .doOnSuccess(unused -> {
                    // 同步 r_t_account_resource表 优化查询
                    SynchronousAuthorizedCreateArgs syncArgs = authorizedCreatArgsToSynchronousAuthorizedCreateArgs(args);
                    syncSender.send(syncArgs);
                });
    }


    private SynchronousAuthorizedCreateArgs authorizedCreatArgsToSynchronousAuthorizedCreateArgs(
            AuthorizedCreatArgs args) {
        SynchronousAuthorizedCreateArgs synchronousAuthorizedCreateArgs = new SynchronousAuthorizedCreateArgs();
        synchronousAuthorizedCreateArgs.setAuthorityIdList(args.getAuthorityIdList());
        synchronousAuthorizedCreateArgs.setRoleId(args.getRoleId());
        synchronousAuthorizedCreateArgs.setCreateTime(args.getCreateTime());
        synchronousAuthorizedCreateArgs.setCreateBy(args.getCreateBy());
        return synchronousAuthorizedCreateArgs;

    }

}
