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

import org.apache.dubbo.config.annotation.DubboReference;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IResourcesService;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/10 22:30
 */
//@SpringBootTest(classes = YejuProviderApplication.class)
//@RunWith(SpringRunner.class)
//@Slf4j
public class MenuTest {

    @DubboReference
    private IResourcesService resourcesService;


}
