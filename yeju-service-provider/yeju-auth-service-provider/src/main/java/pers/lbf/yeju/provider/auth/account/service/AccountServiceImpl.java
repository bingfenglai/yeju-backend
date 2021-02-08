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
package pers.lbf.yeju.provider.auth.account.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.constant.ServiceStatusConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.exception.service.rpc.RpcServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.common.domain.entity.Account;
import pers.lbf.yeju.provider.auth.account.dao.IAccountDao;
import pers.lbf.yeju.provider.auth.account.enums.AccountStatusEnum;
import pers.lbf.yeju.provider.auth.account.factory.AccountStrategyFactory;
import pers.lbf.yeju.provider.auth.account.strategy.IFindSimpleAccountByPrincipalStrategy;
import pers.lbf.yeju.provider.base.util.SubjectUtils;
import pers.lbf.yeju.service.interfaces.auth.dto.AccountDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleAccountDTO;
import pers.lbf.yeju.service.interfaces.auth.enums.AccountOwnerTypeEnum;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;

import java.util.Objects;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/16 10:21
 */
@DubboService(interfaceClass = IAccountService.class)
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    /**
     * 根据账户查找账户及权限信息
     *
     * @param principal 抽象账户
     * @return account
     */
    @Override
    public IResult<SimpleAccountDTO> findSimpleAccountByPrincipal(String principal) throws ServiceException {
        //1。判断账户类型
        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);


        IFindSimpleAccountByPrincipalStrategy strategy
                = AccountStrategyFactory.getStrategy(accountType);

        SimpleAccountDTO accountDTO = strategy.findSimpleAccountByPrincipal(principal);


        return Result.ok(accountDTO);
    }

    /**
     * 更新密码
     *
     * @param principal   抽象账号
     * @param newPassword 新密码
     * @throws RuntimeException e
     */
    @Override
    public IResult<Boolean> updatePassword(String principal, String newPassword) throws ServiceException {

        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);

        QueryWrapper<Account> accountQueryWrapper  = new QueryWrapper<>();

        if (accountType.equals(SubjectTypeEnum.is_mobile)){
            accountQueryWrapper.eq(true,"phone_number",principal);

        }else {
            accountQueryWrapper.eq(true,"account_number",principal);

        }
        Account account = accountDao.selectOne(accountQueryWrapper);

        assert account != null:
                new RpcServiceException(AccountStatusEnum.accountDoesNotExist);

        account.setAccountPassword(newPassword);

        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<Account>();
        //updateWrapper.
        updateWrapper.set("account_password",newPassword);


        return Result.ok(true);
    }

    /** 查询账户详情
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/5 15:44
     * @param principal 账户
     * @return AccountDetailsInfoBean
     */
    @Override
    public IResult<AccountDetailsInfoBean> findAccountDetailsByPrincipal(String principal) throws ServiceException {

        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        if (accountType.equals(SubjectTypeEnum.is_system_account)){
            wrapper.eq("account_number",principal);

        }

        if (accountType.equals(SubjectTypeEnum.is_mobile)){
            wrapper.eq("phone_number",principal);
        }

        if (accountType.equals(SubjectTypeEnum.is_unknown)){
            throw ServiceException.getInstance(AccountStatusEnum.accountDoesNotExist);
        }

        Account account = accountDao.selectOne(wrapper);
        if (account == null) {
            throw ServiceException.getInstance(AccountStatusEnum.accountDoesNotExist);
        }

        AccountDetailsInfoBean accountDetails = new AccountDetailsInfoBean();
        accountDetails.setAccountNumber(account.getAccountNumber());
        accountDetails.setPhoneNumber(account.getPhoneNumber());
        accountDetails.setSubjectId(account.getSubjectId());
        accountDetails.setLastLoginAddress(account.getLastLoginAddress());
        accountDetails.setLastLoginDate(account.getLastLoginDate());
        accountDetails.setAccountStatus(account.getAccountStatus());
        accountDetails.setAccountLevel(account.getAccountLevel());
        accountDetails.setAccountType(account.getAccountType());


        return Result.ok(accountDetails);
    }

    /**
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/7 21:55
     * @param principal 抽象账号
     * @return pers.lbf.yeju.common.core.result.IResult<pers.lbf.yeju.service.interfaces.auth.enums.AccountTypeEnum>
     */
    @Override
    public IResult<AccountOwnerTypeEnum> getAccountType(String principal) throws ServiceException {

        IResult<AccountDetailsInfoBean> infoBeanResult = findAccountDetailsByPrincipal(principal);
        if (Objects.equals(infoBeanResult.getCode(), ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE)){
            AccountDetailsInfoBean infoBean = infoBeanResult.getData();
            if (infoBean.getAccountType()!=null&&infoBean.getAccountType().equalsIgnoreCase(AccountOwnerTypeEnum.Internal_account.getValue())){
                return Result.ok(AccountOwnerTypeEnum.Internal_account);
            }
        }
        throw  ServiceException.getInstance(AccountStatusEnum.AccountOwnerTypeNotExist);
    }
}
