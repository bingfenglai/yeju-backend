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
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.start.YejuProviderApplication;
import pers.lbf.yeju.service.interfaces.product.IHouseInfoService;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.product.pojo.SimpleHouseInfoBean;

import java.util.List;

/**
 * 房源服务测试类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/18 11:59
 */
@SpringBootTest(classes = YejuProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class HouseServiceTest {


    @DubboReference
    private IHouseInfoService houseInfoService;

    @Test
    public void testFindDetailsById() {
        HouseDetailsInfoBean bean = houseInfoService.findDetailsByIdAndStatus(1L, "0").getData();
        log.info(bean.toString());
    }

    @Test
    public void testFindPage() {
        List<SimpleHouseInfoBean> list =
                houseInfoService.findPage(1L, 10L).getList();

        log.info(list.toString());
    }
}
