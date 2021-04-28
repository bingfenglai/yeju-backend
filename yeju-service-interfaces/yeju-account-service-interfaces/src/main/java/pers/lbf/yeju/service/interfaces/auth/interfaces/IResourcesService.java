package pers.lbf.yeju.service.interfaces.auth.interfaces;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.auth.dto.*;

import java.util.List;

/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResouces)表服务接口
 *
 * @author makejava
 * @since 2020-12-13 09:42:43
 */
public interface IResourcesService {

    IResult<List<MenuInfoBean>> findAllMenu() throws ServiceException;

    IResult<List<MenuInfoBean>> findAllAuthorizedMenuInfo(List<String> authorities) throws ServiceException;


    /** TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:36
     * @param args
     * @return
     */
    void createAuthority(CreateAuthorityArgs args) throws ServiceException;

    /** TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:37
     * @param args
     * @return
     */
    IResult<AuthorityInfoBean> findAuthorityPage(FindPageArgs args) throws ServiceException;

    /** TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:37
     * @param id
     * @return
     */
    void deleteResource(Long... id) throws ServiceException;

    /** TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:37
     * @param currentPage
     * @param size
     * @return
     */
    PageResult<MenuInfoBean> findMenuPage(Long currentPage, Long size) throws ServiceException;

    /** TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:37
     * @param args
     * @return
     */
    void createMenu(CreateMenuArgs args) throws ServiceException;



    /** 根据账号获取权限信息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/12 1:20
     * @param principal 账号
     * @return list authorities
     */
    IResult<List<String>> findAuthorityListByPrincipal(String principal) throws ServiceException;

    @Deprecated
    IResult<List<MenuInfoBean>> getMenus(List<String> authorities) throws ServiceException;



    /** 根据权限信息获取控制台路由
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/10 22:55
     * @param authorities 权限信息
     * @return RouterInfoBeanList 路由列表
     */
    @Deprecated
    IResult<List<RouterInfoBean>> getRouters(List<String> authorities) throws ServiceException;
}
