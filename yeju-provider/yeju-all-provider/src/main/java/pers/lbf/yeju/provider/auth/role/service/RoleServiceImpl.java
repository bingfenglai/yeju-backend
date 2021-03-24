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
package pers.lbf.yeju.provider.auth.role.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.common.domain.entity.Role;
import pers.lbf.yeju.provider.auth.account.enums.AccountStatusEnum;
import pers.lbf.yeju.provider.auth.role.dao.IRoleDao;
import pers.lbf.yeju.provider.base.util.SubjectUtils;
import pers.lbf.yeju.service.interfaces.auth.dto.SimpleRole;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IRoleService;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/5 15:34
 */
@DubboService(interfaceClass = IRoleService.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    @Cacheable(cacheNames = "roleService:list", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleRole> findPage(Long currentPage, Long size) throws ServiceException {
        Page<Role> page = new Page<>();
        page.setPages(currentPage);
        page.setSize(size);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_status", 1);
        Page<Role> rolePage = roleDao.selectPage(page, queryWrapper);
        List<Role> roleList = rolePage.getRecords();
        List<SimpleRole> result = new LinkedList<>();
        if (roleList.size() > 0) {

            for (Role role : roleList) {
                SimpleRole simpleRole = roleToSimpleInfoBean(role);
                result.add(simpleRole);
            }
        }
        return PageResult.ok(page.getTotal(), currentPage, size, result);
    }

    @CacheEvict(cacheNames = {"roleService"}, allEntries = true)
    @Override
    public IResult<Boolean> deleteById(Long id) throws ServiceException {
        roleDao.deleteById(id);
        return Result.ok(true);
    }

    @CachePut(cacheNames = "roleService:id", key = "#role.roleId")
    @Override
    public IResult<Boolean> updateById(SimpleRole role) throws ServiceException {
        return null;
    }

    @CacheEvict(cacheNames = "roleService:list", keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<Boolean> create(SimpleRole role) throws ServiceException {
        return null;
    }

    @Cacheable(cacheNames = "roleService:id", key = "#id")
    @Override
    public IResult<SimpleRole> findById(Long id) throws ServiceException {

        Role role = roleDao.selectById(id);
        SimpleRole simpleRole = roleToSimpleInfoBean(role);

        return Result.ok(simpleRole);
    }


    @Cacheable(cacheNames = "roleService:list:principal", key = "#principal")
    @Override
    public IResult<List<String>> getRoleListByPrincipal(String principal) throws ServiceException {
        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);
        List<String> roleNameList = null;
        if (accountType.equals(SubjectTypeEnum.is_system_account)) {
            roleNameList = roleDao.selectRoleNamesBySystemAccount(principal);

        }

        if (accountType.equals(SubjectTypeEnum.is_mobile)) {
            roleNameList = roleDao.selectRoleNamesByPhoneNumber(principal);
        }

        if (accountType.equals(SubjectTypeEnum.is_unknown)) {
            throw ServiceException.getInstance(AccountStatusEnum.accountDoesNotExist);
        }

        return Result.ok(roleNameList);
    }

    @Override
    public IResult<Boolean> isExist(Long roleId) throws ServiceException {
        int count = roleDao.isExist(roleId);

        if (count == 1) {
            return Result.success();
        }

        return Result.failed();
    }


    private SimpleRole roleToSimpleInfoBean(Role role) {
        SimpleRole bean = new SimpleRole();
        bean.setRoleId(role.getRoleId());
        bean.setRoleName(role.getRoleName());
        bean.setRoleCode(role.getRoleCode());
        bean.setRoleStatus(role.getRoleStatus());
        bean.setCreateTime(role.getCreateTime());

        return bean;
    }

}
