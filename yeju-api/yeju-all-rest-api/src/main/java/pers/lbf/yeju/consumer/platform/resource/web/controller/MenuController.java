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
package pers.lbf.yeju.consumer.platform.resource.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.base.security.authorization.manager.AuthorizationTokenManager;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.constant.DataDictionaryTypeConstant;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.platform.resource.pojo.vo.MetaVO;
import pers.lbf.yeju.consumer.platform.resource.pojo.vo.RouterVO;
import pers.lbf.yeju.service.interfaces.auth.dto.MenuInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.RouterInfoBean;
import pers.lbf.yeju.service.interfaces.auth.enums.ResourceType;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
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
@Api(tags = "菜单服务接口")
public class MenuController {

    @DubboReference
    private IResourcesService resourcesService;

    @DubboReference
    private IDataDictionaryInfoService dataDictionaryInfoService;

    @Autowired
    private AuthorizationTokenManager tokenManager;

    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单说明", httpMethod = "GET")
    @GetMapping("/findAll")
    public Mono<IResult<List<MenuInfoBean>>> findAll() throws ServiceException {

        return Mono.just(resourcesService.findAllMenu());
    }


    @ApiOperation(value = "获取Menu列表 分页", notes = "Menu列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<MenuInfoBean>> findPage(
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);

        return Mono.just(resourcesService.findMenuPage(args.getCurrentPage(), args.getSize()));
    }


    @Deprecated
    @ApiOperation(value = "获取菜单信息", notes = "菜单信息说明", httpMethod = "GET")
    @GetMapping("/getRouters")
    public Mono<IResult<List<RouterInfoBean>>> getRouters(ServerWebExchange webExchange) throws ServiceException {

        List<String> authorityList = getAuthorityList(webExchange);
        IResult<List<RouterInfoBean>> routers = resourcesService.getRouters(authorityList);
        return Mono.just(routers);
    }

    @ApiOperation(value = "获取授权的菜单信息", notes = "菜单信息说明", httpMethod = "GET")
    @GetMapping("/v2/getRouters")
    public Mono<IResult<List<RouterVO>>> getRoutersV2(ServerWebExchange webExchange) throws ServiceException {

        List<String> authorityList = getAuthorityList(webExchange);
        IResult<List<MenuInfoBean>> menuInfoBeanListResult = resourcesService.findAllAuthorizedMenuInfo(authorityList);

        List<MenuInfoBean> menuInfoBeanList;
        if (menuInfoBeanListResult.isSuccess()) {
            menuInfoBeanList = menuInfoBeanListResult.getData();
        } else {
            throw new ServiceException();
        }

        List<RouterVO> result = new LinkedList<>();
        List<MenuInfoBean> orMenuListList = new LinkedList<>();
        // 获取顶级路由
        for (MenuInfoBean menuInfoBean : menuInfoBeanList) {
            if (menuInfoBean.getParentId() == 0L) {
                RouterVO routerVO = new RouterVO();
                if (menuInfoBean.getIsFrame() == 0) {

                    routerVO.setRedirect("noRedirect");
                    routerVO.setAlwaysShow(true);

                }
                routerVO.setId(menuInfoBean.getMenuId());
                routerVO.setComponent(menuInfoBean.getComponent());
                routerVO.setName(menuInfoBean.getResourceCode());
                routerVO.setPath(menuInfoBean.getPath());
                routerVO.setHidden(!menuInfoBean.getVisible());
                MetaVO meta = new MetaVO();
                meta.setNoCache(!menuInfoBean.getCache());
                meta.setTitle(menuInfoBean.getMenuName());
                meta.setIcon(menuInfoBean.getIcon());
                routerVO.setMeta(meta);

                result.add(routerVO);

            } else {
                orMenuListList.add(menuInfoBean);
            }
        }

        List<RouterVO> allRouter = new LinkedList<>();
        for (MenuInfoBean menuInfoBean : orMenuListList) {
            RouterVO routerVO = new RouterVO();
            if (Objects.equals(menuInfoBean.getMenuType(), ResourceType.is_menu_dir.getValue())) {

                routerVO.setRedirect("noRedirect");
                routerVO.setAlwaysShow(true);

            }
            routerVO.setId(menuInfoBean.getMenuId());
            routerVO.setComponent(menuInfoBean.getComponent());
            routerVO.setName(menuInfoBean.getResourceCode());
            routerVO.setPath(menuInfoBean.getPath());
            routerVO.setHidden(!menuInfoBean.getVisible());
            MetaVO meta = new MetaVO();
            meta.setNoCache(!menuInfoBean.getCache());
            meta.setTitle(menuInfoBean.getMenuName());
            meta.setIcon(menuInfoBean.getIcon());
            routerVO.setParentId(menuInfoBean.getParentId());
            routerVO.setMeta(meta);


            allRouter.add(routerVO);
        }
        log.info("子菜单总数{}", orMenuListList.size());
        log.info("子节点总数{}", allRouter.size());

        //递归获取子节点
        for (RouterVO parent : result) {
            parent = recursiveTree(parent, allRouter);
        }


        return Mono.just(Result.ok(result));
    }

    @ApiOperation(value = "获取菜单状态列表", notes = "获取菜单状态列表", httpMethod = "GET")
    @GetMapping("/status/list")
    public Mono<IResult<List<SimpleLabelAndValueBean>>> getMenuStatusInfoList() throws ServiceException {
        IResult<List<SimpleLabelAndValueBean>> result = dataDictionaryInfoService.findLabelAndValueByType(DataDictionaryTypeConstant.RESOURCE_STATUS);
        return Mono.just(result);
    }

    private RouterVO recursiveTree(RouterVO parent, List<RouterVO> routerList) {
        for (RouterVO router : routerList) {
            log.debug("父id{} 子的父id{}", parent.getId(), router.getParentId());
            if (parent.getId().equals(router.getParentId())) {
                recursiveTree(router, routerList);
                parent.addChildren(router);
            }
        }

        return parent;
    }


    private List<String> getAuthorityList(ServerWebExchange webExchange) throws ServiceException {
        //获取token
        String authorization = Objects.requireNonNull(webExchange.getRequest().getHeaders().get(TokenConstant.TOKEN_KEY)).get(0);
        AuthorityInfoBean authorityInfoBean = null;
        try {
            authorityInfoBean = tokenManager.getAuthorityInfo(authorization);

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert authorityInfoBean != null;
        List<String> authorityList = authorityInfoBean.getAuthorityList();

        if (authorityList == null || authorityList.size() == 0) {
            throw ServiceException.getInstance(AuthStatusEnum.unauthorized);
        }

        return authorityList;
    }


}
