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
package pers.lbf.yeju.consumer.product.collection.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.consumer.base.util.ArgsHelper;
import pers.lbf.yeju.service.interfaces.product.IProductCollectionService;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseCollectionCreateArgs;
import reactor.core.publisher.Mono;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/24 21:28
 */
@RestController
@RequestMapping("/product/collection")
@Slf4j
@Api("房源收藏服务Api")
public class CollectionController {

    @DubboReference
    private IProductCollectionService collectionService;


    @ApiOperation(value = "新增收藏", notes = "说明", httpMethod = "POST")
    @PostMapping
    public Mono<IResult<Boolean>> create(HouseCollectionCreateArgs args, ServerHttpRequest request) throws ServiceException {
        ArgsHelper.createArgsHelper(args, request);
        return Mono.just(collectionService.create(args));
    }

    @ApiOperation(value = "取消收藏", notes = "说明", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public Mono<IResult<Boolean>> deleteById(
            @PathVariable @ApiParam(name = "id") Long id) throws ServiceException {
        return Mono.just(collectionService.cancelCollectionById(id));
    }


}
