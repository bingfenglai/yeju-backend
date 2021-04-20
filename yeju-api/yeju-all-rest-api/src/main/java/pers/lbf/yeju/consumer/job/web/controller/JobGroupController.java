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
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.job.IJobGroupService;
import pers.lbf.yeju.service.interfaces.job.pojo.JobGroupInfoBean;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/3 10:33
 */
@RestController
@RequestMapping("/job/jobGroup")
@Api(tags = "定时任务组接口")
public class JobGroupController {

    @DubboReference
    private IJobGroupService jobGroupService;

    @ApiOperation(value = "获取JobGroup", notes = "JobGroup说明", httpMethod = "GET")
    @GetMapping("/getNameAndId")

    public Mono<IResult<List<JobGroupInfoBean>>> getJobGroupNameAndId() throws ServiceException {
        return Mono.just(jobGroupService.getJobGroupNameAndId());
    }

}
