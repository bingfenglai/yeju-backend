package pers.lbf.yeju.service.interfaces.auth.interfaces;

import pers.lbf.yeju.common.core.result.IResult;

import java.util.List;

/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResouces)表服务接口
 *
 * @author makejava
 * @since 2020-12-13 09:42:43
 */
public interface IResourcesService {

    IResult<List<String>> findAuthorityListByPrincipal(String principal);
}
