package pers.lbf.yeju.gateway.web.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.gateway.config.IgnoreWhiteProperties;

/**成功时输出一些日志
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 11:06
 */
@Slf4j
@Component
public class InitSuccessListener implements ApplicationListener<WebServerInitializedEvent> {

    @Autowired
    private IgnoreWhiteProperties properties;



    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        log.info(String.format("业务网关启动成功， %d",port));
        log.info(properties.getWhites().toString());

        log.info("=================================");

    }
}
