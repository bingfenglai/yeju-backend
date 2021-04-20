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
package pers.lbf.yeju.consumer.swaggerinfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.consumer.config.swagger.SwaggerPropertiesConfig;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;

/**
 * 测试用API
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/15 0:32
 */
@Api(tags = "测试用API")
@RestController
@RequestMapping("/test")
@Validated
public class SwaggerConfigInfoController {

    @ApiModelProperty(value = "swagger config info")
    @Autowired
    private SwaggerPropertiesConfig config;

    @ApiOperation(value = "获取详情", notes = "说明", httpMethod = "GET")
    @GetMapping("/swaggerInfo")
    public Mono<String> getSwaggerInfo() throws ServiceException {
        return Mono.just(config.toString());
    }

    @ApiOperation(value = "获取SwaggerConfigInfoController分页数据",
            notes = "SwaggerConfigInfoController分页接口说明",
            httpMethod = "GET")
    @GetMapping("/getSwaggerConfigInfoControllerPage/{currentPage}")
    public Mono<PageResult> getSwaggerConfigInfoControllerPage(
            @ApiParam(name = "当前页")
            @PathVariable Long currentPage,
            @ApiParam(name = "每页显示条数")
            @RequestParam Long size
    ) throws ServiceException {
        return Mono.empty();
    }


    @ApiOperation(value = "获取SwaggerDetails", notes = "SwaggerDetails说明", httpMethod = "GET")
    @GetMapping("/createSwaggerDetails")
    public Mono<IResult> getSwaggerDetailsInfo(
            @Min(value = 10, message = "hheheheheh")
            @RequestParam Long id
    ) throws ServiceException {

        return Mono.just(SimpleResult.ok());
    }


}
