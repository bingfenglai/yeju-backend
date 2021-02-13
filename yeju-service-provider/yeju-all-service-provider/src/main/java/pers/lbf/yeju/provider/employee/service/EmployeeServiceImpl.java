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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.args.IFindPageArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.domain.entity.Employee;
import pers.lbf.yeju.provider.employee.dao.IEmployeeDao;
import pers.lbf.yeju.provider.employee.status.EmployeesStatus;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleDataDictionaryInfoBean;
import pers.lbf.yeju.service.interfaces.employee.IEmployeeService;
import pers.lbf.yeju.service.interfaces.employee.pojo.SimpleEmployeeInfoBean;

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
    private IDataDictionaryService dataDictionaryService;

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

        Employee employee = employeeDao.selectById(employeeId);

        if (employee == null){
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
        simpleEmployeeInfoBean.setEmployeesStatus(getEmployeesStatus(employee.getEmployeeStatus()));

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

    /** 分页查询
     * @return list
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:42
     */
    @Override
    public PageResult<SimpleEmployeeInfoBean> findPage(IFindPageArgs args) throws ServiceException {

        Page<Employee> page = new Page<>();
        page.setCurrent(args.getCurrentPage());
        page.setSize(args.getSize());
        Page<Employee> employeesPage = employeeDao.selectPage(page, null);
        List<SimpleEmployeeInfoBean> result = new LinkedList<>();

        if (employeesPage.getTotal()==0){
            throw ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);
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
            bean.setEmployeesStatus(employeeStatusMap.get(employee.getEmployeeStatus().toString()));

            bean.setEmployeesNumber(employee.getEmployeesNumber());

            result.add(bean);
        }

      return PageResult.ok(employeesPage.getTotal(), args.getCurrentPage(), args.getSize(), result);


    }


    private String getGenderLabel(Long gender){
        Map<String, String> map = getDictMap("gender");
        return map.get(gender.toString());
    }

    private String getEmployeesStatus(Long employeeStatus){

        Map<String, String> map = getDictMap("employee_status");
        return map.get(employeeStatus.toString());
    }

    private Map<String,String> getDictMap(String type){
        Map<String,String> map = new HashMap<>();
        IResult<List<SimpleDataDictionaryInfoBean>> result =
                dataDictionaryService.findSimpleDataDictionaryByDictType(type);

        List<SimpleDataDictionaryInfoBean> data = result.getData();

        if (data.size()>0){
            for (SimpleDataDictionaryInfoBean bean : data) {
                map.put(bean.getDictionaryValue(),bean.getDictionaryLabel());
            }
        }

        return map;
    }
}
