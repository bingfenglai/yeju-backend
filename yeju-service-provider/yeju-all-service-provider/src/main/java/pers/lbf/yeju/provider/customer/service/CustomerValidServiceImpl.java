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

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.CustomerValid;
import pers.lbf.yeju.provider.customer.dao.ICustomerValidDao;
import pers.lbf.yeju.service.interfaces.customer.ICustomerValidService;
import pers.lbf.yeju.service.interfaces.customer.pojo.SimpleCustomerInfoBean;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/23 21:59
 */
@DubboService(interfaceClass = ICustomerValidService.class)
@Slf4j
public class CustomerValidServiceImpl implements ICustomerValidService {

    @Autowired
    private ICustomerValidDao customerValidDao;

    @Override
    public IResult<SimpleCustomerInfoBean> findDetailsById(Long id) throws ServiceException {
        CustomerValid customerValid = customerValidDao.selectById(id);
        if (customerValid == null) {
            log.error("客户不存在 customer id {}", id);
        }
        SimpleCustomerInfoBean bean = customerValidToBean(customerValid);
        return Result.ok(bean);
    }

    private SimpleCustomerInfoBean customerValidToBean(CustomerValid customerValid) {
        SimpleCustomerInfoBean simpleCustomerInfoBean = new SimpleCustomerInfoBean();
        simpleCustomerInfoBean.setCustomerId(customerValid.getCustomerId());
        simpleCustomerInfoBean.setCustomerName(customerValid.getCustomerName());
        simpleCustomerInfoBean.setGender(customerValid.getGender());
        simpleCustomerInfoBean.setPhoneNumber(customerValid.getPhoneNumber());
        simpleCustomerInfoBean.setPhoneNumberPrefix(customerValid.getPhoneNumberPrefix());
        simpleCustomerInfoBean.setCustomerStatus(customerValid.getCustomerStatus());
        simpleCustomerInfoBean.setProvince(customerValid.getProvince());
        simpleCustomerInfoBean.setCity(customerValid.getCity());
        simpleCustomerInfoBean.setAvatar(customerValid.getAvatar());
        simpleCustomerInfoBean.setEmail(customerValid.getEmail());
        simpleCustomerInfoBean.setCreateTime(customerValid.getCreateTime());
        return simpleCustomerInfoBean;
    }
}