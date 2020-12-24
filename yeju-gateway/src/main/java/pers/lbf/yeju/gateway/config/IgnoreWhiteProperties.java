package pers.lbf.yeju.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**白名单配置信息，目前nacos只支持@RefreshScope+ConfigurationProperties
 * 可以自动刷新，@Value注入时不能刷新
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description 鉴权白名单配置
 * @date 2020/11/26 11:50
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "ignore")
public class IgnoreWhiteProperties {

    /**
     * 放行白名单，从配置中心加载
     */
    //@Value("${ignore.whites}")
    private  List<String> whites;


    public List<String> getWhites() {
        return whites;
    }

    public void setWhites(ArrayList<String> whites) {
        this.whites = whites;
    }
}
