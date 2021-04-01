package pers.lbf.yeju.gateway.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.constant.OperationStatus;
import pers.lbf.yeju.common.core.constant.OperationType;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.gateway.config.IgnoreWhiteProperties;
import pers.lbf.yeju.gateway.message.operationlog.OperationLogMessageSender;
import pers.lbf.yeju.gateway.util.HttpUtils;
import pers.lbf.yeju.service.interfaces.log.pojo.AddOperationLogRequestBean;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Objects;

/**
 * 鉴权过滤器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description 鉴权拦截
 * @date 2020/11/26 11:46
 */

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final String START_TIME = "startTime";

    @Autowired
    private IgnoreWhiteProperties properties;

    @Autowired
    private OperationLogMessageSender sender;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("走了鉴权全局过滤");
        String path = exchange.getRequest().getURI().getPath();
        ServerHttpRequest request = exchange.getRequest();
        String ip = HttpUtils.getIpAddress(request);
        // GET POST PUT DELETE
        String methodName = Objects.requireNonNull(exchange.getRequest().getMethod()).name();


        String info = String.format("Method:{%s} Host:{%s} Path:{%s} Query:{%s}",
                methodName,
                ip,
                path,
                exchange.getRequest().getQueryParams());

        log.info(info);

        //1.跳过白名单
        if (!YejuStringUtils.matches(path, properties.getWhites()) && request.getHeaders().get(TokenConstant.TOKEN_KEY) == null) {
            //2.1 token不存在直接抛出异常，走全局异常处理
            log.info("{},请求路径{}，鉴权不通过", HttpUtils.getIpAddress(request), path);
            throw new ServiceException(AuthStatusEnum.NO_TOKEN);
        }

        //2.2 token存在则放行

        //记录开始时间戳
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                //计算 执行时间
                long executeTime = (System.currentTimeMillis() - startTime);
                log.info(exchange.getRequest().getURI().getRawPath() + " : " + executeTime + "ms");

                AddOperationLogRequestBean operationLogRequestBean = new AddOperationLogRequestBean();
                operationLogRequestBean.setOperationTime(new Date());
                operationLogRequestBean.setOperationStatus(OperationStatus.success.getValue());
                operationLogRequestBean.setIp(ip);
                operationLogRequestBean.setBusinessType(getBusinessType(methodName));
                operationLogRequestBean.setMethod(path);
                operationLogRequestBean.setRequestMethod(methodName);
                operationLogRequestBean.setExecuteTime(executeTime);
                sender.send(operationLogRequestBean, null);
            }
        }));
    }


    public Integer getBusinessType(String requestMethodName) {
        if ("GET".equalsIgnoreCase(requestMethodName)) {
            return OperationType.SELECT.getValue();
        }

        if ("DELETE".equalsIgnoreCase(requestMethodName)) {
            return OperationType.DELETE.getValue();
        }

        if ("POST".equalsIgnoreCase(requestMethodName)) {
            return OperationType.INSTER.getValue();
        }

        if ("PUT".equalsIgnoreCase(requestMethodName)) {
            return OperationType.UPDATE.getValue();
        }

        return OperationType.OTHER.getValue();

    }


    @Override
    public int getOrder() {
        return -200;
    }
}
