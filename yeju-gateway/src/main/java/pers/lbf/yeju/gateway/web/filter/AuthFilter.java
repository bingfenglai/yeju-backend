package pers.lbf.yeju.gateway.web.filter;

import com.alibaba.nacos.common.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.enumes.AuthStatus;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.gateway.config.IgnoreWhiteProperties;
import reactor.core.publisher.Mono;

import java.util.List;

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
        log.info("走了鉴权全局过滤");
        String path = exchange.getRequest().getURI().getPath();

        //1.跳过白名单
        if (YejuStringUtils.matches(path,properties.getWhites())){
            log.info(properties.getWhites().toString());
            return chain.filter(exchange);
        }

        List<String> authorizations = exchange.getRequest().getHeaders().get("Authorization");


        if (authorizations == null) {
           return setUnauthorizedResponse(exchange,AuthStatus.NO_TOKEN);
        }

       // String token = authorizations.get(0);



        return chain.filter(exchange);
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, AuthStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        log.info("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());

        SimpleResult result = SimpleResult.faild(status.getCode(), status.getMessage());

        return response.writeWith(
            Mono.fromSupplier(
                () -> {
                  DataBufferFactory bufferFactory = response.bufferFactory();
                  return bufferFactory.wrap(JacksonUtils.toJsonBytes(result));
                }));
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
