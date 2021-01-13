package pers.lbf.yeju.consumer.authrestapi.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**认证授权模块启动类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 10:51
 */
@SpringBootApplication(scanBasePackages = "pers.lbf.yeju.consumer")
@EnableDiscoveryClient
public class AuthRestApi {

  public static void main(String[] args) {
      SpringApplication.run(AuthRestApi.class,args);
  }
}
