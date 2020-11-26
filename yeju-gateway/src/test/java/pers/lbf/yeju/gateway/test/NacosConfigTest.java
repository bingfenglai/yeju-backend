package pers.lbf.yeju.gateway.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.yeju.gateway.config.IgnoreWhiteProperties;
import pers.lbf.yeju.gateway.start.GatewayApp;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 17:24
 */
@SpringBootTest(classes = GatewayApp.class)
@Slf4j
public class NacosConfigTest {

    @Autowired
    private IgnoreWhiteProperties properties;

  @Test
  public void test1() {

    for (String s : properties.getWhites()) {
        log.info(String.format("白名单配置：%s",s));
    }

  }
}
