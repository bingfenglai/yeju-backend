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
package pers.lbf.yeju.authserver.enums;

import pers.lbf.yeju.authserver.interfaces.dto.SimpleAccountDTO;
import pers.lbf.yeju.authserver.strategy.account.IFindSimpleAccountByPrincipalStrategy;

/**枚举单例策略实现
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/23 13:07
 */
public enum FindSimpleAccountByPrincipalStrategyEnum implements IFindSimpleAccountByPrincipalStrategy {



    /**
     * 实例
     */
    INSTANCE;



    /**
     * 查询账户信息策略接口方法
     *
     * @param principal 抽象账户
     * @return SimpleAccountDTO
     * @throws RuntimeException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/23 11:54
     */
    @Override
    public SimpleAccountDTO findSimpleAccountByPrincipal(String principal) throws RuntimeException {
        return null;
    }
}
