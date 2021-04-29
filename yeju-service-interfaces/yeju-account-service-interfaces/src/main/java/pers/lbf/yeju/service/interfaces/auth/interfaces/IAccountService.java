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
package pers.lbf.yeju.service.interfaces.auth.interfaces;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.auth.dto.AccountDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleAccountDTO;
import pers.lbf.yeju.service.interfaces.auth.enums.AccountOwnerTypeEnum;

/**
 * 账户服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description
 * @date 2020/12/16 10:14
 */
public interface IAccountService {

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
    IResult<Long> generateCustomerAccount(Long customerId, String phoneNumber) throws ServiceException;

    /**
     * 根据账户查找账户及权限信息
     *
     * @param principal 抽象账户
     * @return account
     */
    IResult<SimpleAccountDTO> findSimpleAccountByPrincipal(String principal) throws ServiceException;

    /**
     * 更新密码
     *
     * @param principal   抽象账号
     * @param newPassword 新密码
     * @throws RuntimeException e
     */
    IResult<Boolean> updatePassword(String principal, String newPassword) throws ServiceException;


    IResult<AccountDetailsInfoBean> findAccountDetailsByPrincipal(String principal) throws ServiceException;


    /**
     * @param principal 抽象账号
     * @return pers.lbf.yeju.common.core.result.IResult<pers.lbf.yeju.service.interfaces.auth.enums.AccountTypeEnum>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/7 21:55
     */
    IResult<String> getAccountType(String principal) throws ServiceException;

    IResult<Long> findAccountIdByPrincipal(String principal) throws ServiceException;

    IResult<Boolean> deleteByPrincipal(String principal) throws ServiceException;

    IResult<Boolean> deleteById(Long id) throws ServiceException;

    IResult<Boolean> deleteBatch(String[] idList) throws ServiceException;

    /**
     * 判断手机号是否已被注册
     *
     * @param phoneNumber 手机号
     * @return flag
     * @throws ServiceException
     */
    IResult<Boolean> isExitPhoneNumber(String phoneNumber) throws ServiceException;

    /**
     * 根据账户所有者id 查询账户id
     *
     * @param subjectId
     * @return
     * @throws ServiceException
     */
    IResult<Long> findAccountIdBySubjectIdAndAccountType(Long subjectId, AccountOwnerTypeEnum type) throws ServiceException;
}
