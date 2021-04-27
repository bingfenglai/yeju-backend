package pers.lbf.yeju.provider.start;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 服务提供者启动类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 20:15
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.provider")
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "pers.lbf.yeju.provider.**.service")
@MapperScan(basePackages = {"pers.lbf.yeju.provider.**.dao"})
@EnableElasticsearchRepositories(basePackages = "pers.lbf.yeju.provider.**.repository")
public class YejuProviderApplication {

    private static final Logger log = LoggerFactory.getLogger(YejuProviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(YejuProviderApplication.class, args);

        log.info("############################################");
        log.info("#---------   服务提供者启动成功！    ----------#");
        log.info("############################################");

    }
}
