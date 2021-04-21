package pers.lbf.yeju.provider.auth.resource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.AntPathMatcher;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.common.domain.entity.Resource;
import pers.lbf.yeju.provider.auth.account.enums.AccountStatusEnum;
import pers.lbf.yeju.provider.auth.resource.dao.IResourcesDao;
import pers.lbf.yeju.provider.auth.resource.enums.ResourceStatus;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.base.util.SubjectUtils;
import pers.lbf.yeju.service.interfaces.auth.dto.*;
import pers.lbf.yeju.service.interfaces.auth.enums.ResourceType;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResources)表服务实现类
 *
 * @author makejava
 * @since 2020-12-13 09:42:48
 */
@DubboService(interfaceClass = IResourcesService.class)
public class ResourcesServiceImpl implements IResourcesService {

    /**
     * 权限匹配
     */
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private IResourcesDao resourceDao;

    /**
     * 查询所有菜单资源
     *
     * @param
     * @return pers.lbf.yeju.common.core.result.IResult<java.util.List < pers.lbf.yeju.service.interfaces.auth.dto.MenuInfoBean>>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/16 16:32
     */
    @Cacheable(cacheNames = "resourcesService:menu:all", keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<List<MenuInfoBean>> findAllMenu() throws ServiceException {

        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_status", 1);
        //目录
        queryWrapper.eq("resource_type", 0)
                .or()
                //菜单项
                .eq("resource_type", 3)
                .or()
                //按钮
                .eq("resource_type", 2);
        List<Resource> resources = resourceDao.selectList(queryWrapper);
        List<MenuInfoBean> result = new LinkedList<>();
        for (Resource resource : resources) {
            MenuInfoBean bean = buildMenuInfoBean(resource);
            result.add(bean);
        }

        return Result.ok(result);
    }


    /**
     * 查询所有授权的路由
     *
     * @param authorities
     * @return
     * @throws ServiceException
     */
    @Cacheable(cacheNames = "resourcesService:menu:authz", keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<List<MenuInfoBean>> findAllAuthorizedMenuInfo(List<String> authorities) throws ServiceException {

        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type", ResourceType.is_menu_dir.getValue())
                .or()
                .eq("resource_type", ResourceType.is_menu.getValue());
        queryWrapper.eq("resource_status", ResourceStatus.able.getValue());
        queryWrapper.orderByAsc("order_number");
        List<Resource> resources = resourceDao.selectList(queryWrapper);

        List<MenuInfoBean> menuInfoBeanList = new LinkedList<>();
        List<Resource> authorizedResources = filterByAuthorized(resources, authorities);

        if (authorizedResources.size() == 0) {
            throw ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);
        }
        for (Resource resource : authorizedResources) {
            MenuInfoBean menuInfoBean = new MenuInfoBean();
            menuInfoBean.setIsFrame(resource.getIsFrame());
            menuInfoBean.setMenuId(resource.getResourceId());
            menuInfoBean.setMenuName(resource.getResourceName());
            menuInfoBean.setResourceCode(resource.getResourceCode());
            menuInfoBean.setParentId(resource.getParentMenuId());
            menuInfoBean.setOrderNumber(resource.getOrderNumber());
            menuInfoBean.setPath(resource.getPath());
            menuInfoBean.setComponent(resource.getComponetPath());
            menuInfoBean.setCache(resource.getIsCache() == 1);
            menuInfoBean.setResourceStatus(resource.getResourceStatus());
            menuInfoBean.setVisible(resource.getVisible() == 1);
            menuInfoBean.setIcon(resource.getIcon());
            menuInfoBean.setMenuType(resource.getResourceType());
            menuInfoBeanList.add(menuInfoBean);

        }

        return Result.ok(menuInfoBeanList);
    }

    /**
     * TODO
     *
     * @param args
     * @return
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:36
     */
    @CacheEvict(cacheNames = {
            "resourcesService:menu:authz",
            "resourcesService:authz:list"}, allEntries = true)
    @Override
    public void createAuthority(CreateAuthorityArgs args) throws ServiceException {

    }

    /**
     * TODO
     *
     * @param args
     * @return
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:37
     */
    @Cacheable(cacheNames = "resourcesService:authz:list", keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<AuthorityInfoBean> findAuthorityPage(FindPageArgs args) throws ServiceException {
        return null;
    }

    /**
     * TODO
     *
     * @param id
     * @return
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:37
     */
    @Override
    @CacheEvict(cacheNames = {
            "resourcesService"}, allEntries = true)
    public void deleteResource(Long... id) throws ServiceException {

    }

    /**
     * @param currentPage
     * @param size
     * @return
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:37
     */
    @Override
    @Cacheable(cacheNames = "resourcesService:menu:list", keyGenerator = "yejuKeyGenerator")
    public PageResult<MenuInfoBean> findMenuPage(Long currentPage, Long size) throws ServiceException {

        Page<Resource> page = PageUtil.getPage(Resource.class, currentPage, size);
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_status", 1);
        //目录
        queryWrapper.eq("resource_type", 0)
                .or()
                //菜单项
                .eq("resource_type", 3)
                .or()
                //按钮
                .eq("resource_type", 2);
        Page<Resource> resourcePage = resourceDao.selectPage(page, queryWrapper);
        List<Resource> resourceList = resourcePage.getRecords();
        List<MenuInfoBean> result = new LinkedList<>();

        for (Resource resource : resourceList) {
            MenuInfoBean bean = buildMenuInfoBean(resource);
            result.add(bean);
        }

        return PageResult.ok(page.getTotal(), currentPage, size, result);
    }

    /**
     * TODO
     *
     * @param args
     * @return
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:37
     */
    @Override
    @CacheEvict(cacheNames = "resourcesService:menu:list", allEntries = true)
    public void createMenu(CreateMenuArgs args) throws ServiceException {

    }

    /**
     * 根据账号查询权限标识符
     *
     * @param principal
     * @return pers.lbf.yeju.common.core.result.IResult<java.util.List < java.lang.String>>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 15:50
     */
    @Cacheable(cacheNames = "resourcesService:authz", key = "#principal")
    @Override
    public IResult<List<String>> findAuthorityListByPrincipal(String principal) throws ServiceException {

        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);
        List<String> resourceNameList = null;
        if (accountType.equals(SubjectTypeEnum.is_system_account)) {
            resourceNameList = resourceDao.findResourceListByAccount(principal);

        }

        if (accountType.equals(SubjectTypeEnum.is_mobile)) {
            resourceNameList = resourceDao.findResourceListByPhoneNumber(principal);
        }

        if (accountType.equals(SubjectTypeEnum.is_unknown)) {
            throw ServiceException.getInstance(AccountStatusEnum.accountDoesNotExist);
        }

        return Result.ok(resourceNameList);
    }


    @Cacheable(cacheNames = "menu", key = "#authorities")
    @Override
    public IResult<List<MenuInfoBean>> getMenus(List<String> authorities) throws ServiceException {
        if (authorities != null && !authorities.isEmpty()) {
            // 1. 查询顶级菜单
            QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("resource_type", ResourceType.is_menu_dir.getValue())
                    .or()
                    .eq("resource_type", ResourceType.is_menu.getValue());
            queryWrapper.eq("resource_status", ResourceStatus.able.getValue());
            queryWrapper.eq("parent_menu_id", 0);
            List<Resource> resources = resourceDao.selectList(queryWrapper);

            // 1.1 筛选与权限匹配的顶级菜单信息
            List<Resource> tempList = null;
            if (resources != null && resources.size() > 0) {

                tempList = filterByAuthorized(resources, authorities);

                List<MenuInfoBean> menuInfoBeanList = new ArrayList<>();

                for (Resource resource : tempList) {
                    menuInfoBeanList.add(this.buildMenuInfoBean(resource));
                }

                // 2. 获取子菜单
                List<MenuInfoBean> list = getChildrenMenus(menuInfoBeanList);

                return Result.ok(list);

            }

            throw ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);

        }

        throw ServiceException.getInstance("权限表标识符不能为空", ParameStatusEnum.Parameter_cannot_be_empty.getCode());
    }


    /**
     * 构建二级菜单
     *
     * @param menuInfoBeanList
     * @return
     */
    @Deprecated
    private List<RouterInfoBeanChild> buildRouterInfoBeanChild(List<MenuInfoBean> menuInfoBeanList) {
        List<RouterInfoBeanChild> childrenList = new ArrayList<>();
        for (MenuInfoBean menuInfoBean : menuInfoBeanList) {
            RouterInfoBeanChild child = new RouterInfoBeanChild();
            child.setName(menuInfoBean.getPath());
            child.setPath(menuInfoBean.getPath());
            child.setHidden(false);
            child.setComponent(menuInfoBean.getComponent());
            Meta meta = new Meta();
            meta.setTitle(menuInfoBean.getMenuName());
            meta.setIcon(menuInfoBean.getIcon());
            meta.setNoCache(false);
            child.setMeta(meta);

            childrenList.add(child);

        }
        return childrenList;
    }


    private MenuInfoBean buildMenuInfoBean(Resource resource) {
        MenuInfoBean menuInfoBean = new MenuInfoBean();
        menuInfoBean.setMenuId(resource.getResourceId());
        menuInfoBean.setMenuName(resource.getResourceName());
        menuInfoBean.setResourceCode(resource.getResourceCode());
        menuInfoBean.setParentId(resource.getParentMenuId());
        menuInfoBean.setOrderNumber(resource.getOrderNumber());
        menuInfoBean.setPath(resource.getPath());
        menuInfoBean.setComponent(resource.getComponetPath());
        menuInfoBean.setCache(resource.getIsCache() == 1);
        menuInfoBean.setResourceStatus(resource.getResourceStatus());
        menuInfoBean.setVisible(resource.getVisible() == 1);
        menuInfoBean.setIcon(resource.getIcon());

        return menuInfoBean;
    }

    /**
     * 筛选与权限匹配的菜单信息
     *
     * @param resources   顶级菜单列表
     * @param authorities 权限字符串列表
     * @return java.util.List<pers.lbf.yeju.common.domain.entity.Resource>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/9 14:44
     */
    private List<Resource> filterByAuthorized(List<Resource> resources, List<String> authorities) {


        ArrayList<Resource> list = new ArrayList<>();

        for (String authorized : authorities) {
            for (Resource resource : resources) {
                if (resource.getResourceCode() != null && "".equals(resource.getResourceCode())) {
                    boolean flag = antPathMatcher.match(authorized, resource.getResourceCode());
                    if (flag) {
                        list.add(resource);

                    }

                }
                list.add(resource);
            }
        }


        return list;
    }


    /**
     * 获取菜单的子菜单
     *
     * @param menuInfoBeanList 父类菜单列表
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/9 14:47
     */
    @Deprecated
    private List<MenuInfoBean> getChildrenMenus(List<MenuInfoBean> menuInfoBeanList) {
        LinkedList<Long> parentIds = new LinkedList<>();
        for (MenuInfoBean menuInfoBean : menuInfoBeanList) {
            parentIds.add(menuInfoBean.getMenuId());
        }

        List<Resource> childrenResources = resourceDao.findChildrenResources(parentIds);

        for (MenuInfoBean menuInfoBean : menuInfoBeanList) {

            for (Resource childrenResource : childrenResources) {
                if (menuInfoBean.getMenuId().equals(childrenResource.getParentMenuId())) {
                    menuInfoBean.addChildren(buildMenuInfoBean(childrenResource));
                }
            }

        }
        return menuInfoBeanList;

    }


    @Cacheable(cacheNames = "routers", key = "#authorities.toArray()")
    @Override
    public IResult<List<RouterInfoBean>> getRouters(List<String> authorities) throws ServiceException {
        IResult<List<MenuInfoBean>> menuInfoBeanListResult = this.getMenus(authorities);
        List<MenuInfoBean> menuInfoBeanList = menuInfoBeanListResult.getData();
        List<RouterInfoBean> routerInfoBeanList = new ArrayList<>();
        for (MenuInfoBean menuInfoBean : menuInfoBeanList) {
            RouterInfoBean routerInfoBean = buildRouterInfoBean(menuInfoBean);
            routerInfoBeanList.add(routerInfoBean);
        }

        return Result.ok(routerInfoBeanList);
    }


    /**
     * 构建一级菜单
     *
     * @param resource
     * @return
     */
    private RouterInfoBean buildRouterInfoBean(MenuInfoBean resource) {
        RouterInfoBean bean = new RouterInfoBean();
        bean.setName(resource.getResourceCode());
        bean.setPath(resource.getPath());
        bean.setHidden(false);
        bean.setComponent(resource.getComponent());
        bean.setAlwaysShow(true);
        Meta meta = new Meta();
        meta.setTitle(resource.getMenuName());
        meta.setIcon(resource.getIcon());
        meta.setNoCache(false);

        bean.setMeta(meta);

        if (resource.getChildren().size() > 0) {
            bean.setChildren(this.buildRouterInfoBeanChild(resource.getChildren()));
        }


        return bean;

    }


}
