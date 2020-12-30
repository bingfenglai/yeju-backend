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
package pers.lbf.yeju.currency.provider.account.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.authrestapi.interfaces.dto.SimpleAccountDTO;
import pers.lbf.yeju.authrestapi.interfaces.interfaces.IAccountService;
import pers.lbf.yeju.common.core.exception.service.rpc.RpcServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.common.domain.entity.Account;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.currency.provider.account.dao.IAccountDao;
import pers.lbf.yeju.currency.provider.account.enums.AccountStatusEnumEnum;
import pers.lbf.yeju.currency.provider.account.factory.AccountStrategyFactory;
import pers.lbf.yeju.currency.provider.account.strategy.IFindSimpleAccountByPrincipalStrategy;
import pers.lbf.yeju.currency.provider.util.SubjectUtils;

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
    public IResult<SimpleAccountDTO> findSimpleAccountByPrincipal(String principal) throws RuntimeException {
        //1。判断账户类型
        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);

        assert accountType != null
                : new RpcServiceException(
                AuthStatusEnum.NO_ACCOUNT.getMessage(),
                AuthStatusEnum.NO_ACCOUNT.getCode(),
                YejuStringUtils.split(principal),
                AccountServiceImpl.class.getName());

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
    public IResult<Boolean> updatePassword(String principal, String newPassword) throws RuntimeException {

        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);

        QueryWrapper<Account> accountQueryWrapper  = new QueryWrapper<>();

        if (accountType.equals(SubjectTypeEnum.is_mobile)){
            accountQueryWrapper.eq(true,"phone_number",principal);

        }else {
            accountQueryWrapper.eq(true,"account_number",principal);

        }
        Account account = accountDao.selectOne(accountQueryWrapper);

        assert account != null:
                new RpcServiceException(AccountStatusEnumEnum.accountDoesNotExist);

        account.setAccountPassword(newPassword);

        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<Account>();
        //updateWrapper.

        return Result.ok(true);
    }
}
