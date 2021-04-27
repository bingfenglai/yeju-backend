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
package pers.lbf.yeju.consumer.customer.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.util.PhoneUtils;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.customer.ICustomerService;
import pers.lbf.yeju.service.interfaces.customer.pojo.CustomerRegisteringArgs;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 客户注册接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/23 21:21
 */
@RestController
@RequestMapping("/customer/register")
@Slf4j
@Api(tags = "客户注册接口")
public class RegisterCustomerController {

    @DubboReference
    private ICustomerService customerService;

    @DubboReference
    private IAccountService accountService;

    @ApiOperation(value = "客户注册", notes = "说明", httpMethod = "POST")
    @PostMapping
    public Mono<IResult<Boolean>> getInfo(@RequestBody CustomerRegisteringArgs args) throws ServiceException {
        args.setCreateTime(new Date());

        return Mono.just(customerService.registering(args));
    }

    @ApiOperation(value = "判断手机号是否已被注册接口", notes = "说明", httpMethod = "GET")
    @GetMapping("/check/{phoneNumber}")
    public Mono<IResult<Boolean>> checkPhoneNumberIsExist(@PathVariable String phoneNumber) throws ServiceException {
        boolean flag = PhoneUtils.isPhone(phoneNumber);
        if (!flag) {
            throw ServiceException.getInstance("手机号格式不正确", ParameStatusEnum.invalidParameter.getCode());
        }
        return Mono.just(accountService.isExitPhoneNumber(phoneNumber));
    }
}
