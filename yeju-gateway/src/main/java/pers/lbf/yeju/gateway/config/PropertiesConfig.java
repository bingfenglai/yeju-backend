package pers.lbf.yeju.gateway.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 11:56
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "pers.lbf.yeju.gateway.**.config")
public class PropertiesConfig {
}
