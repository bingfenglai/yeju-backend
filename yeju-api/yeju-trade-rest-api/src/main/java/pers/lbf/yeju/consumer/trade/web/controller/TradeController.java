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
package pers.lbf.yeju.consumer.trade.web.controller;

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
import pers.lbf.yeju.service.interfaces.trade.ITradeService;
import pers.lbf.yeju.service.interfaces.trade.pojo.HouseTradeCreateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.SimpleTradeInfoBean;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeQueryArgs;
import reactor.core.publisher.Mono;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 14:21
 */
@RestController
@RequestMapping("/trade")
@Slf4j
@Api(tags = "交易服务api")
public class TradeController {

    @DubboReference
    private ITradeService tradeService;

    @ApiOperation(value = "获取交易信息列表 分页", notes = "说明", httpMethod = "GET")
    @GetMapping("/query/list")
    public Mono<PageResult<SimpleTradeInfoBean>> query(@ApiParam("分页查询参数") @Validated TradeQueryArgs args) throws ServiceException {

        return Mono.just(tradeService.queryPage(args));
    }

    @ApiOperation(value = "创建交易", notes = "说明", httpMethod = "POST")
    @PostMapping
    public Mono<IResult<String>> create(ServerHttpRequest request,
                                        @RequestBody @Validated HouseTradeCreateArgs args) throws ServiceException {
        ArgsHelper.createArgsHelper(args, request);
        return Mono.just(tradeService.createHouseTrade(args));
    }


}
