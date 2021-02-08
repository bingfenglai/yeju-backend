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
package pers.lbf.yeju.provider.employee.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.domain.entity.Employees;
import pers.lbf.yeju.provider.employee.dao.IEmployeeDao;
import pers.lbf.yeju.provider.employee.status.EmployeesStatus;
import pers.lbf.yeju.service.interfaces.employee.IEmployeeService;
import pers.lbf.yeju.service.interfaces.employee.pojo.SimpleEmployeeInfoBean;

import java.util.List;

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
    public IResult<SimpleEmployeeInfoBean> findInfoByEmployeeId(Long employeeId) throws ServiceException {


        if (employeeId==null){
           throw ServiceException.getInstance("员工id不能为空", ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

        Employees employees = employeeDao.selectById(employeeId);

        if (employees == null){
            throw ServiceException.getInstance(EmployeesStatus.employeeDoesNotExist);
        }

        SimpleEmployeeInfoBean simpleEmployeeInfoBean = new SimpleEmployeeInfoBean();
        simpleEmployeeInfoBean.setName(employees.getName());
        simpleEmployeeInfoBean.setGender(employees.getGender());
        simpleEmployeeInfoBean.setGenderValue(employees.getGenderValue());
        simpleEmployeeInfoBean.setPhoneNumber(employees.getPhoneNumber());
        simpleEmployeeInfoBean.setPhoneNumberPrefix(employees.getPhoneNumberPrefix());
        simpleEmployeeInfoBean.setPhoneNumberPrefixValue(employees.getPhoneNumberPrefixValue());
        simpleEmployeeInfoBean.setLeaderId(employees.getLeaderId());
        simpleEmployeeInfoBean.setAvatar(employees.getAvatar());
        simpleEmployeeInfoBean.setEmail(employees.getEmail());
        simpleEmployeeInfoBean.setEmployeesStatus(employees.getEmployeesStatus());
        simpleEmployeeInfoBean.setEmployeesStatusValue(employees.getEmployeesStatusValue());
        simpleEmployeeInfoBean.setCreateTime(employees.getCreateTime());
        simpleEmployeeInfoBean.setCreateBy(employees.getCreateBy());
        simpleEmployeeInfoBean.setUpdateTime(employees.getUpdateTime());
        simpleEmployeeInfoBean.setChangedBy(employees.getChangedBy());
        simpleEmployeeInfoBean.setEmployeesNumber(employees.getEmployeesNumber());

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
    public void addList(List<SimpleEmployeeInfoBean> employeeInfos) throws ServiceException {

    }

    /**
     * @return list
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:42
     */
    @Override
    public IResult<SimpleEmployeeInfoBean> findList() throws ServiceException {
        return null;
    }
}
