package pers.lbf.yeju.authserver.start;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 20:15
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.authserver")
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "pers.lbf.yeju.authserver.service")
@MapperScan(basePackages = "pers.lbf.yeju.authserver.dao")
public class AuthServer {

  private static final Logger log = LoggerFactory.getLogger(AuthServer.class);

  public static void main(String[] args) {
    SpringApplication.run(AuthServer.class, args);

    log.info("############################################");
    log.info("#################鉴权服务启动成功！#############");
    log.info("############################################");

  }
}
