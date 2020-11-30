package pers.lbf.yeju.authserver.start;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = "pers.lbf.yeju.auth.dao")
public class AuthServer {

  public static void main(String[] args) {
    SpringApplication.run(AuthServer.class, args);
  }
}
