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
package pers.lbf.yeju.provider.log.start;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.TimeZone;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/1 16:51
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.provider")
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "pers.lbf.yeju.provider.log.service")
@MapperScan("pers.lbf.yeju.provider.log.dao")
@Slf4j
public class LogApplication {


  public static void main(String[] args) {
    SpringApplication.run(LogApplication.class,args);
    JSONObject.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");

    log.info("############################################");
    log.info("#------------日志服务提供者启动成功！----------#");
    log.info("############################################");

  }
}
