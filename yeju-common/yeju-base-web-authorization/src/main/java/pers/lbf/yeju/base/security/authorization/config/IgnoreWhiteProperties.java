package pers.lbf.yeju.base.security.authorization.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单配置信息，目前nacos只支持@RefreshScope+ConfigurationProperties
 * 可以自动刷新，@Value注入时不能刷新
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description 鉴权白名单配置
 * @date 2020/11/26 11:50
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "ignore")
public class IgnoreWhiteProperties {


    private List<String> whites;


    public List<String> getWhites() {
        return whites;
    }

    public void setWhites(ArrayList<String> whites) {
        this.whites = whites;
    }

    public String[] getWhiteArrays() {
        String[] strings = new String[10];
        strings = getWhites().toArray(new String[whites.size()]);
        return strings;
    }
}
