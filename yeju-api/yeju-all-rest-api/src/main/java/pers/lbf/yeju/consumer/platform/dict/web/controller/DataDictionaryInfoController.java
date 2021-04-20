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
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.DictQueryArgs;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryInfoBean;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/16 16:42
 */
@RestController
@RequestMapping("/platform/dataDictionary/info")
@Api(tags = "字典数据服务接口")
public class DataDictionaryInfoController {

    @DubboReference
    private IDataDictionaryInfoService dictionaryInfoService;

    @ApiOperation(value = "获取数据字典信息列表 分页", notes = "数据字典列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<SimpleDataDictionaryInfoBean>> findPage(
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);

        return Mono.just(dictionaryInfoService.findPage(args.getCurrentPage(), args.getSize()));
    }

    @ApiOperation(value = "获取列表 分页", notes = "列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}/{size}")
    public Mono<PageResult<SimpleDataDictionaryInfoBean>> findPage(
            @Validated @NotNull(message = "type id is not null") @RequestParam("dictTypeId") Long id,
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @PathVariable Long size) throws ServiceException {

        DictQueryArgs args = new DictQueryArgs();

        args.setCurrentPage(currentPage);
        args.setSize(size);

        args.setDataTypeId(id);
        return Mono.just(dictionaryInfoService.findPage(args));
    }


}
