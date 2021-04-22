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
import pers.lbf.yeju.service.interfaces.trade.ITradeEvaluationService;
import pers.lbf.yeju.service.interfaces.trade.pojo.SimpleTradeEvaluationInfoBean;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeEvaluationCreateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeEvaluationQueryArgs;
import reactor.core.publisher.Mono;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 23:51
 */
@RestController
@RequestMapping("trade/evaluation")
@Slf4j
@Api(tags = "交易评价服务接口")
public class TradeEvaluationController {

    @DubboReference
    private ITradeEvaluationService tradeEvaluationService;

    @ApiOperation(value = "获取列表 分页", notes = "说明", httpMethod = "GET")
    @GetMapping("query/list")
    public Mono<PageResult<SimpleTradeEvaluationInfoBean>> query(@ApiParam("分页查询参数") @Validated TradeEvaluationQueryArgs args) throws ServiceException {
        return Mono.just(tradeEvaluationService.queryPage(args));
    }


    @ApiOperation(value = "删除", notes = "说明", httpMethod = "DELETE")
    @DeleteMapping("/remove/{id}")
    public Mono<IResult<Boolean>> deleteById(
            @PathVariable @ApiParam(name = "id") Long id) throws ServiceException {
        return Mono.just(tradeEvaluationService.removeById(id));
    }

    @ApiOperation(value = "新增", notes = "说明", httpMethod = "POST")
    @PostMapping("/create")
    public Mono<IResult<Boolean>> create(@RequestBody @Validated TradeEvaluationCreateArgs args, ServerHttpRequest request) throws ServiceException {
        ArgsHelper.createArgsHelper(args, request);
        return Mono.just(tradeEvaluationService.create(args));
    }


}
