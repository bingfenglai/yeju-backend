package pers.lbf.yeju.provider.auth.start;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**服务提供者启动类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 20:15
 */
@SpringBootApplication(scanBasePackages = {"pers.lbf.yeju.provider","org.apache.dubbo.config"})
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = {"pers.lbf.yeju.provider.**.service",
                                 "org.apache.dubbo.apidocs.core.providers"})
@MapperScan(basePackages = "pers.lbf.yeju.provider.auth.**.dao")
public class YejuAuthProviderApplication {

  private static final Logger log = LoggerFactory.getLogger(YejuAuthProviderApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(YejuAuthProviderApplication.class, args);

    log.info("############################################");
    log.info("#---------   认证服务提供者启动成功！    -------#");
    log.info("############################################");



  }
}
