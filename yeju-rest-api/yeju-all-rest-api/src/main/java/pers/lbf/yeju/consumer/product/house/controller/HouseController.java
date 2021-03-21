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
package pers.lbf.yeju.consumer.product.house.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.product.IHouseInfoService;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.product.pojo.SimpleHouseInfoBean;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/17 17:02
 */
@RestController
@RequestMapping("/product/house")
@Slf4j
@Api(description = "房源信息服务")
public class HouseController {

    @DubboReference
    private IHouseInfoService houseInfoService;

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
            @Valid @NotNull(message = "房源标识不能为空") @PathVariable("id") String id,
            @Valid @NotNull(message = "房源状态不能为空") @RequestParam String houseStatus
    ) throws ServiceException {
        return Mono.just(houseInfoService.findDetailsByIdAndStatus(Long.valueOf(id), houseStatus));
    }
    

}
