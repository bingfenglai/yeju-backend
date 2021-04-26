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
import pers.lbf.yeju.common.domain.entity.business.product.house.HouseInfoTradable;
import pers.lbf.yeju.provider.search.repository.HouseInfoElasticsearchRepository;
import pers.lbf.yeju.provider.start.YejuProviderApplication;

import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/26 20:20
 */
@SpringBootTest(classes = YejuProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class HouseInfoSearchTest {

    @Autowired
    private HouseInfoElasticsearchRepository houseInfoElasticsearchRepository;

    @Test
    public void test() {
        HouseInfoTradable houseInfoTradable = new HouseInfoTradable();

        houseInfoTradable.setOwnerId(2L);
        houseInfoTradable.setTitle("海大房源出租了");
        houseInfoTradable.setCommunityId(1384757130706276354L);
        houseInfoTradable.setBuildingNumber("10");
        houseInfoTradable.setBuildingUint("一单元");
        houseInfoTradable.setBuildingFloorNumber("1020");
        houseInfoTradable.setRent(9699.98D);
        houseInfoTradable.setRentalMode("1");
        houseInfoTradable.setPaymentMethod("1");
        houseInfoTradable.setHouseType("1");
        houseInfoTradable.setCoveredArea(63);
        houseInfoTradable.setUseArea(45);
        houseInfoTradable.setFloors("1/6");
        houseInfoTradable.setHouseOrientation("1");
        houseInfoTradable.setHouseDecorationType("1");
        houseInfoTradable.setHouseFacilities("1");
        houseInfoTradable.setDescs("1");
        houseInfoTradable.setHouseStatus("1");
        houseInfoTradable.setHouseImagesAddress("海南省海口市美兰区人民大道58号");
        houseInfoTradable.setCreateTime(new Date());
        houseInfoTradable.setCreateBy(0L);
        houseInfoTradable.setUpdateTime(new Date());
        houseInfoTradable.setChangedBy(0L);
        houseInfoTradable.setVersionNumber(0);
        houseInfoTradable.setIsDelete(0);
        houseInfoTradable.setMonthAdded(0);
        houseInfoTradable.setMonthCompleted(0);

        houseInfoElasticsearchRepository.save(houseInfoTradable);

    }
}
