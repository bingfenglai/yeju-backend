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
package pers.lbf.yeju.provider.account.strategy;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleAccountDTO;

/**查询账户信息策略接口
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/23 10:21
 */
public interface IFindSimpleAccountByPrincipalStrategy {

    /**查询账户信息策略接口方法
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/23 11:54
     * @param principal 抽象账户
     * @return SimpleAccountDTO
     * @throws RuntimeException e
     */
    SimpleAccountDTO findSimpleAccountByPrincipal(String principal) throws ServiceException;
}
