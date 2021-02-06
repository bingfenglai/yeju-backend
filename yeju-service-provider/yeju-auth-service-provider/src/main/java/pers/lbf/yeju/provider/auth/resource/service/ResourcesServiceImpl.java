package pers.lbf.yeju.provider.auth.resource.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.provider.auth.account.enums.AccountStatusEnumEnum;
import pers.lbf.yeju.provider.auth.resource.dao.IResourcesDao;
import pers.lbf.yeju.provider.base.util.SubjectUtils;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;

import java.util.List;


/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResources)表服务实现类
 *
 * @author makejava
 * @since 2020-12-13 09:42:48
 */
@DubboService(interfaceClass = IResourcesService.class)
public class ResourcesServiceImpl implements IResourcesService {
    
    @Autowired
    private IResourcesDao resourceDao;

    @Override
    public IResult<List<String>> findAuthorityListByPrincipal(String principal) {
        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);
        List<String> resourceNameList = null;
        if (accountType.equals(SubjectTypeEnum.is_system_account)){
            resourceNameList = resourceDao.findResourceListByAccount(principal);

        }

        if (accountType.equals(SubjectTypeEnum.is_mobile)){
            resourceNameList = resourceDao.findResourceListByPhoneNumber(principal);
        }

        if (accountType.equals(SubjectTypeEnum.is_unknown)){
            throw ServiceException.getInstance(AccountStatusEnumEnum.accountDoesNotExist);
        }

        return Result.ok(resourceNameList);
    }

}
