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
package pers.lbf.yeju.consumer.platform.online.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.util.YejuInfoSecurityUtil;
import pers.lbf.yeju.service.interfaces.session.ISessionService;
import pers.lbf.yeju.service.interfaces.session.pojo.OnlineInfoBean;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 在线用户
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/20 20:49
 */
@RestController
@RequestMapping("platform/online")
@Slf4j
@Api(tags = "在线用户服务接口")
public class OnlineController {

    @DubboReference
    private ISessionService sessionService;


    @ApiOperation(value = "获取在线用户列表 分页", notes = "在线用户列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<OnlineInfoBean>> findPage(
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);
        PageResult<OnlineInfoBean> pageResult = sessionService.findPage(args.getCurrentPage(), args.getSize());
        filterInfo(pageResult.getList());

        return Mono.just(pageResult);


    }

    @ApiOperation(value = "踢人", notes = "OnlineController说明", httpMethod = "DELETE")
    @DeleteMapping("/{principal}")
    public Mono<Void> deleteOnlineByPrincipal(@ApiParam("抽象账户") @PathVariable("principal") String principal) throws ServiceException {
        sessionService.destroySession(principal);
        return Mono.empty();
    }


    public void filterInfo(List<OnlineInfoBean> list) {

        for (OnlineInfoBean onlineInfoBean : list) {
            String sessionId = onlineInfoBean.getSessionId();
            String s = YejuInfoSecurityUtil.around(sessionId, 1, 1);
            log.info(s);
            onlineInfoBean.setSessionId(s);
        }
    }


}
