package pers.lbf.yeju.gateway.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description 业务网关模块
 * @date 2020/11/26 1:06
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju")
public class GatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class,args);
    }

}
