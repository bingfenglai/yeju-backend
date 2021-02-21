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
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
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
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @DubboReference
    private IDataDictionaryInfoService dataDictionaryService;

    @Cacheable(cacheNames = "roleService",keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleRole> findPage(Long currentPage, Long size) throws ServiceException {
        Page<Role> page = new Page<>();
        page.setPages(currentPage);
        page.setSize(size);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_status",1);
        Page<Role> rolePage = roleDao.selectPage(page, queryWrapper);
        List<Role> roleList = rolePage.getRecords();
        List<SimpleRole> result = new LinkedList<>();
        if (roleList.size() > 0){
            IResult<Map<String, String>> mapResult = dataDictionaryService.getDictMap("role_status");
            Map<String, String> map = null;
            if (mapResult.isSuccess()){
                map = mapResult.getData();
            }

            for (Role role : roleList) {
                SimpleRole simpleRole = roleToSimpleInfoBean(role);
                if(map!=null){
                    simpleRole.setRoleStatusValueStr(map.get(role.getRoleStatus().toString()));
                }
                result.add(simpleRole);
            }
        }
        return PageResult.ok(page.getTotal(),currentPage,size,result);
    }

    @CachePut(cacheNames = "roleService",keyGenerator = "yejuKeyGenerator")
    @Override
    public void deleteById(Long id) throws ServiceException {
        roleDao.deleteById(id);
    }

    @CachePut(cacheNames = "roleService",keyGenerator = "yejuKeyGenerator")
    @Override
    public void updateById(SimpleRole role) throws ServiceException {

    }

    @CachePut(cacheNames = "roleService",keyGenerator = "yejuKeyGenerator")
    @Override
    public void create(SimpleRole role) throws ServiceException {

    }

    @Cacheable(cacheNames = "roleService",keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<SimpleRole> findById(Long id) throws ServiceException {
        return null;
    }



    @Cacheable(cacheNames = "roleService",keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<List<String>> getRoleListByPrincipal(String principal) throws ServiceException {
        SubjectTypeEnum accountType = SubjectUtils.getAccountType(principal);
        List<String> roleNameList = null;
        if (accountType.equals(SubjectTypeEnum.is_system_account)){
            roleNameList = roleDao.selectRoleNamesBySystemAccount(principal);

        }

        if (accountType.equals(SubjectTypeEnum.is_mobile)){
            roleNameList = roleDao.selectRoleNamesByPhoneNumber(principal);
        }

        if (accountType.equals(SubjectTypeEnum.is_unknown)){
            throw ServiceException.getInstance(AccountStatusEnum.accountDoesNotExist);
        }

        return Result.ok(roleNameList);
    }


    private SimpleRole roleToSimpleInfoBean(Role role){
        SimpleRole bean = new SimpleRole();
        bean.setRoleId(role.getRoleId());
        bean.setRoleName(role.getRoleName());
        bean.setRoleCode(role.getRoleCode());
        bean.setRoleStatus(role.getRoleStatus());
        bean.setCreateTime(role.getCreateTime());

        return bean;
    }

    private String getRoleStatusLabel(Long roleStatus) {
        IResult<Map<String, String>> mapResult = dataDictionaryService.getDictMap("role_status");

        if (mapResult.isSuccess()){
            Map<String, String> map = mapResult.getData();
            return map.get(roleStatus.toString());
        }
        return null;
    }
}
