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
package pers.lbf.yeju.consumer.advertisement.banner.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.consumer.base.util.ArgsHelper;
import pers.lbf.yeju.service.interfaces.advertise.banner.interfaces.IBannerService;
import pers.lbf.yeju.service.interfaces.advertise.banner.pojo.BannerCreateArgs;
import pers.lbf.yeju.service.interfaces.advertise.banner.pojo.BannerQueryArgsBean;
import pers.lbf.yeju.service.interfaces.advertise.banner.pojo.BannerUpdateArgs;
import pers.lbf.yeju.service.interfaces.advertise.banner.pojo.SimpleBannerInfoBean;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * 轮播图服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/2 16:21
 */
@RestController
@RequestMapping("/advertisement/banner")
@Slf4j
@Api(tags = "轮播图服务")
public class BannerController {

    @DubboReference
    private IBannerService bannerService;

    @ApiOperation(value = "条件查询", notes = "BannerController说明", httpMethod = "GET")
    @GetMapping("/list")
    public Mono<PageResult<SimpleBannerInfoBean>> list(@Validated BannerQueryArgsBean args) throws ServiceException {

        return Mono.just(bannerService.queryPage(args));
    }

    @ApiOperation(value = "新增轮播图", notes = "说明", httpMethod = "POST")
    @PostMapping
    public Mono<IResult<Boolean>> create(@RequestBody @Validated BannerCreateArgs args,
                                         ServerHttpRequest request) throws ServiceException {
        ArgsHelper.createArgsHelper(args, request);
        return Mono.just(bannerService.create(args));
    }

    @ApiOperation(value = "修改轮播图", notes = "说明", httpMethod = "PUT")
    @PutMapping
    public Mono<IResult<Boolean>> update(@RequestBody @Validated BannerUpdateArgs args,
                                         ServerHttpRequest request) throws ServiceException {
        ArgsHelper.updateArgsHelper(args, request);
        return Mono.just(bannerService.updateById(args));
    }

    @ApiOperation(value = "批量删除", notes = "说明", httpMethod = "DELETE")
    @DeleteMapping
    public Mono<IResult<Boolean>> removeBatch(@RequestBody Set<Long> ids) throws ServiceException {

        return Mono.just(bannerService.removeBatch(ids));
    }


}
