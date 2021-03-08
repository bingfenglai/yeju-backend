package pers.lbf.yeju.gateway.start;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description 业务网关模块
 * @date 2020/11/26 1:06
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.gateway")
@Slf4j
public class YejuGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(YejuGatewayApp.class, args);
        log.info("############################################");
        log.info("#------------  微服务网关启动成功！ -----------#");
        log.info("############################################");

    }


}
