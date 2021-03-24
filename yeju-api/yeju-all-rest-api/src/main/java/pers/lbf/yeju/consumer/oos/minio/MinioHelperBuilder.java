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
package pers.lbf.yeju.consumer.oos.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.consumer.oos.config.MinioConfig;

/** minioHelper 建造者
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/3 15:04
 */
@Component
public class MinioHelperBuilder {

    private final MinioHelper minioHelper = new MinioHelper();

    @Autowired
    private MinioConfig config;

    @Bean
    public MinioHelper build(){
        MinioClient minioClient =
        MinioClient.builder()
                .endpoint(config.getEndpoint())
                .credentials(config.getAccessKey(),config.getSecretKey())
                .build();
        MinioHelper.setClient(minioClient);
        MinioHelper.setBucketName(config.getBucketName());
        return minioHelper;
    }
}
