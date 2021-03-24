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
package pers.lbf.yeju.consumer.customer.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.customer.ICustomerService;
import pers.lbf.yeju.service.interfaces.customer.pojo.CustomerUpdateArgs;
import pers.lbf.yeju.service.interfaces.customer.pojo.SimpleCustomerInfoBean;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/23 9:32
 */
@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @DubboReference
    private ICustomerService customerService;

    @ApiOperation(value = "获取客户信息列表 分页", notes = "客户信息列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<SimpleCustomerInfoBean>> findPage(
            @Valid @Positive @ApiParam("当前页必须是一个正数") @PathVariable Long currentPage,
            @Valid @Size(min = 1, max = 50, message = "每页查询大小必须大于1小于50") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);

        return Mono.just(customerService.findPage(args.getCurrentPage(), args.getSize()));
    }


    @ApiOperation(value = "修改个人信息接口", notes = "说明", httpMethod = "Put")
    @PutMapping
    public Mono<IResult<Boolean>> update(@RequestBody CustomerUpdateArgs args) throws ServiceException {

        return Mono.just(customerService.update(args));
    }


}
