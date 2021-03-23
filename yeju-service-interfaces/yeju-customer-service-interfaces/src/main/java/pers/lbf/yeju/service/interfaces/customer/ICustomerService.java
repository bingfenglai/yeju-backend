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
package pers.lbf.yeju.service.interfaces.customer;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.customer.pojo.*;

/**
 * 客户服务接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/8 13:07
 */
public interface ICustomerService {

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
    IResult<Boolean> registering(CustomerRegisteringArgs args) throws ServiceException;

    /**
     * 将客户认证信息存入数据库
     *
     * @param args 客户认证参数封装类
     * @return flag
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 10:19
     */
    IResult<Boolean> authenticate(CustomerAuthenticationArgs args) throws ServiceException;

    /**
     * 分页查询接口
     *
     * @param currentPage 当前页码
     * @param size        每页显示大小
     * @return list SimpleCustomerInfoBean
     * @throws ServiceException e
     */
    PageResult<SimpleCustomerInfoBean> findPage(Long currentPage, Long size) throws ServiceException;

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
    IResult<CustomerDetailsInfo> findDetailsInfoByPrincipal(String principal) throws ServiceException;

    /**
     * 根据客户标识查询客户信息
     *
     * @param customerId 客户标识
     * @return 客户详情信息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/22 10:29
     */
    IResult<CustomerDetailsInfo> findDetailsInfoByCustomerId(Long customerId) throws ServiceException;


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
    IResult<Boolean> update(CustomerUpdateArgs args) throws ServiceException;


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
    IResult<Boolean> changeCustomerStatusByCustomerId(CustomerChangeStatusArgs args) throws ServiceException;
}
