package pers.lbf.yeju.service.interfaces.auth.interfaces;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.auth.dto.MenuInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.RouterInfoBean;

import java.util.List;

/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResouces)表服务接口
 *
 * @author makejava
 * @since 2020-12-13 09:42:43
 */
public interface IResourcesService {

    IResult<List<String>> findAuthorityListByPrincipal(String principal);

    IResult<List<MenuInfoBean>> getMenus(List<String> authorities) throws ServiceException;



    /** 根据权限信息获取控制台路由
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/10 22:55
     * @param authorities 权限信息
     * @return RouterInfoBeanList 路由列表
     */
    IResult<List<RouterInfoBean>> getRouters(List<String> authorities) throws ServiceException;
}
