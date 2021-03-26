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
package pers.lbf.yeju.provider.auth.authz.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.domain.entity.AccountResources;
import pers.lbf.yeju.common.domain.entity.RoleResource;
import pers.lbf.yeju.provider.auth.authz.dao.IAccountResourcesDao;
import pers.lbf.yeju.provider.auth.authz.dao.IAccountRoleDao;
import pers.lbf.yeju.provider.auth.authz.dao.IAuthorizationDao;
import pers.lbf.yeju.provider.auth.authz.pojo.AccountSimpleInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.AccountDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.AuthzSimpleInfoBean;
import pers.lbf.yeju.service.interfaces.auth.enums.AccountOwnerTypeEnum;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAuthorizationService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IRoleService;
import pers.lbf.yeju.service.interfaces.auth.pojo.AuthorizedCreatArgs;
import pers.lbf.yeju.service.interfaces.auth.pojo.SynchronousAuthorizedCreateArgs;
import pers.lbf.yeju.service.interfaces.customer.ICustomerValidService;
import pers.lbf.yeju.service.interfaces.customer.pojo.SimpleCustomerInfoBean;
import pers.lbf.yeju.service.interfaces.platfrom.employee.IEmployeeService;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimpleEmployeeInfoBean;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/18 20:34
 */
@DubboService(interfaceClass = IAuthorizationService.class, timeout = 3000)
public class AuthorizationServiceImpl implements IAuthorizationService {

    @DubboReference
    private IAccountService accountService;

    @DubboReference
    private IResourcesService resourcesService;

    @DubboReference
    private IRoleService roleService;

    @DubboReference
    private IEmployeeService employeeService;

    @DubboReference
    private ICustomerValidService customerValidService;

    @Autowired
    private IAuthorizationDao authorizationDao;

    @Autowired
    private IAccountRoleDao accountRoleDao;

    @Autowired
    private IAccountResourcesDao accountResourcesDao;


    @Cacheable(cacheNames = "authzService:authzBean")
    @Override
    public IResult<AuthzSimpleInfoBean> findAuthzInfoByPrincipal(String principal) throws ServiceException {
        AuthzSimpleInfoBean bean = new AuthzSimpleInfoBean();
        if (principal == null || "".equals(principal)) {
            return Result.ok(bean);
        }

        String accountType = accountService.getAccountType(principal).getData();
        AccountDetailsInfoBean accountDetailsInfo = accountService.findAccountDetailsByPrincipal(principal).getData();
        // 内部账号
        if (AccountOwnerTypeEnum.Internal_account.getValue().equals(accountType)) {
            SimpleEmployeeInfoBean employeeInfoBean = employeeService.findInfoByEmployeeId(accountDetailsInfo.getSubjectId()).getData();
            bean.setName(employeeInfoBean.getName());
            bean.setAvatar(employeeInfoBean.getAvatar());

        } else if (AccountOwnerTypeEnum.Customer_account.getValue().equals(accountType)) {
            SimpleCustomerInfoBean customerInfoBean = customerValidService.findDetailsById(accountDetailsInfo.getSubjectId()).getData();
            bean.setName(customerInfoBean.getCustomerName());
            bean.setAvatar(customerInfoBean.getAvatar());

        }

        List<String> roleList = roleService.getRoleListByPrincipal(principal).getData();

        bean.setRoles(roleList);

        List<String> permissionList = resourcesService.findAuthorityListByPrincipal(principal).getData();

        bean.setPermissions(permissionList);

        return Result.ok(bean);
    }

    /**
     * 对 抽象客体 进行授权
     *
     * @param args
     * @return 是否成功
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/11 13:19
     */
    @Override
    public IResult<Boolean> authorizedToRole(AuthorizedCreatArgs args) throws ServiceException {
        //1 验证角色是否存在
        Boolean flag = roleService.isExist(Long.valueOf(args.getRoleId())).getData();

        if (flag) {
            // 2. 角色存在 对角色进行授权

            for (String s : args.getAuthorityIdList()) {
                RoleResource roleResource = creatArgsToRoleResource(args, Long.valueOf(s));
                authorizationDao.insert(roleResource);
            }

            return Result.success();
        }


        throw ServiceException.getInstance("角色不存在",
                ServiceStatusEnum.no_data_has_been_found.getCode());
    }

    @Override
    @Async
    public void synchronousAuthorizedToAccount(SynchronousAuthorizedCreateArgs args) throws ServiceException {

        // 1. 查询拥有该角色的账号
        String roleId = args.getRoleId();
        List<AccountSimpleInfoBean> accountList = accountRoleDao.findAccountIdListByRoleId(Long.valueOf(roleId));

        //2. 将添加对应的授权信息

        for (AccountSimpleInfoBean accountSimpleInfoBean : accountList) {
            for (String s : args.getAuthorityIdList()) {
                AccountResources accountResource = buildAccountResource(accountSimpleInfoBean, Long.valueOf(s));
                accountResource.setCreateBy(args.getCreateBy());
                accountResource.setCreateTime(args.getCreateTime());
                accountResourcesDao.insert(accountResource);

            }
        }


    }

    private AccountResources buildAccountResource(AccountSimpleInfoBean accountSimpleInfoBean, Long resourceId) {
        AccountResources accountResources = new AccountResources();
        accountResources.setId(accountSimpleInfoBean.getId());
        accountResources.setAccountNumber(accountSimpleInfoBean.getAccountNumber());
        accountResources.setPhoneNumber(accountSimpleInfoBean.getPhoneNumber());
        accountResources.setResourceId(resourceId);

        return accountResources;
    }

    private RoleResource creatArgsToRoleResource(AuthorizedCreatArgs args, Long authorityId) {
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleId(Long.valueOf(args.getRoleId()));
        roleResource.setResourceId(authorityId);
        roleResource.setAuthorizedType(args.getAuthorizedType());
        roleResource.setCreateTime(args.getCreateTime());
        roleResource.setCreateBy(Long.valueOf(args.getCreateBy()));

        roleResource.setRemark(args.getRemark());
        roleResource.setValidPeriod(args.getValidPeriod());
        return roleResource;
    }
}