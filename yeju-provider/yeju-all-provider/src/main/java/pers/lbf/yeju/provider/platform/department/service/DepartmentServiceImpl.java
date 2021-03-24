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
package pers.lbf.yeju.provider.platform.department.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.constant.DataDictionaryTypeConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.domain.entity.Department;
import pers.lbf.yeju.provider.platform.department.dao.IDepartmentDao;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.platfrom.department.IDepartmentService;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.DepartmentIdAndName;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimpleDepartmentInfoBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/14 23:10
 */
@DubboService(interfaceClass = IDepartmentService.class)
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentDao departmentDao;

    @DubboReference
    private IDataDictionaryInfoService dataDictionaryService;

    @Cacheable(cacheNames = "departmentService:id", key = "#id")
    @Override
    public IResult<SimpleDepartmentInfoBean> findDeptById(Long id) throws ServiceException {
        Department department = departmentDao.selectById(id);

        if (department != null) {
            SimpleDepartmentInfoBean bean = new SimpleDepartmentInfoBean();

            bean.setDepartmentId(id);
            bean.setName(department.getName());
            bean.setLeaderId(department.getLeaderId());
            bean.setPhoneNumber(department.getPhoneNumber());
            bean.setPhoneNumberPrefix(department.getPhoneNumberPrefix());
            bean.setEmail(department.getEmail());
            bean.setDepartmentStatus(getDeptStatus(department.getYejuDepartmentStatus()));
            bean.setCreateTime(department.getCreateTime());

            return Result.ok(bean);
        }


        throw ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);
    }

    @Cacheable(cacheNames = "departmentService:list", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleDepartmentInfoBean> findPage(Long currentPage, Long size) throws ServiceException {

        Page<Department> page = new Page<>();
        page.setSize(size);
        page.setPages(currentPage);
        Page<Department> departmentPage = departmentDao.selectPage(page, null);
        List<Department> list = departmentPage.getRecords();
        List<SimpleDepartmentInfoBean> result = new LinkedList<>();
        IResult<Map<String, String>> mapResult = dataDictionaryService.getDictMap(DataDictionaryTypeConstant.DEPARTMENT_STATUS);
        Map<String, String> statusMap = mapResult.getData();
        for (Department department : list) {
            SimpleDepartmentInfoBean bean = departmentToSimpleInfoBean(department);
            bean.setDepartmentStatusStr(statusMap.get(bean.getDepartmentStatus()));
            result.add(bean);
        }
        return PageResult.ok(departmentPage.getTotal(), currentPage, size, result);
    }

    @Override
    @CacheEvict(cacheNames = "departmentService:list", allEntries = true)
    public IResult<Object> addDepartment(SimpleDepartmentInfoBean bean) throws ServiceException {
        return null;
    }

    @CacheEvict(cacheNames = "departmentService", allEntries = true)
    @Override
    public IResult<Object> updateDepartment(SimpleDepartmentInfoBean bean) throws ServiceException {

        return null;
    }

    @Cacheable(cacheNames = "departmentService", keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<List<DepartmentIdAndName>> findAll() throws ServiceException {

        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        List<Department> departmentList = departmentDao.selectList(queryWrapper);
        List<DepartmentIdAndName> result = new LinkedList<>();
        if (departmentList != null) {
            for (Department department : departmentList) {
                DepartmentIdAndName bean = new DepartmentIdAndName();
                bean.setId(department.getDepartmentId());
                bean.setName(department.getName());
                bean.setParentId(department.getParentDepartmentId());
                result.add(bean);
            }


        }

        return Result.ok(result);


    }


    private SimpleDepartmentInfoBean departmentToSimpleInfoBean(Department department) {
        SimpleDepartmentInfoBean bean = new SimpleDepartmentInfoBean();
        bean.setParentId(department.getParentDepartmentId());
        bean.setDepartmentId(department.getDepartmentId());
        bean.setName(department.getName());

        bean.setPhoneNumber(department.getPhoneNumber());
        bean.setPhoneNumberPrefix(department.getPhoneNumberPrefix());
        bean.setEmail(department.getEmail());
        bean.setDepartmentStatus(department.getYejuDepartmentStatus().toString());
        bean.setCreateTime(department.getCreateTime());
        bean.setLeaderId(department.getLeaderId());

        return bean;

    }


    private String getDeptStatus(Long deptStatus) {

        IResult<Map<String, String>> result = dataDictionaryService.getDictMap(DataDictionaryTypeConstant.DEPARTMENT_STATUS);
        Map<String, String> map = result.getData();
        return map.get(deptStatus.toString());
    }


}
