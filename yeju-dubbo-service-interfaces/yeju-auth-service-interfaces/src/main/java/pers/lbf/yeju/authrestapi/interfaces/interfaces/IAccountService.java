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
package pers.lbf.yeju.authrestapi.interfaces.interfaces;

import pers.lbf.yeju.authrestapi.interfaces.dto.SimpleAccountDTO;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;

/**账户服务接口类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description
 * @date 2020/12/16 10:14
 */
public interface IAccountService {

    /**
     * 根据账户查找账户及权限信息
     * @param principal 抽象账户
     * @return account
     */
    IResult<SimpleAccountDTO> findSimpleAccountByPrincipal(String principal) throws ServiceException;

    /**
     * 更新密码
     * @param principal 抽象账号
     * @param newPassword 新密码
     * @throws RuntimeException e
     */
    IResult<Boolean> updatePassword(String principal,String newPassword) throws ServiceException;
}
