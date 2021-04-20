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
package pers.lbf.yeju.consumer.sms.template.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.consumer.base.util.ArgsHelper;
import pers.lbf.yeju.service.interfaces.sms.interfaces.ISmsTemplateService;
import pers.lbf.yeju.service.interfaces.sms.pojo.SimpleSmsTemplateInfoBean;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateCreateArgs;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateQueryArgs;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateUpdateArgs;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * 短信模板服务接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/15 21:46
 */
@RestController
@RequestMapping("sms/template")
@Slf4j
@Api(tags = "短信模板服务")
public class SmsTemplateController {

    @DubboReference
    private ISmsTemplateService smsTemplateService;

    @ApiOperation(value = "获取短信模板列表 综合条件查询接口", notes = "列表说明", httpMethod = "GET")
    @GetMapping
    public Mono<PageResult<SimpleSmsTemplateInfoBean>> findPage(
            @ApiParam("分页查询参数") @Validated SmsTemplateQueryArgs args) throws ServiceException {

        return Mono.just(smsTemplateService.queryPage(args));
    }

    @ApiOperation(value = "新增短信模板", notes = "说明", httpMethod = "POST")
    @PostMapping
    public Mono<IResult<Boolean>> create(
            @ApiParam("短信模板创建参数") @RequestBody SmsTemplateCreateArgs args,
            ServerHttpRequest request) throws ServiceException {
        ArgsHelper.createArgsHelper(args, request);
        return Mono.just(smsTemplateService.create(args));
    }

    @ApiOperation(value = "修改短信模板", notes = "说明", httpMethod = "PUT")
    @PutMapping
    public Mono<IResult<Boolean>> updateById(
            @RequestBody SmsTemplateUpdateArgs args,
            ServerHttpRequest request) throws ServiceException {
        ArgsHelper.updateArgsHelper(args, request);
        return Mono.just(smsTemplateService.updateById(args));
    }

    @ApiOperation(value = "删除", notes = "说明", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public Mono<IResult<Boolean>> deleteById(
            @ApiParam(name = "id") @PathVariable Long id) throws ServiceException {
        return Mono.just(smsTemplateService.deleteById(id));
    }


    @ApiOperation(value = "删除", notes = "说明", httpMethod = "DELETE")
    @DeleteMapping
    public Mono<IResult<Boolean>> deleteBatch(
            @ApiParam(name = "ids") @RequestBody Set<Long> ids) throws ServiceException {
        return Mono.just(smsTemplateService.removeBatch(ids));
    }


}
