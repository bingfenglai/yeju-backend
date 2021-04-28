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
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleRole;

import java.util.List;

/**
 * 角色服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/12 23:55
 */
public interface IRoleService {

    IResult<Boolean> deleteById(Long id) throws ServiceException;

    IResult<Boolean> updateById(SimpleRole role) throws ServiceException;

    IResult<Boolean> create(SimpleRole role) throws ServiceException;

    IResult<SimpleRole> findById(Long id) throws ServiceException;

    PageResult<SimpleRole> findPage(Long currentPage, Long size) throws ServiceException;

    IResult<List<String>> getRoleListByPrincipal(String principal) throws ServiceException;

    IResult<Boolean> isExist(Long roleId) throws ServiceException;
}
