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
package pers.lbf.yeju.consumer.platform.resource;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.consumer.base.security.manger.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.base.security.pojo.AuthorityInfo;
import pers.lbf.yeju.service.interfaces.auth.dto.RouterInfoBean;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/9 15:43
 */
@RestController
@RequestMapping("/platform/menu")
@Slf4j
public class MenuController {

    @DubboReference
    private IResourcesService resourcesService;

    @Autowired
    private AuthorizationTokenManager tokenManager;

    @ApiOperation(value = "获取菜单信息",notes = "菜单信息说明",httpMethod = "GET")
    @GetMapping("/getRouters")
    public Mono<IResult<List<RouterInfoBean>>> getRouters(ServerWebExchange webExchange)throws ServiceException {

        //获取token
        String authorization = Objects.requireNonNull(webExchange.getRequest().getHeaders().get(TokenConstant.TOKEN_KEY)).get(0);
        AuthorityInfo authorityInfo = null;
        try {
           authorityInfo  = tokenManager.getAuthorityInfo(authorization);

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert authorityInfo != null;
        List<String> authorityList = authorityInfo.getAuthorityList();

        log.info("权限字符串：{}",authorityList.toString());

        return Mono.just(resourcesService.getRouters(authorityList));
    }
}
