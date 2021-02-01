package pers.lbf.yeju.provider.oos.test;/*
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

import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.yeju.provider.oos.service.ObjectUploadServiceImpl;
import pers.lbf.yeju.provider.oos.start.OosProviderApplication;
import pers.lbf.yeju.service.interfaces.oos.IFileUploadService;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/1 16:59
 */
@SpringBootTest(classes = OosProviderApplication.class)
public class UploadTest  {


    private final IFileUploadService fileUploadService = new ObjectUploadServiceImpl();


   // @Test
    public void test1(){
        fileUploadService.isExited("1111");

    }
}
