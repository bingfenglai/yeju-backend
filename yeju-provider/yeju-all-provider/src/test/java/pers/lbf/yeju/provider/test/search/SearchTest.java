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

package pers.lbf.yeju.provider.test.search;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.base.util.IdHelper;
import pers.lbf.yeju.provider.search.document.TestDocument;
import pers.lbf.yeju.provider.search.repository.TestElasticsearchRepository;
import pers.lbf.yeju.provider.start.YejuProviderApplication;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/26 21:23
 */
@SpringBootTest(classes = YejuProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SearchTest {

    @Autowired
    private TestElasticsearchRepository testElasticsearchRepository;

    @Autowired
    private IdHelper idHelper;

    @Test
    public void test() {
        TestDocument test = new TestDocument(idHelper.nextId(), "海南大学");
        testElasticsearchRepository.save(test);
       
    }

    @Test
    public void test1() {
        TestDocument test = new TestDocument(idHelper.nextId(), "海南大学");
        testElasticsearchRepository.save(test);
        Iterable<TestDocument> all = testElasticsearchRepository.findAll();
        log.info(all.iterator().next().toString());
    }

    @Test
    public void test2() {

        Page<TestDocument> pageResult = testElasticsearchRepository.findByName("海", null);

        log.info(pageResult.iterator().next().toString());
    }
}
