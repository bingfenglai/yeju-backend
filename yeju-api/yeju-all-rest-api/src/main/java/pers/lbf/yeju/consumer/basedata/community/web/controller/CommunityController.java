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
package pers.lbf.yeju.consumer.basedata.community.web.controller;

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
import pers.lbf.yeju.service.basedata.community.interfaces.ICommunityService;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityCreateArgs;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityQueryArgs;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityUpdateArgs;
import pers.lbf.yeju.service.basedata.community.pojo.SimpleCommunityInfoBean;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * 社区服务api
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/20 10:45
 */
@RestController
@RequestMapping("/basedata/community")
@Slf4j
@Api(tags = "社区服务Api")
public class CommunityController {

    @DubboReference
    private ICommunityService communityService;


    @ApiOperation(value = "社区综合查询接口", notes = "列表说明", httpMethod = "GET")
    @GetMapping
    public Mono<PageResult<SimpleCommunityInfoBean>> query(@ApiParam("分页查询参数") CommunityQueryArgs args) throws ServiceException {

        return Mono.just(communityService.query(args));
    }

    @ApiOperation(value = "新增社区", notes = "说明", httpMethod = "POST")
    @PostMapping
    public Mono<IResult<Boolean>> create(
            @RequestBody @Validated CommunityCreateArgs args,
            ServerHttpRequest request) throws ServiceException {
        ArgsHelper.createArgsHelper(args, request);
        return Mono.just(communityService.create(args));
    }

    @ApiOperation(value = "修改社区信息", notes = "说明", httpMethod = "PUT")
    @PutMapping
    public Mono<IResult<Boolean>> updateById(
            @RequestBody @Validated @ApiParam("社区修改参数") CommunityUpdateArgs args,
            ServerHttpRequest request) throws ServiceException {
        ArgsHelper.updateArgsHelper(args, request);
        return Mono.just(communityService.updateById(args));
    }

    @ApiOperation(value = "删除", notes = "说明", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public Mono<IResult<Boolean>> deleteById(
            @PathVariable @ApiParam(name = "id") Long id) throws ServiceException {
        return Mono.just(communityService.removeById(id));
    }

    @ApiOperation(value = "批量删除", notes = "说明", httpMethod = "DELETE")
    @DeleteMapping
    public Mono<IResult<Boolean>> deleteBatch(
            @RequestBody @ApiParam(name = "id") Set<Long> ids) throws ServiceException {
        return Mono.just(communityService.removeBatch(ids));
    }
    

}
