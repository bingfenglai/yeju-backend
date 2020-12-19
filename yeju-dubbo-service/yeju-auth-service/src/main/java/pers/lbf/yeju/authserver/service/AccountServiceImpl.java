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
package pers.lbf.yeju.authserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.authserver.dao.IAccountDao;
import pers.lbf.yeju.authserver.enums.AccountStatus;
import pers.lbf.yeju.authserver.interfaces.dto.SimpleAccountDTO;
import pers.lbf.yeju.authserver.interfaces.interfaces.IAccountService;
import pers.lbf.yeju.authserver.util.SubjectUtils;
import pers.lbf.yeju.common.core.exception.service.rpc.RpcServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.AuthStatus;
import pers.lbf.yeju.common.core.status.enums.SubjectType;
import pers.lbf.yeju.common.domain.entity.Account;
import pers.lbf.yeju.common.util.YejuStringUtils;

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
        SubjectType accountType = SubjectUtils.getAccountType(principal);


        assert accountType != null
            : new RpcServiceException(
                AuthStatus.NO_ACCOUNT.getMessage(),
                AuthStatus.NO_ACCOUNT.getCode(),
                YejuStringUtils.split(principal),
                AccountServiceImpl.class.getName());

        QueryWrapper<Account> accountQueryWrapper  = new QueryWrapper<>();

        //构造条件
        if (accountType.equals(SubjectType.is_mobile)){
            accountQueryWrapper.eq(true,"phone_number",principal);

        }else {
            accountQueryWrapper.eq(true,"account_number",principal);

        }

        //2.获取账号主体信息
        Account account =account = accountDao.selectOne(accountQueryWrapper);

        //账号不存在
        if (account==null){
            throw RpcServiceException.getInstance(
                    AuthStatus.NO_ACCOUNT.getMessage(),
                    AuthStatus.NO_ACCOUNT.getCode(),
                    YejuStringUtils.split(principal),
                    ""
            );
        }

        SimpleAccountDTO accountDTO = new SimpleAccountDTO();
        accountDTO.setPrincipal(principal);
        accountDTO.setAccountType(accountType);
        accountDTO.setAccountStatus(account.getAccountStatus());
        accountDTO.setCertificate(account.getAccountPassword());

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

        SubjectType accountType = SubjectUtils.getAccountType(principal);

        QueryWrapper<Account> accountQueryWrapper  = new QueryWrapper<>();

        if (accountType.equals(SubjectType.is_mobile)){
            accountQueryWrapper.eq(true,"phone_number",principal);

        }else {
            accountQueryWrapper.eq(true,"account_number",principal);

        }
        Account account = accountDao.selectOne(accountQueryWrapper);

        assert account != null:
                RpcServiceException
                        .getInstance(AccountStatus.accountDoesNotExist);

        account.setAccountPassword(newPassword);

        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<Account>();
        //updateWrapper.

        return Result.ok(true);
    }
}
