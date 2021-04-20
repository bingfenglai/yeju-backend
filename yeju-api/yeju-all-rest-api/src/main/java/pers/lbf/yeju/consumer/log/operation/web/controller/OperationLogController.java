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
package pers.lbf.yeju.consumer.log.operation.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.log.IOperationLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.OperationLogInfoBean;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/15 16:49
 */
@RestController
@RequestMapping("/log/operationLog")
@Slf4j
@Api(tags = "操作日志查询接口")
public class OperationLogController {

    @DubboReference
    private IOperationLogService operationLogService;

    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<OperationLogInfoBean>> getList(
            @Validated @NotNull @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @RequestParam Long size) {

        return Mono.just(operationLogService.findList(currentPage, size));
    }

}
