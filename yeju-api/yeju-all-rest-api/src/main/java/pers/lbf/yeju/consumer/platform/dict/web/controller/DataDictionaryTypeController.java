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
package pers.lbf.yeju.consumer.platform.dict.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.constant.DataDictionaryTypeConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryTypeService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryTypeBean;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/16 16:59
 */
@RestController
@RequestMapping("/platform/dataDictionary/type")
@Api(tags = "数据字典服务接口")
public class DataDictionaryTypeController {

    @DubboReference
    private IDataDictionaryTypeService dictionaryTypeService;

    @DubboReference
    private IDataDictionaryInfoService dataDictionaryInfoService;

    @ApiOperation(value = "获取数据字典信息列表 分页", notes = "数据字典列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<SimpleDataDictionaryTypeBean>> findPage(
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);

        return Mono.just(dictionaryTypeService.findPage(args.getCurrentPage(), args.getSize()));
    }


    @ApiOperation(value = "获取通用状态列表", notes = "获取通用状态列表", httpMethod = "GET")
    @GetMapping("/status/list")
    public Mono<IResult<List<SimpleLabelAndValueBean>>> getMenuStatusInfoList() throws ServiceException {
        IResult<List<SimpleLabelAndValueBean>> result = dataDictionaryInfoService.findLabelAndValueByType(DataDictionaryTypeConstant.STATUS);
        return Mono.just(result);
    }

    @ApiOperation(value = "获取通用状态列表", notes = "获取通用状态列表", httpMethod = "GET")
    @GetMapping("/{type}/list")
    public Mono<IResult<List<SimpleLabelAndValueBean>>> getLabelAndValueList(
            @ApiParam("字典类型") @Validated @NotNull(message = "字典类型不能为空") @PathVariable("type") String type
    ) throws ServiceException {
        IResult<List<SimpleLabelAndValueBean>> result = dataDictionaryInfoService.findLabelAndValueByType(type);
        return Mono.just(result);
    }


}
