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
package pers.lbf.yeju.consumer.job.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.job.ITaskSchedulerService;
import pers.lbf.yeju.service.interfaces.job.pojo.JobCreateArgs;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;
import pers.lbf.yeju.service.interfaces.job.pojo.JobUpdateArgs;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/27 19:52
 */
@RestController
@RequestMapping("/job")
@Api(tags = "定时任务服务接口")
public class JobController {

    @DubboReference
    private ITaskSchedulerService jobService;

    @ApiOperation(value = "获取定时任务列表 分页", notes = "定时任务列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<JobInfoBean>> findPage(
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);

        return Mono.just(jobService.findPage(args.getCurrentPage(), args.getSize()));
    }

    @ApiOperation(value = "新增job", notes = "新增job说明", httpMethod = "POST")
    @PostMapping("/create")
    public Mono<IResult<Object>> create(JobCreateArgs args) throws ServiceException {
        return Mono.just(jobService.create(args));
    }

    @ApiOperation(value = "修改Job", notes = "修改Job说明", httpMethod = "PUT")
    @PutMapping("/update")
    public Mono<IResult<Object>> update(JobUpdateArgs args) throws ServiceException {

        return Mono.just(jobService.update(args));
    }

    @ApiOperation(value = "删除Job", notes = "删除Job说明", httpMethod = "DELETE")
    @DeleteMapping("/delete/{id}")
    public Mono<IResult<Object>> deleteJobById(@ApiParam(name = "id")
                                               @Validated @NotNull
                                               @PathVariable("id") Long id) throws ServiceException {
        return Mono.just(jobService.deleteJobByIds(new Long[]{id}));
    }

    @ApiOperation(value = "批量删除Job", notes = "批量删除Job说明", httpMethod = "DELETE")
    @DeleteMapping("/deleteBatch")
    public Mono<IResult<Object>> deleteBatch(@ApiParam(name = "idList") @RequestBody List<Long> idList) throws ServiceException {
        return Mono.just(jobService.deleteJobByIds(idList.toArray(new Long[0])));
    }


}
