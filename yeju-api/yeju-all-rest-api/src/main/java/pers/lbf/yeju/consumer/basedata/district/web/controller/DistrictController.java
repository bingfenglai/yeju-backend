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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.basedata.interfaces.IDistrictService;
import pers.lbf.yeju.service.basedata.pojo.DistrictQueryArgs;
import pers.lbf.yeju.service.basedata.pojo.SimpleDistrictInfoBean;
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
public class DistrictController {

    @DubboReference
    private IDistrictService districtService;


    @ApiOperation(value = "获取地域树", notes = "地域列表说明", httpMethod = "GET")
    @GetMapping("/tree")
    public Mono<IResult<List<SimpleDistrictInfoBean>>> queryTree() throws ServiceException {

        return Mono.just(districtService.queryTree());
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
    @GetMapping("/direct/district{id}")
    public Mono<IResult<List<SimpleDistrictInfoBean>>> getDirectDistrictListById(@PathVariable Long id) throws ServiceException {
        return Mono.just(districtService.findDirectDistrictById(id));
    }


}
