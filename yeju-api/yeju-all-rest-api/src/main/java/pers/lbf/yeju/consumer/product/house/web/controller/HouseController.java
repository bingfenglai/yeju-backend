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
package pers.lbf.yeju.consumer.product.house.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.consumer.base.util.SubjectHelper;
import pers.lbf.yeju.consumer.product.house.args.HouseCheckArgs;
import pers.lbf.yeju.consumer.product.house.sender.HouseCheckLogSender;
import pers.lbf.yeju.service.interfaces.log.pojo.HouseCheckLogCreateArgs;
import pers.lbf.yeju.service.interfaces.product.IHouseInfoService;
import pers.lbf.yeju.service.interfaces.product.pojo.*;
import pers.lbf.yeju.service.interfaces.product.status.HouseStatusEnum;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

/**
 * 商品-房源服务 web api
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/17 17:02
 */
@RestController
@RequestMapping("/product/house")
@Slf4j
@Api(tags = "房源信息服务")
public class HouseController {

    @DubboReference
    private IHouseInfoService houseInfoService;

    @Autowired
    private HouseCheckLogSender houseCheckLogSender;


    @ApiOperation(value = "房源信息综合搜索接口", notes = "说明", httpMethod = "GET")
    @GetMapping("/search")
    public Mono<PageResult<HouseInfoDoc>> search(
            @Validated HouseSearchArgs args) throws ServiceException {
        return Mono.just(houseInfoService.search(args));
    }


    @ApiOperation(value = "获取房源信息列表 分页", notes = "说明", httpMethod = "GET")
    @GetMapping
    public Mono<PageResult<SimpleHouseInfoBean>> query(@ApiParam("分页查询参数") @Validated HouseInfoQueryArgs args) throws ServiceException {

        return Mono.just(houseInfoService.query(args));
    }


    @Deprecated
    @ApiOperation(value = "获取房源信息列表 分页", notes = "房源信息列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<SimpleHouseInfoBean>> findPage(
            @Valid @Positive @ApiParam("当前页必须是一个正数") @PathVariable Long currentPage,
            @Valid @Size(min = 1, max = 50, message = "每页查询大小必须大于1小于50") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);

        return Mono.just(houseInfoService.findPage(args.getCurrentPage(), args.getSize()));
    }


    @ApiOperation(value = "获取房源信息详情", notes = "房源信息详情说明", httpMethod = "GET")
    @GetMapping("/{id}")
    public Mono<IResult<HouseDetailsInfoBean>> getHouseDetailsInfo(
            @PathVariable("id") @Valid @NotNull(message = "房源标识不能为空") String id,
            @RequestParam @Valid @NotNull(message = "房源状态不能为空") String houseStatus
    ) throws ServiceException {
        return Mono.just(houseInfoService.findDetailsByIdAndStatus(Long.valueOf(id), houseStatus));
    }

    @ApiOperation(value = "房源审核接口", notes = "说明", httpMethod = "PUT")
    @PutMapping("/check")
    public Mono<IResult<Boolean>> check(@RequestBody @Validated HouseCheckArgs args, ServerHttpRequest request) throws ServiceException {

        return Mono.just(houseInfoService.verifyById(Long.valueOf(args.getHouseId()), args.getNewHouseStatus()))
                .doOnSuccess(resp -> {

                    // 审核之后，发送审核日志 以便日后进行操作日志审计
                    HouseCheckLogCreateArgs logCreateArgs = houseCheckArgsToLogArgs(args);
                    logCreateArgs.setCheckTime(new Date());
                    try {
                        logCreateArgs.setAccountId(SubjectHelper.getAccountId(request));
                    } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
                        log.error(String.valueOf(e));
                        throw ServiceException.getInstance(e.getMessage(), ServiceStatusEnum.UNKNOWN_ERROR.getCode());
                    }
                    houseCheckLogSender.send(logCreateArgs);

                    //如果是审核通过 则同步房源数据到可交易表、ES
                    // 如果是 审核未通过（下架） 需要检查ES中索引是否存在 存在则需要删除
                    if (HouseStatusEnum.offShelf.getValue().equals(args.getNewHouseStatus())) {
                        houseInfoService.removeByIdFromElasticsearch(args.getHouseId());
                    } else if (HouseStatusEnum.tradable.getValue().equals(args.getNewHouseStatus())) {
                        houseInfoService.copyHouseInfoToTradable(Long.valueOf(args.getHouseId()));
                    }


                });

    }

    private HouseCheckLogCreateArgs houseCheckArgsToLogArgs(HouseCheckArgs args) {
        HouseCheckLogCreateArgs houseCheckLogCreateArgs = new HouseCheckLogCreateArgs();
        houseCheckLogCreateArgs.setHouseId(Long.valueOf(args.getHouseId()));
        houseCheckLogCreateArgs.setHouseOldStatus(args.getOldHouseStatus());
        houseCheckLogCreateArgs.setHouseNewStatus(args.getNewHouseStatus());
        houseCheckLogCreateArgs.setCheckTime(new Date());
        houseCheckLogCreateArgs.setOpinions(args.getOpinions());
        return houseCheckLogCreateArgs;
    }


}
