package pers.lbf.yeju.provider.auth.session.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.constant.ServiceStatusConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.auth.dto.AccountDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.SessionDetails;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IRoleService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.ISessionService;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 20:05
 */
@DubboService(interfaceClass = ISessionService.class)
@Service
@Slf4j

public class SessionServiceImpl implements ISessionService {

    @DubboReference
    private IAccountService accountService;

    @DubboReference
    private IRoleService roleService;

    @DubboReference
    private IResourcesService resourcesService;

    /**
     * 登出方法，销毁会话信息
     *
     * @param principal 账号
     * @return r
     */
    @CacheEvict(cacheNames = "yeju:session",key = "#principal")
    @Override
    public void destroySession(String principal) {

    }

    /** 初始化会话信息，认证成功后将会话信息存入redis
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/5 14:20
     * @param principal 员工账号、用户手机号
     * @return void
     */
    @Cacheable(cacheNames = "yeju:session",key = "#principal")
    @Override
    public SessionDetails initSession(String principal) throws ServiceException {
        SessionDetails sessionDetails = new SessionDetails();

        // 1. 查询用户详情信息
        IResult<AccountDetailsInfoBean> accountResult =
                accountService.findAccountDetailsByPrincipal(principal);

        if (accountResult.getCode().equals(ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE)){
            sessionDetails.setAccountDetailsInfo(accountResult.getData());
        }

        // 2. 查询账户关联的角色
        IResult<List<String>> roleListResult = roleService.getRoleListByPrincipal(principal);

        if (roleListResult.getCode().equals(ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE)){
            sessionDetails.setRoles(roleListResult.getData());
        }
        // 3. 查询账户（角色）关联的资源
        IResult<List<String>> resourceListResult = resourcesService.findAuthorityListByPrincipal(principal);
        if(resourceListResult.getCode().equals(ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE)){
            sessionDetails.setResources(resourceListResult.getData());
        }

        log.info("会话初始化成功{}",principal);

        return sessionDetails;

    }


}
