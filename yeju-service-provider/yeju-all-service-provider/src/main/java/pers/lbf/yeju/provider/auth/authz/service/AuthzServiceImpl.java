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
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.service.interfaces.auth.dto.AccountDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.AuthzSimpleInfoBean;
import pers.lbf.yeju.service.interfaces.auth.enums.AccountOwnerTypeEnum;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAuthzService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IRoleService;
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
@DubboService(interfaceClass = IAuthzService.class)
public class AuthzServiceImpl implements IAuthzService {

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

    @Cacheable(cacheNames = "authzService",key = "#principal")
    @Override
    public IResult<AuthzSimpleInfoBean> findAuthzInfoByPrincipal(String principal) throws ServiceException {
        AuthzSimpleInfoBean bean = new AuthzSimpleInfoBean();
        if (principal==null|| "".equals(principal)){
            return Result.ok(bean);
        }

        AccountOwnerTypeEnum ownerTypeEnum = accountService.getAccountType(principal).getData();
        AccountDetailsInfoBean accountDetailsInfo = accountService.findAccountDetailsByPrincipal(principal).getData();
        // 内部账号
        if (AccountOwnerTypeEnum.Internal_account.equals(ownerTypeEnum)) {
            SimpleEmployeeInfoBean employeeInfoBean = employeeService.findInfoByEmployeeId(accountDetailsInfo.getSubjectId()).getData();
            bean.setName(employeeInfoBean.getName());
            bean.setAvatar(employeeInfoBean.getAvatar());

        }else if (AccountOwnerTypeEnum.Customer_account.equals(ownerTypeEnum)){
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
}