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
package pers.lbf.yeju.provider.auth.account.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.lbf.yeju.common.core.exception.service.rpc.RpcServiceException;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.common.domain.entity.Account;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.auth.account.dao.IAccountDao;
import pers.lbf.yeju.provider.auth.account.strategy.IFindSimpleAccountByPrincipalStrategy;
import pers.lbf.yeju.provider.auth.resource.dao.IResourcesDao;
import pers.lbf.yeju.provider.base.util.SpringContextUtils;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleAccountDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/23 11:58
 */
public enum FindSimpleAccountByPhoneNumberStrategyEnum implements IFindSimpleAccountByPrincipalStrategy {

    /**
     * 实例
     */
    INSTANCE(SpringContextUtils.getBean(IAccountDao.class),SpringContextUtils.getBean(IResourcesDao.class));

    private IAccountDao accountDao;
    private IResourcesDao resourcesDao;


    FindSimpleAccountByPhoneNumberStrategyEnum(IAccountDao accountDao, IResourcesDao resourcesDao){
        this.accountDao = accountDao;
        this.resourcesDao = resourcesDao;
    }

    /**
     * 查询账户信息策略方法
     * @param phoneNumber 手机号
     * @return SimpleAccountDTO
     * @throws RuntimeException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/23 11:54
     */
    @Override
    public SimpleAccountDTO findSimpleAccountByPrincipal(String phoneNumber) throws RuntimeException {
        QueryWrapper<Account> accountQueryWrapper  = new QueryWrapper<>();
        accountQueryWrapper.eq(true,"phone_number",phoneNumber);

        //2.获取账号主体信息
        Account account  = accountDao.selectOne(accountQueryWrapper);

        //账号不存在
        if (account==null){
            throw new RpcServiceException(
                    AuthStatusEnum.NO_ACCOUNT.getMessage(),
                    AuthStatusEnum.NO_ACCOUNT.getCode(),
                    YejuStringUtils.split(phoneNumber),
                    ""
            );
        }
        List<String> resourceCodeList = new ArrayList<>();

        resourceCodeList = resourcesDao.findResourceListByPhoneNumber(phoneNumber);


        SimpleAccountDTO accountDTO = new SimpleAccountDTO();

        accountDTO.setPrincipal(phoneNumber);
        accountDTO.setAccountType(SubjectTypeEnum.is_mobile);
        accountDTO.setAccountStatus(account.getAccountStatus());
        accountDTO.setCertificate(account.getAccountPassword());
        accountDTO.setAuthorityStringList(resourceCodeList);


        return accountDTO;
    }
}
