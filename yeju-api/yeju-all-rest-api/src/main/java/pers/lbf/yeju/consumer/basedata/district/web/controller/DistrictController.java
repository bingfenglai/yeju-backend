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
package pers.lbf.yeju.consumer.basedata.district.web.controller;

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
import pers.lbf.yeju.service.basedata.district.interfaces.IDistrictService;
import pers.lbf.yeju.service.basedata.district.pojo.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/18 14:09
 */
@RestController
@RequestMapping("/basedata/district")
@Slf4j
@Api(tags = "地域服务")
public class DistrictController {

    @DubboReference
    private IDistrictService districtService;

    @ApiOperation(value = "根据id查找地域选项", notes = "说明", httpMethod = "GET")
    @GetMapping("/option/{id}")
    public Mono<IResult<DistrictNameAndIdVO>> getDistrictOptionById(@PathVariable Long id) throws ServiceException {
        return Mono.just(districtService.getDistrictNameAndIdListById(id));
    }

    @ApiOperation(value = "根据父节点ID获取地域选项", notes = "地域选项说明", httpMethod = "GET")
    @GetMapping("/option/parent/{id}")
    public Mono<IResult<List<DistrictNameAndIdVO>>> getDistrictOptionList(@PathVariable Long id) throws ServiceException {
        return Mono.just(districtService.getDistrictNameAndIdListByParentId(id));
    }

    @ApiOperation(value = "新增地域", notes = "说明", httpMethod = "POST")
    @PostMapping
    public Mono<IResult<Boolean>> create(
            @ApiParam("地域创建参数封装类") @RequestBody @Validated DistrictCreateArgs args, ServerHttpRequest request) throws ServiceException {
        ArgsHelper.createArgsHelper(args, request);
        return Mono.just(districtService.create(args));
    }

    @ApiOperation(value = "修改地域", notes = "说明", httpMethod = "PUT")
    @PutMapping
    public Mono<IResult<Boolean>> updateById(@RequestBody DistrictUpdateArgs args, ServerHttpRequest request) throws ServiceException {
        ArgsHelper.updateArgsHelper(args, request);
        return Mono.just(districtService.update(args));
    }


    @ApiOperation(value = "获取地域树", notes = "地域列表说明", httpMethod = "GET")
    @GetMapping("/tree")
    public Mono<IResult<List<SimpleDistrictInfoBean>>> queryTree() throws ServiceException {

        return Mono.just(districtService.queryTree());
    }

    @ApiOperation(value = "获取地域树", notes = "地域列表说明", httpMethod = "GET")
    @GetMapping("/tree/v2")
    public Mono<IResult<List<SimpleDistrictInfoBean>>> queryTreeV2(@Validated DistrictQueryArgs args) throws ServiceException {

        return Mono.just(districtService.queryTree(args));
    }

    @ApiOperation(value = "获取地域名称", notes = "地域名称说明", httpMethod = "GET")
    @GetMapping("/name/{id}")
    public Mono<IResult<String>> getCityNameByCityId(@PathVariable Long id) throws ServiceException {

        return Mono.just(districtService.findCityNameByCityId(id));
    }

    @ApiOperation(value = "综合查询接口", notes = "说明", httpMethod = "GET")
    @GetMapping
    public Mono<PageResult<SimpleDistrictInfoBean>> query(
            @ApiParam("综合查询接口封装类") @Validated DistrictQueryArgs args) throws ServiceException {
        return Mono.just(districtService.query(args));
    }


    @ApiOperation(value = "获取直接子地域节点", notes = "直接子地域节点说明", httpMethod = "GET")
    @GetMapping("/direct/{id}")
    public Mono<IResult<List<SimpleDistrictInfoBean>>> getDirectDistrictListById(@PathVariable Long id) throws ServiceException {
        return Mono.just(districtService.findDirectDistrictById(id));
    }


}
