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
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.constant.StatusConstants;
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
import pers.lbf.yeju.provider.customer.status.CustomerServiceStatus;
import pers.lbf.yeju.service.interfaces.auth.dto.AccountDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleAccountDTO;
import pers.lbf.yeju.service.interfaces.auth.enums.AccountOwnerTypeEnum;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/16 10:21
 */
@DubboService(interfaceClass = IAccountService.class)
@Slf4j
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    /**
     * 根据账户标识生成客户账号
     *
     * @param customerId  客户标识
     * @param phoneNumber 手机号
     * @return accountId 账户标识
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 11:09
     */
    @Override
    public IResult<Long> generateCustomerAccount(Long customerId, String phoneNumber) throws ServiceException {
        Account account = new Account();
        account.setAccountNumber(phoneNumber);
        account.setSubjectId(customerId);
        account.setPhoneNumber(phoneNumber);

        account.setAccountStatus(String.valueOf(StatusConstants.ABLE));
        account.setAccountLevel("0");
        account.setAccountType(AccountOwnerTypeEnum.Customer_account.getValue());
        account.setCreateTime(new Date());
        account.setCreateBy(0L);

        accountDao.insert(account);

        return Result.ok(account.getAccountId());
    }

    /**
     * 根据账户查找账户及权限信息
     *
     * @param principal 抽象账户
     * @return account
     */
    @Cacheable(cacheNames = "accountService:simpleAccount", key = "#principal")
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
    @CacheEvict(cacheNames = {
            "accountService:simpleAccount",
            "accountService:details"}, key = "#principal")
    @Override
    public IResult<Boolean> updatePassword(String principal, String newPassword) throws ServiceException {

        QueryWrapper<Account> accountQueryWrapper = getAccountQueryWrapperByPrincipal(principal);
        Account account = accountDao.selectOne(accountQueryWrapper);

        assert account != null :
                new RpcServiceException(AccountStatusEnum.accountDoesNotExist);

        account.setAccountPassword(newPassword);

        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<Account>();
        //updateWrapper.
        updateWrapper.set("account_password", newPassword);


        return Result.ok(true);
    }


    /**
     * 查询账户详情
     *
     * @param principal 账户
     * @return AccountDetailsInfoBean
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/5 15:44
     */
    @Override
    @Cacheable(cacheNames = "accountService:details", key = "#principal")
    public IResult<AccountDetailsInfoBean> findAccountDetailsByPrincipal(String principal) throws ServiceException {

        QueryWrapper<Account> wrapper = getAccountQueryWrapperByPrincipal(principal);

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
     * 获取账号类型
     *
     * @param principal 抽象账号
     * @return pers.lbf.yeju.common.core.result.IResult<pers.lbf.yeju.service.interfaces.auth.enums.AccountTypeEnum>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/7 21:55
     */
    @Cacheable(cacheNames = "accountService:accountType", keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<String> getAccountType(String principal) throws ServiceException {

        QueryWrapper<Account> wrapper = getAccountQueryWrapperByPrincipal(principal);
        wrapper.select("account_type");
        Account account = accountDao.selectOne(wrapper);
        if (account != null && account.getAccountType() != null) {
            return Result.ok(account.getAccountType());
        }
        throw ServiceException.getInstance(AccountStatusEnum.AccountOwnerTypeNotExist);
    }

    /**
     * 根据抽象账户查找账户主键
     *
     * @param principal
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Long>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/6 17:20
     */
    @Override
    @Cacheable(cacheNames = "accountService:accountId", key = "#principal")
    public IResult<Long> findAccountIdByPrincipal(String principal) throws ServiceException {
        QueryWrapper<Account> queryWrapper = getAccountQueryWrapperByPrincipal(principal);
        queryWrapper.select("account_id");
        queryWrapper.eq("account_status", StatusConstants.ABLE);
        Account account = accountDao.selectOne(queryWrapper);
        log.info("查询账户ID成功");
        return Result.ok(account.getAccountId());
    }

    /**
     * TODO 后续需要补关系表的删除
     *
     * @param principal
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/11 13:10
     */
    @Override
    @CacheEvict(cacheNames = {
            "accountService:accountId",
            "accountService:details",
            "accountService:simpleAccount",
            "accountService:details"

    }, allEntries = true)
    public IResult<Boolean> deleteByPrincipal(String principal) throws ServiceException {

        QueryWrapper<Account> queryWrapper = getAccountQueryWrapperByPrincipal(principal);
        accountDao.delete(queryWrapper);
        return Result.ok(true);
    }

    @CacheEvict(cacheNames = {
            "accountService:accountId",
            "accountService:details",
            "accountService:simpleAccount",
            "accountService:details"

    }, allEntries = true)
    @Override
    public IResult<Boolean> deleteById(Long id) throws ServiceException {
        accountDao.deleteById(id);
        return Result.ok(true);
    }

    @CacheEvict(cacheNames = {
            "accountService:accountId",
            "accountService:details",
            "accountService:simpleAccount",
            "accountService:details"

    }, allEntries = true)
    @Override
    public IResult<Boolean> deleteBatch(String[] idList) throws ServiceException {
        List<Long> ids = new LinkedList<>();
        for (String s : idList) {
            ids.add(Long.valueOf(s));
        }
        accountDao.deleteBatchIds(ids);
        return Result.ok(true);
    }

    /**
     * 判断手机号是否已被注册
     *
     * @param phoneNumber 手机号
     * @return flag
     * @throws ServiceException
     */
    @Override
    public IResult<Boolean> isExitPhoneNumber(String phoneNumber) throws ServiceException {
        boolean flag = accountDao.isExitPhoneNumber(phoneNumber);
        if (flag) {
            throw ServiceException.getInstance(CustomerServiceStatus.mobilePhoneNumberHasBeenRegistered);
        }
        return Result.success();
    }

    /**
     * 根据账户所有者id 查询账户id
     *
     * @param subjectId
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<Long> findAccountIdBySubjectIdAndAccountType(Long subjectId, AccountOwnerTypeEnum type) throws ServiceException {
        Long accountId = accountDao.selectIdBySubjectIdAndAccountType(subjectId, type.getValue());
        return Result.ok(accountId);
    }


    private QueryWrapper<Account> getAccountQueryWrapperByPrincipal(String principal) {
        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        if (accountType.equals(SubjectTypeEnum.is_system_account)) {
            wrapper.eq("account_number", principal);

        }

        if (accountType.equals(SubjectTypeEnum.is_mobile)) {
            wrapper.eq("phone_number", principal);
        }

        if (accountType.equals(SubjectTypeEnum.is_unknown)) {
            throw ServiceException.getInstance(AccountStatusEnum.accountDoesNotExist);
        }

        return wrapper;
    }
}
