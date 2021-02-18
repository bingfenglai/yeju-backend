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
package pers.lbf.yeju.provider.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.dict.info.dao.IDataDictionaryInfoDao;
import pers.lbf.yeju.provider.start.YejuProviderApplication;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;

import java.util.LinkedList;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/18 12:59
 */
@SpringBootTest(classes = YejuProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class DataDictionaryInfoDaoTest {

    @Autowired
    private IDataDictionaryInfoDao dataDictionaryInfoDao;

    @Test
    public void test1(){
        LinkedList<SimpleLabelAndValueBean> gender = dataDictionaryInfoDao.selectLabelAndValueByType("notice_type");
        for (SimpleLabelAndValueBean simpleLabelAndValueBean : gender) {
            log.info(simpleLabelAndValueBean.toString());
        }
        Assert.assertNotNull(gender);
    }
}
