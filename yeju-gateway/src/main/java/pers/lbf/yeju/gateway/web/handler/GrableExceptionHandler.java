/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package pers.lbf.yeju.gateway.web.handler;

import com.alibaba.nacos.common.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.gateway.pojo.ErrorAndExceptionResult;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/9 22:04
 */
@Configuration
@Order(-2)
public class GrableExceptionHandler implements ErrorWebExceptionHandler {

    private static final Logger log =  LoggerFactory.getLogger(GrableExceptionHandler.class);

    /**
     * Handle the given exception. A completion signal through the return value
     * indicates error handling is complete while an error signal indicates the
     * exception is still not handled.
     *
     * @param exchange the current exchange
     * @param ex       the exception to handle
     * @return {@code Mono<Void>} to indicate when exception handling is complete
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted())
        {
            return Mono.error(ex);
        }

        String message;
        String code;
        IResult<String> result = null;
        if (ex instanceof NotFoundException){
            message = "服务不存在";
            code = "404";
        }
        else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            message = ex.getMessage();
            code = ((ResponseStatusException) ex).getStatus().toString();
        }
        else if (ex instanceof ServiceException){
            message = ex.getMessage();
            code = ((ServiceException) ex).getExceptionCode();

        }
        else {
            message = "内部服务错误，请联系客服"+ex.getMessage();
            code = "e9999";
        }
        String path = String.valueOf(exchange.getRequest().getPath());

        if (ex instanceof ServiceException){
            log.info("[网关异常处理]请求路径:{},异常信息:{}", path, ex.getMessage());
            log.info(Arrays.toString(ex.getStackTrace()));
        }else if (ex instanceof ResponseStatusException) {
            log.info("[网关异常处理]请求路径:{},异常信息:{}", path, ex.getMessage());
            log.info(Arrays.toString(ex.getStackTrace()));
        } else {
            log.error("[网关异常处理]请求路径:{},异常信息:{}", path, ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
        }



        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);
        result = ErrorAndExceptionResult.getInstance(code,message,path);
        IResult<String> finalResult = result;

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JacksonUtils.toJsonBytes(finalResult));
        }));

    }
}
