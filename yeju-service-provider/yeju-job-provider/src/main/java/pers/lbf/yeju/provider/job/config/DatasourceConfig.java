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
package pers.lbf.yeju.provider.job.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/23 10:07
 */
@Configuration
@Slf4j
public class DatasourceConfig {
    private static final String DATASOURCE_NAME = "dbDataSource";
    static final String SESSION_FACTORY = "dbSqlSessionFactory";


//    @Value("${mybatis.queryLimit}")
//    private String queryLimit;

    static final String MAPPER_PACKAGE = "pers.lbf.yeju.provider.job.dao";

    @Primary
    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

//    @Autowired
//    public void setDruidDataSource(SqlSessionFactoryB sqlSessionFactory){
//        sqlSessionFactory.
//    }

//    @Primary
//    @Bean(name = SESSION_FACTORY)
//    public SqlSessionFactory sqlSessionFactory() {
//        log.info("配置SqlSessionFactory开始");
//        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//
//        sessionFactoryBean.setDataSource(druidDataSource());
//        try {
//            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//            // 自定义mapper的xml文件地址，当通用mapper提供的默认功能无法满足我们的需求时，可以自己添加实现，与mybatis写mapper一样
//            //sessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_XML_PATH));
//            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//            Properties properties = new Properties();
//            //properties.put("queryLimit", queryLimit);
//            configuration.setVariables(properties);
//            configuration.setMapUnderscoreToCamelCase(true);
//            configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
//            sessionFactoryBean.setConfiguration(configuration);
//            //sessionFactoryBean.setTypeAliasesPackage(MODEL_PACKAGE);
//            return sessionFactoryBean.getObject();
//        } catch (Exception e) {
//            log.error("配置SqlSessionFactory失败，error:{}", e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//    }


}
