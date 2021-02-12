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
package pers.lbf.yeju.consumer.platform.resource.controller;

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
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.base.security.manger.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.base.security.pojo.AuthorityInfo;
import pers.lbf.yeju.consumer.platform.resource.pojo.vo.MetaVO;
import pers.lbf.yeju.consumer.platform.resource.pojo.vo.RouterVO;
import pers.lbf.yeju.service.interfaces.auth.dto.MenuInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.RouterInfoBean;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;
import reactor.core.publisher.Mono;

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
public class MenuController {

    @DubboReference
    private IResourcesService resourcesService;

    @Autowired
    private AuthorizationTokenManager tokenManager;

    @Deprecated
    @ApiOperation(value = "获取菜单信息",notes = "菜单信息说明",httpMethod = "GET")
    @GetMapping("/getRouters")
    public Mono<IResult<List<RouterInfoBean>>> getRouters(ServerWebExchange webExchange)throws ServiceException {

        List<String> authorityList = getAuthorityList(webExchange);
        IResult<List<RouterInfoBean>> routers = resourcesService.getRouters(authorityList);
        return Mono.just(routers);
    }

    @ApiOperation(value = "获取菜单信息",notes = "菜单信息说明",httpMethod = "GET")
    @GetMapping("/v2/getRouters")
    public Mono<IResult<List<RouterVO>>> getRoutersV2(ServerWebExchange webExchange)throws ServiceException {

        List<String> authorityList = getAuthorityList(webExchange);
        IResult<List<MenuInfoBean>> menuInfoBeanListResult = resourcesService.findAllAuthorizedMenuInfo(authorityList);

        List<MenuInfoBean> menuInfoBeanList;
        if (menuInfoBeanListResult.isSuccess()){
            menuInfoBeanList = menuInfoBeanListResult.getData();
        }else {
            throw new ServiceException();
        }

        List<RouterVO> result = new LinkedList<>();
        List<MenuInfoBean> orMenuListList = new LinkedList<>();
        // 获取顶级路由
        for (MenuInfoBean menuInfoBean : menuInfoBeanList) {
            if (menuInfoBean.getParentMenuId()==0L){
                RouterVO routerVO = new RouterVO();
                if (menuInfoBean.getIsFrame()==0){

                    routerVO.setId(menuInfoBean.getResourceId());
                    routerVO.setRedirect("noRedirect");
                    routerVO.setComponent(menuInfoBean.getComponent());
                    routerVO.setAlwaysShow(true);
                    routerVO.setName(menuInfoBean.getResourceCode());
                    routerVO.setPath(menuInfoBean.getPath());
                    routerVO.setHidden(!menuInfoBean.getVisible());
                    MetaVO meta = new MetaVO();
                    meta.setNoCache(!menuInfoBean.getCache());
                    meta.setTitle(menuInfoBean.getResourceName());
                    meta.setIcon(menuInfoBean.getIcon());
                    routerVO.setMeta(meta);
                }else {
                    routerVO.setId(menuInfoBean.getResourceId());
                    routerVO.setComponent(menuInfoBean.getComponent());
                    routerVO.setName(menuInfoBean.getResourceCode());
                    routerVO.setPath(menuInfoBean.getPath());
                    routerVO.setHidden(!menuInfoBean.getVisible());
                    MetaVO meta = new MetaVO();
                    meta.setNoCache(!menuInfoBean.getCache());
                    meta.setTitle(menuInfoBean.getResourceName());
                    meta.setIcon(menuInfoBean.getIcon());
                    routerVO.setMeta(meta);
                }
                result.add(routerVO);

            }else {
                orMenuListList.add(menuInfoBean);
            }
        }

        List<RouterVO> allRouter = new LinkedList<>();
        for (MenuInfoBean menuInfoBean : orMenuListList) {
            RouterVO routerVO = new RouterVO();
            routerVO.setId(menuInfoBean.getResourceId());
            routerVO.setParentId(menuInfoBean.getParentMenuId());
            routerVO.setComponent(menuInfoBean.getComponent());
            routerVO.setName(menuInfoBean.getResourceCode());
            routerVO.setPath(menuInfoBean.getPath());
            routerVO.setHidden(!menuInfoBean.getVisible());
            MetaVO meta = new MetaVO();
            meta.setNoCache(!menuInfoBean.getCache());
            meta.setTitle(menuInfoBean.getResourceName());
            meta.setIcon(menuInfoBean.getIcon());
            routerVO.setMeta(meta);

            allRouter.add(routerVO);
        }
        log.info("子菜单总数{}",orMenuListList.size());
        log.info("子节点总数{}", allRouter.size());

        //递归获取子节点
        for (RouterVO parent : result) {
           parent = recursiveTree(parent, allRouter);
        }




        return Mono.just(Result.ok(result));
    }

    public RouterVO recursiveTree(RouterVO parent,List<RouterVO> routerList){
        for (RouterVO router : routerList) {
            log.info("父id{} 子的父id{}",parent.getId(),router.getParentId());
            if (parent.getId().equals(router.getParentId())){
                router = recursiveTree(router,routerList);
                parent.addChildren(router);
            }
        }

        return parent;
    }



    private List<String> getAuthorityList(ServerWebExchange webExchange) throws ServiceException{
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

        if (authorityList == null || authorityList.size() == 0) {
            throw  ServiceException.getInstance(AuthStatusEnum.unauthorized);
        }

        return authorityList;
    }




}
