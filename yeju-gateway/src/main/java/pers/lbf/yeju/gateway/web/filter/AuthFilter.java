package pers.lbf.yeju.gateway.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.gateway.config.IgnoreWhiteProperties;
import reactor.core.publisher.Mono;

/**鉴权过滤器
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description 鉴权拦截
 * @date 2020/11/26 11:46
 */

@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private IgnoreWhiteProperties properties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        //1.跳过白名单
        if (YejuStringUtils.matches(path,properties.getWhites())){
            log.info(properties.getWhites().toString());
            return chain.filter(exchange);
        }
        log.info("走了鉴权全局过滤");

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
