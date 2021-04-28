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
import pers.lbf.yeju.service.interfaces.auth.dto.AuthzSimpleInfoBean;
import pers.lbf.yeju.service.interfaces.auth.pojo.AuthorizedCreatArgs;
import pers.lbf.yeju.service.interfaces.auth.pojo.SynchronousAuthorizedCreateArgs;

/**
 * 授权信息服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/18 20:23
 */
public interface IAuthorizationService {

    /**
     * 查询账户的授权信息
     * 用于前端鉴权
     *
     * @param principal 抽象账户
     * @return 授权信息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/11 13:16
     */
    IResult<AuthzSimpleInfoBean> findAuthzInfoByPrincipal(String principal) throws ServiceException;

    /**
     * 对 抽象客体 进行授权
     *
     * @param args
     * @return 是否成功
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/11 13:19
     */
    IResult<Boolean> authorizedToRole(AuthorizedCreatArgs args) throws ServiceException;


    void synchronousAuthorizedToAccount(SynchronousAuthorizedCreateArgs args) throws ServiceException;

}
