package pers.lbf.yeju.authserver.resource.service;

import org.apache.dubbo.config.annotation.DubboService;
import pers.lbf.yeju.authserver.interfaces.interfaces.IResourcesService;
import pers.lbf.yeju.common.core.result.IResult;

import java.util.ArrayList;


/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResources)表服务实现类
 *
 * @author makejava
 * @since 2020-12-13 09:42:48
 */
@DubboService(interfaceClass = IResourcesService.class)
public class ResourcesServiceImpl implements IResourcesService {

    @Override
    public IResult<ArrayList<String>> findAuthorityListByPrincipal(String principal) {
        return null;
    }
}
