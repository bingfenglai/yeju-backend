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
package pers.lbf.yeju.consumer.platform.role.web.controller;

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
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleRole;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IRoleService;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/16 14:49
 */
@RestController
@RequestMapping("/platform/role")
@Api(tags = "角色服务接口")
public class RoleController {

    @DubboReference
    private IRoleService roleService;

    @DubboReference
    private IDataDictionaryInfoService dataDictionaryInfoService;


    @ApiOperation(value = "获取Role列表 分页", notes = "Role列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<SimpleRole>> findPage(
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);
        PageResult<SimpleRole> pageResult = roleService.findPage(args.getCurrentPage(), args.getSize());

        Map<String, String> data = dataDictionaryInfoService.getDictMap(DataDictionaryTypeConstant.ROLE_STATUS).getData();
        for (SimpleRole simpleRole : pageResult.getList()) {
            simpleRole.setRoleStatusValueStr(data.get(String.valueOf(simpleRole.getRoleStatus())));
        }


        return Mono.just(pageResult);
    }

    @ApiOperation(value = "获取角色状态列表", notes = "获取角色状态列表", httpMethod = "GET")
    @GetMapping("/status/list")
    public Mono<IResult<List<SimpleLabelAndValueBean>>> getRoleStatusInfoList() throws ServiceException {
        IResult<List<SimpleLabelAndValueBean>> result = dataDictionaryInfoService.findLabelAndValueByType(DataDictionaryTypeConstant.ROLE_STATUS);
        return Mono.just(result);
    }

}
