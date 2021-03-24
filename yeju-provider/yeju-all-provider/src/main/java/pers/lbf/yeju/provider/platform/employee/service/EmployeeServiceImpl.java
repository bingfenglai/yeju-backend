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
package pers.lbf.yeju.provider.platform.employee.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.common.domain.entity.Employee;
import pers.lbf.yeju.provider.base.util.SubjectUtils;
import pers.lbf.yeju.provider.platform.employee.dao.IEmployeeDao;
import pers.lbf.yeju.provider.platform.employee.status.EmployeesStatus;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryInfoBean;
import pers.lbf.yeju.service.interfaces.platfrom.department.IDepartmentService;
import pers.lbf.yeju.service.interfaces.platfrom.employee.IEmployeeService;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimpleDepartmentInfoBean;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimpleEmployeeInfoBean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/20 20:36
 */
@DubboService(interfaceClass = IEmployeeService.class)
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;

    @DubboReference
    private IDataDictionaryInfoService dataDictionaryService;

    @Lazy
    @DubboReference(check = false, interfaceClass = IDepartmentService.class)
    private IDepartmentService departmentService;

    @Cacheable(cacheNames = "employeeService:infoBean", key = "#account")
    @Override
    public IResult<SimpleEmployeeInfoBean> findInfoByAccount(String account) throws ServiceException {

        SubjectTypeEnum accountType = SubjectUtils.getAccountType(account);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        if (accountType.equals(SubjectTypeEnum.is_system_account)) {
            queryWrapper.eq("employees_number", account);
        } else if (accountType.equals(SubjectTypeEnum.is_mobile)) {
            queryWrapper.eq("phone_number", account);
        }

        Employee employee = employeeDao.selectOne(queryWrapper);
        SimpleEmployeeInfoBean bean = new SimpleEmployeeInfoBean();
        if (employee != null) {

            bean.setName(employee.getName());
            bean.setGender(getGenderLabel(employee.getGender()));
            bean.setPhoneNumber(employee.getPhoneNumber());
            bean.setPhoneNumberPrefix(employee.getPhoneNumberPrefix());

            bean.setAvatar(employee.getAvatar());
            bean.setEmail(employee.getEmail());
            bean.setEmployeesStatusStr(getEmployeesStatus(employee.getEmployeeStatus()));
            bean.setCreateTime(employee.getCreateTime());

            bean.setUpdateTime(employee.getUpdateTime());
            bean.setEmployeesNumber(employee.getEmployeesNumber());

            if (employee.getDepartmentId() != null) {
                IResult<SimpleDepartmentInfoBean> deptResult = departmentService.findDeptById(employee.getDepartmentId());
                SimpleDepartmentInfoBean simpleDepartmentInfoBean = deptResult.getData();
                bean.setDepartment(simpleDepartmentInfoBean);
            }

            return Result.ok(bean);

        }

        throw ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);
    }

    @Cacheable(cacheNames = "employeeService:name", key = "#account")
    @Override
    public IResult<String> findNameByAccount(String account) throws ServiceException {
        String name = "";
        if (account == null) {
            return Result.ok(name);
        }

        SubjectTypeEnum accountType = SubjectUtils.getAccountType(account);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        if (accountType.equals(SubjectTypeEnum.is_system_account)) {
            name = employeeDao.selectEmployeeNameByEmployeeNumber(account);
        } else if (accountType.equals(SubjectTypeEnum.is_mobile)) {
            name = employeeDao.selectEmployeeNameByPhoneNumber(account);
        }


        return Result.ok(name);
    }

    /**
     * TODO
     *
     * @param employeeId 系统账户
     * @return IResult
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:34
     */
    @Override
    @Cacheable(cacheNames = "employeeService:infoBean", key = "#employeeId")
    public IResult<SimpleEmployeeInfoBean> findInfoByEmployeeId(Long employeeId) throws ServiceException {


        if (employeeId == null) {
            throw ServiceException.getInstance("员工id不能为空", ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

        Employee employee = employeeDao.selectById(employeeId);

        if (employee == null) {
            throw ServiceException.getInstance(EmployeesStatus.employeeDoesNotExist);
        }

        SimpleEmployeeInfoBean simpleEmployeeInfoBean = new SimpleEmployeeInfoBean();
        simpleEmployeeInfoBean.setName(employee.getName());
        simpleEmployeeInfoBean.setGender(getGenderLabel(employee.getGender()));
        simpleEmployeeInfoBean.setPhoneNumber(employee.getPhoneNumber());

        simpleEmployeeInfoBean.setPhoneNumberPrefix(employee.getPhoneNumberPrefix());
        simpleEmployeeInfoBean.setLeaderId(employee.getLeaderId());
        simpleEmployeeInfoBean.setAvatar(employee.getAvatar());
        simpleEmployeeInfoBean.setEmail(employee.getEmail());
        simpleEmployeeInfoBean.setEmployeesStatusStr(getEmployeesStatus(employee.getEmployeeStatus()));

        simpleEmployeeInfoBean.setCreateTime(employee.getCreateTime());
        simpleEmployeeInfoBean.setCreateBy(employee.getCreateBy());
        simpleEmployeeInfoBean.setUpdateTime(employee.getUpdateTime());
        simpleEmployeeInfoBean.setChangedBy(employee.getChangedBy());
        simpleEmployeeInfoBean.setEmployeesNumber(employee.getEmployeesNumber());

        log.debug(simpleEmployeeInfoBean.toString());

        return Result.ok(simpleEmployeeInfoBean);

    }

    /**
     * TODO
     *
     * @param account 系统账户
     * @return void
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:36
     */
    @Override
    @CacheEvict(cacheNames = "employeeService", allEntries = true)
    public void removeEmployeeByAccount(String account) throws ServiceException {

    }

    /**
     * TODO
     *
     * @param employeeInfo 员工信息
     * @return void
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:37
     */
    @Override
    @CacheEvict(cacheNames = "employeeService:list", allEntries = true)
    public void addOne(SimpleEmployeeInfoBean employeeInfo) throws ServiceException {

    }

    /**
     * TODO
     *
     * @param employeeInfos 员工信息列表
     * @return void
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:39
     */
    @Override
    @CacheEvict(cacheNames = "employeeService:list", allEntries = true)
    public void addList(List<SimpleEmployeeInfoBean> employeeInfos) throws ServiceException {

    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @param size
     * @return list
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:42
     */

    @Cacheable(cacheNames = "employeeService:list", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleEmployeeInfoBean> findPage(Long currentPage, Long size) throws ServiceException {

        Page<Employee> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(size);
        Page<Employee> employeesPage = employeeDao.selectPage(page, null);
        List<SimpleEmployeeInfoBean> result = new LinkedList<>();

        if (employeesPage.getTotal() == 0) {
            return PageResult.ok(employeesPage.getTotal(), employeesPage.getPages(), employeesPage.getSize(), result);
        }
        Map<String, String> genderMap = getDictMap("gender");

        Map<String, String> employeeStatusMap = getDictMap("employee_status");

        for (Employee employee : employeesPage.getRecords()) {
            SimpleEmployeeInfoBean bean = new SimpleEmployeeInfoBean();
            bean.setName(employee.getName());
            bean.setGender(genderMap.get(employee.getGender().toString()));
            bean.setPhoneNumber(employee.getPhoneNumber());
            bean.setPhoneNumberPrefix(employee.getPhoneNumberPrefix());
            bean.setLeaderId(employee.getLeaderId());
            bean.setAvatar(employee.getAvatar());
            bean.setEmail(employee.getEmail());
            bean.setEmployeesStatusStr(employeeStatusMap.get(employee.getEmployeeStatus().toString()));
            bean.setEmployeesStatus(employee.getEmployeeStatus());
            bean.setCreateTime(employee.getCreateTime());
            bean.setEmployeesNumber(employee.getEmployeesNumber());

            if (employee.getDepartmentId() != null) {
                IResult<SimpleDepartmentInfoBean> deptResult = departmentService.findDeptById(employee.getDepartmentId());
                if (deptResult.getData() != null) {
                    bean.setDepartment(deptResult.getData());
                }
            }

            result.add(bean);
        }

        return PageResult.ok(employeesPage.getTotal(), employeesPage.getPages(), employeesPage.getSize(), result);


    }


    private String getGenderLabel(Long gender) {
        Map<String, String> map = getDictMap("gender");
        return map.get(gender.toString());
    }

    private String getEmployeesStatus(Long employeeStatus) {

        Map<String, String> map = getDictMap("employee_status");
        return map.get(employeeStatus.toString());
    }

    private Map<String, String> getDictMap(String type) {
        Map<String, String> map = new HashMap<>();
        IResult<List<SimpleDataDictionaryInfoBean>> result =
                dataDictionaryService.findSimpleDataDictionaryByDictType(type);

        List<SimpleDataDictionaryInfoBean> data = result.getData();

        if (data.size() > 0) {
            for (SimpleDataDictionaryInfoBean bean : data) {
                map.put(bean.getDictionaryValue(), bean.getDictionaryLabel());
            }
        }

        return map;
    }
}
