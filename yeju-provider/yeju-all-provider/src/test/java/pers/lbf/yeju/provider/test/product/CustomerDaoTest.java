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

package pers.lbf.yeju.provider.test.product;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.customer.dao.ICustomerDao;
import pers.lbf.yeju.provider.start.YejuProviderApplication;
import pers.lbf.yeju.service.interfaces.customer.pojo.CustomerAuthenticationArgs;

import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/22 23:22
 */
@SpringBootTest(classes = YejuProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class CustomerDaoTest {
    @Autowired
    private ICustomerDao customerDao;

    //@Test
    public void testSaveAuthenticationInfo() {
        CustomerAuthenticationArgs args = new CustomerAuthenticationArgs();
        args.setCustomerId(1L);
        args.setChangedBy(969391L);
        args.setCustomerName("赖柄沣");
        args.setCity(120L);
        args.setProvince(9L);
        args.setGender("1");
        args.setCustomerStatus("1");

        args.setUpdateTime(new Date());

        int i = customerDao.saveAuthenticateInfo(args);

        log.info("修改记录数 {}", i);
    }

    @Test
    public void testIsExist() {
        boolean flag = customerDao.isExist("17330937086");
        log.info(String.valueOf(flag));
        //Assert.assertTrue(flag);

    }
}
