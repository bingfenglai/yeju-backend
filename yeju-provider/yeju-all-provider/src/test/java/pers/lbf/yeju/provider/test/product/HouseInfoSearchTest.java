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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.base.util.IdHelper;
import pers.lbf.yeju.provider.product.house.repository.HouseInfoElasticsearchRepository;
import pers.lbf.yeju.provider.start.YejuProviderApplication;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseInfoDoc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private IdHelper idHelper;

    @Autowired
    private HouseInfoElasticsearchRepository houseInfoRepository;


    @Test
    public void test1() {


        // Read file in stream mode
        try {
            InputStream inputStream = new FileInputStream("E:\\graduation project\\yeju_code\\yeju_dev\\yeju-provider\\yeju-all-provider\\src\\test\\resources\\data.json");

            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new GsonBuilder().create();
            reader.beginArray();
            int i = 0;
            while (reader.hasNext()) {
                // Read data into object model
                HouseInputModel inputModel = gson.fromJson(reader, HouseInputModel.class);
                HouseInfoDoc doc = modelToDoc(inputModel);
                houseInfoRepository.save(doc);
                log.info("正在录入第{}个房源", i + 1);
                i = i + 1;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private HouseInfoDoc modelToDoc(HouseInputModel inputModel) {
        final String baseUrl = "http://8.129.77.225:9000/yeju/house/p/";
        HouseInfoDoc houseInfoDoc = new HouseInfoDoc();
        houseInfoDoc.setHouseId(idHelper.nextId());
        houseInfoDoc.setTitle(inputModel.getTitle());
        houseInfoDoc.setRent(inputModel.getRent());
        houseInfoDoc.setHouseType(inputModel.getHouseType());
        houseInfoDoc.setCoveredArea((int) (Math.random() * 5000));
        houseInfoDoc.setHouseOrientation("坐北朝南");
        houseInfoDoc.setHouseDecorationType("精装修");
        houseInfoDoc.setHouseImagesAddress(baseUrl + inputModel.getImage());

        houseInfoDoc.setRentalMode(inputModel.getRentMethod());

        houseInfoDoc.setCreateTime(new Date());
        houseInfoDoc.setDetailsAddress("海口市美兰区金山花园");
        return houseInfoDoc;
    }


    @Test
    public void test3() {

        PageRequest page = PageRequest.of(1, 10);
        Page<HouseInfoDoc> search = houseInfoRepository.search(QueryBuilders.multiMatchQuery("季付", "title", "paymentMethod"), page);
        for (HouseInfoDoc houseInfoDoc : search.getContent()) {
            log.info(houseInfoDoc.toString());
        }
    }


}
