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
package pers.lbf.yeju.provider.customer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.blur.anotations.DoBlur;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.Customer;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.customer.constant.CustomerStatusConstant;
import pers.lbf.yeju.provider.customer.dao.ICustomerDao;
import pers.lbf.yeju.provider.customer.dao.ICustomerValidDao;
import pers.lbf.yeju.provider.customer.status.CustomerServiceStatus;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.customer.ICustomerService;
import pers.lbf.yeju.service.interfaces.customer.pojo.*;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/22 10:58
 */
@DubboService(interfaceClass = ICustomerService.class, timeout = 10000, retries = 0)
@Slf4j
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private ICustomerValidDao customerValidDao;

    @DubboReference
    private IAccountService accountService;

    /**
     * 验证手机号是否已被注册
     *
     * @param phoneNumber 手机号
     * @return flag
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/23 21:05
     */
    @Override
    public IResult<Boolean> isExitPhoneNumber(String phoneNumber) throws ServiceException {
        boolean flag = customerDao.isExist(phoneNumber);
        if (flag) {
            throw ServiceException.getInstance(CustomerServiceStatus.mobilePhoneNumberHasBeenRegistered);
        }
        return Result.success();
    }

    /**
     * 客户注册
     *
     * @param args 客户注册参数封装类
     * @return flag
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 9:39
     */
    @Override
    public IResult<Boolean> registering(CustomerRegisteringArgs args) throws ServiceException {


        Customer customer = customerRegisteringArgsToCustomer(args);

        customerDao.insert(customer);

        Long accountId = accountService.generateCustomerAccount(customer.getCustomerId(), customer.getPhoneNumber()).getData();

        customerDao.initAccountIdByCustomerId(customer.getCustomerId(), accountId);

        return Result.success();
    }

    @Override
    public IResult<Boolean> cancelling(CustomerCancellationArgs cancellationArgs) throws ServiceException {
        return null;
    }

    private Customer customerRegisteringArgsToCustomer(CustomerRegisteringArgs args) {
        Customer customer = new Customer();
        customer.setPhoneNumber(args.getPhoneNumber());
        customer.setPhoneNumberPrefix(args.getPhoneNumberPrefix());
        customer.setCustomerStatus(CustomerStatusConstant.notCertified);
        customer.setCreateTime(args.getCreateTime());

        return customer;
    }

    /**
     * 客户认证
     *
     * @param args 客户认证参数封装类
     * @return flag
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 10:19
     */
    @Override
    public IResult<Boolean> authenticate(CustomerAuthenticationArgs args) throws ServiceException {

        customerDao.saveAuthenticateInfo(args);

        return null;
    }

    /**
     * 分页查询接口
     *
     * @param currentPage 当前页码
     * @param size        每页显示大小
     * @return list SimpleCustomerInfoBean
     * @throws ServiceException e
     */
    @Override
    @DoBlur
    public PageResult<SimpleCustomerInfoBean> findPage(Long currentPage, Long size) throws ServiceException {
        Page<Customer> page = PageUtil.getPage(Customer.class, currentPage, size);

        Page<Customer> customerPage = customerDao.selectPage(page, null);
        List<SimpleCustomerInfoBean> result = new LinkedList<>();
        for (Customer customer : customerPage.getRecords()) {
            SimpleCustomerInfoBean bean = customerToSimpleInfoBean(customer);
            result.add(bean);
        }
        return PageResult.ok(customerPage.getTotal(), currentPage, size, result);
    }

    private SimpleCustomerInfoBean customerToSimpleInfoBean(Customer customer) {
        SimpleCustomerInfoBean simpleCustomerInfoBean = new SimpleCustomerInfoBean();
        simpleCustomerInfoBean.setCustomerId(customer.getCustomerId());
        simpleCustomerInfoBean.setCustomerName(customer.getCustomerName());
        simpleCustomerInfoBean.setGender(customer.getGender());
        simpleCustomerInfoBean.setPhoneNumber(customer.getPhoneNumber());
        simpleCustomerInfoBean.setPhoneNumberPrefix(customer.getPhoneNumberPrefix());
        simpleCustomerInfoBean.setCustomerStatus(customer.getCustomerStatus());
        simpleCustomerInfoBean.setProvince(customer.getProvince());
        simpleCustomerInfoBean.setCity(customer.getCity());
        simpleCustomerInfoBean.setAvatar(customer.getAvatar());
        simpleCustomerInfoBean.setEmail(customer.getEmail());
        simpleCustomerInfoBean.setCreateTime(customer.getCreateTime());
        return simpleCustomerInfoBean;
    }

    /**
     * 根据抽象账号查询客户详情信息
     *
     * @param principal 抽象账号
     * @return 客户详情
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 10:26
     */
    @Override
    public IResult<CustomerDetailsInfo> findDetailsInfoByPrincipal(String principal) throws ServiceException {
        return null;
    }

    /**
     * 根据客户标识查询客户信息
     *
     * @param customerId 客户标识
     * @return 客户详情信息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 10:29
     */
    @Override
    public IResult<CustomerDetailsInfo> findDetailsInfoByCustomerId(Long customerId) throws ServiceException {

        return null;
    }

    /**
     * 更新客户资料
     *
     * @param args 更新客户资料参数封装类
     * @return flag
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 10:35
     */
    @Override
    public IResult<Boolean> update(CustomerUpdateArgs args) throws ServiceException {
        return null;
    }

    /**
     * 更新客户状态
     *
     * @param args 更新客户状态参数封装类
     * @return flag
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 10:36
     */
    @Override
    public IResult<Boolean> changeCustomerStatusByCustomerId(CustomerChangeStatusArgs args) throws ServiceException {
        return null;
    }
}