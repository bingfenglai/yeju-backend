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
package pers.lbf.yeju.provider.auth.account.factory;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.exception.service.strategy.StrategyException;
import pers.lbf.yeju.common.core.status.enums.StrategyStatusEnum;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.provider.auth.account.strategy.IFindSimpleAccountByPrincipalStrategy;
import pers.lbf.yeju.provider.auth.account.strategy.impl.FindSimpleAccountByAccountNumberStrategyEnum;
import pers.lbf.yeju.provider.auth.account.strategy.impl.FindSimpleAccountByPhoneNumberStrategyEnum;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/23 12:02
 */

public class AccountStrategyFactory {

    /**获取策略实例
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/23 13:36
     * @param type 账户类型
     * @return pers.lbf.yeju.authserver.account.strategy.IFindSimpleAccountByPrincipalStrategy
     * @throws ServiceException e
     */
    public static IFindSimpleAccountByPrincipalStrategy getStrategy(SubjectTypeEnum type) {

        if (SubjectTypeEnum.is_mobile.equals(type)){
            return FindSimpleAccountByPhoneNumberStrategyEnum.INSTANCE;
        }

        if (SubjectTypeEnum.is_system_account.equals(type)){
            return FindSimpleAccountByAccountNumberStrategyEnum.INSTANCE;
        }

        throw new StrategyException(StrategyStatusEnum.NOT_FOUND);
    }

}
