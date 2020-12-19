package pers.lbf.yeju.authserver.interfaces.interfaces;

import pers.lbf.yeju.common.core.result.IResult;

import java.util.ArrayList;

/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResouces)表服务接口
 *
 * @author makejava
 * @since 2020-12-13 09:42:43
 */
public interface IResourcesService {

    IResult<ArrayList<String>> findAuthorityListByPrincipal(String principal);
}
