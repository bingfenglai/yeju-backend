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
package pers.lbf.yeju.provider.auth.role.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.provider.auth.account.enums.AccountStatusEnum;
import pers.lbf.yeju.provider.auth.role.dao.IRoleDao;
import pers.lbf.yeju.provider.base.util.SubjectUtils;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IRoleService;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/5 15:34
 */
@DubboService(interfaceClass = IRoleService.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public IResult<List<String>> getRoleListByPrincipal(String principal) throws ServiceException {
        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);
        List<String> roleNameList = null;
        if (accountType.equals(SubjectTypeEnum.is_system_account)){
            roleNameList = roleDao.selectRoleNamesBySystemAccount(principal);

        }

        if (accountType.equals(SubjectTypeEnum.is_mobile)){
            roleNameList = roleDao.selectRoleNamesByPhoneNumber(principal);
        }

        if (accountType.equals(SubjectTypeEnum.is_unknown)){
            throw ServiceException.getInstance(AccountStatusEnum.accountDoesNotExist);
        }

        return Result.ok(roleNameList);
    }
}
