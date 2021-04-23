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
package pers.lbf.yeju.consumer.integration.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.payment.IIntegrationService;
import pers.lbf.yeju.service.interfaces.payment.pojo.IntegrationFindPageArgs;
import pers.lbf.yeju.service.interfaces.payment.pojo.SimpleIntegrationInfoBean;
import reactor.core.publisher.Mono;

/**
 * 积分服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/25 15:03
 */
@RestController
@RequestMapping("/integration")
@Slf4j
@Api(tags = "积分服务")
public class IntegrationController {

    @DubboReference
    private IIntegrationService integrationService;


    @ApiOperation(value = "获取积分列表 分页", notes = "积分列表说明", httpMethod = "GET")
    @GetMapping("/list")
    public Mono<PageResult<SimpleIntegrationInfoBean>> findPage(
            @Validated IntegrationFindPageArgs args
    ) throws ServiceException {

        return Mono.just(integrationService.findPage(args));
    }


}
