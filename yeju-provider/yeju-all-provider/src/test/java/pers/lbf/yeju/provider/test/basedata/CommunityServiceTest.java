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

package pers.lbf.yeju.provider.test.basedata;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.provider.start.YejuProviderApplication;
import pers.lbf.yeju.service.basedata.community.interfaces.ICommunityService;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityQueryArgs;
import pers.lbf.yeju.service.basedata.community.pojo.SimpleCommunityInfoBean;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/21 14:38
 */
@SpringBootTest(classes = YejuProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class CommunityServiceTest {

    @DubboReference
    private ICommunityService communityService;

    @Test
    public void testQuery() {
        CommunityQueryArgs args = new CommunityQueryArgs();
        args.setSize(10L);
        args.setCurrentPage(1L);
        List<SimpleCommunityInfoBean> list = communityService.query(args).getList();
        log.info(list.toString());
        Assert.assertTrue(list.size() > 0);
    }
}
