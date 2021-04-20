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
package pers.lbf.yeju.consumer.platform.department.web.controlller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.constant.DataDictionaryTypeConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.consumer.platform.department.pojo.DepartmentTreeVO;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;
import pers.lbf.yeju.service.interfaces.platfrom.department.IDepartmentService;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.DepartmentIdAndName;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimpleDepartmentInfoBean;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/15 20:34
 */
@RestController
@RequestMapping("/platform/department")
@Slf4j
@Api(tags = "部门服务接口")
public class DepartmentController {

    @DubboReference
    private IDepartmentService departmentService;

    @DubboReference
    private IDataDictionaryInfoService dataDictionaryInfoService;

    @ApiOperation(value = "获取部门状态列表", notes = "获取部门状态列表", httpMethod = "GET")
    @GetMapping("/status/list")
    public Mono<IResult<List<SimpleLabelAndValueBean>>> getMenuStatusInfoList() throws ServiceException {
        IResult<List<SimpleLabelAndValueBean>> result = dataDictionaryInfoService.findLabelAndValueByType(DataDictionaryTypeConstant.DEPARTMENT_STATUS);
        return Mono.just(result);
    }


    @ApiOperation(value = "获取DepartmentTree", notes = "DepartmentTree说明", httpMethod = "GET")
    @GetMapping("/getDepartmentTree")
    public Mono<IResult<List<DepartmentTreeVO>>> getDepartmentTreeInfo() throws ServiceException {

        List<DepartmentTreeVO> result = null;

        IResult<List<DepartmentIdAndName>> resultDepartmentIdAndName = departmentService.findAll();
        if (resultDepartmentIdAndName.isSuccess()) {
            List<DepartmentIdAndName> all = resultDepartmentIdAndName.getData();
            result = new LinkedList<>();
            List<DepartmentTreeVO> other = new LinkedList<>();
            for (DepartmentIdAndName departmentIdAndName : all) {
                if (departmentIdAndName.getParentId() == 0) {
                    result.add(beanToDepartmentTreeVO(departmentIdAndName));
                } else {
                    other.add(beanToDepartmentTreeVO(departmentIdAndName));
                }

            }

            log.info(result.toString());
            log.info(other.toString());
            for (DepartmentTreeVO parent : result) {
                recursiveDepartmentTree(parent, other);
            }

            return Mono.just(Result.ok(result));
        }


        return Mono.just(Result.ok(result));
    }


    @ApiOperation(value = "获取Department列表 分页", notes = "Department列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<SimpleDepartmentInfoBean>> findPage(
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);

        return Mono.just(departmentService.findPage(args.getCurrentPage(), args.getSize()));
    }


    private DepartmentTreeVO beanToDepartmentTreeVO(DepartmentIdAndName bean) {

        DepartmentTreeVO vo = new DepartmentTreeVO();
        vo.setId(bean.getId());
        vo.setLabel(bean.getName());
        vo.setParentId(bean.getParentId());

        return vo;
    }

    private DepartmentTreeVO recursiveDepartmentTree(DepartmentTreeVO parent, List<DepartmentTreeVO> list) {
        for (DepartmentTreeVO departmentTreeVO : list) {
            if (parent.getId().equals(departmentTreeVO.getParentId())) {
                recursiveDepartmentTree(departmentTreeVO, list);
                parent.addChildren(departmentTreeVO);
            }
        }
        return parent;
    }
}
