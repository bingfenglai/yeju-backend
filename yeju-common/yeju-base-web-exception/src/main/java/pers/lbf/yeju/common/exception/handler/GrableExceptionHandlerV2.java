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

package pers.lbf.yeju.common.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.*;
import pers.lbf.yeju.common.core.constant.EnvironmentConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.ErrorAndExceptionResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.exception.status.DubboRpcExceptionMessageHelper;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/25 18:47
 */
@Component
@Order(-210)
public class GrableExceptionHandlerV2 implements ErrorWebExceptionHandler {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(GrableExceptionHandlerV2.class);

    @Value("spring.profiles.active")
    private String prefix;

    public static final String DEFAULT_ERROR_MESSAGE = "内部服务错误，请联系客服";
    public static final String DEFAULT_ERROR_CODE = "e9999";

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().set("Content-Type", "application/json;charset=utf-8");
        String path = request.getURI().getPath();

        ErrorAndExceptionResult result = ErrorAndExceptionResult.getInstance(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE, path);
        result.setSuccess(false);
        doHandle(ex, result);

        log.error("\n服务请求异常 \n请求路径：{} \n请求方式：{} \n请求参数：{} \n异常信息：{}",
                path,
                request.getMethod(),
                exchange.getRequest().getQueryParams(),
                ex.getMessage());
        log.error(String.valueOf(ex));

        // 生产环境屏蔽异常细节
        if (EnvironmentConstant.prod.equals(prefix) && DEFAULT_ERROR_CODE.equals(result.getCode())) {
            result.setMessage(DEFAULT_ERROR_MESSAGE);
        }

        return response.writeWith(Mono.fromSupplier(() -> {

            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(MAPPER.writeValueAsBytes(result));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }));
    }

    private void doHandle(Throwable ex, ErrorAndExceptionResult result) {
        if (ex instanceof RpcException) {
            doHandleDubboCallRpcException((RpcException) ex, result);
        } else if (ex instanceof ServiceException) {
            doHandleServiceException((ServiceException) ex, result);
        } else if (ex instanceof ResponseStatusException) {
            doHandleResponseStatusException((ResponseStatusException) ex, result);
        } else if (ex instanceof RuntimeException) {
            doHandleRuntimeException((RuntimeException) ex, result);
        }
    }

    private void doHandleDubboCallRpcException(RpcException ex, ErrorAndExceptionResult result) {
        String message = DubboRpcExceptionMessageHelper.getMessage(ex);
        result.setMessage(message);
    }


    private void doHandleServiceException(ServiceException ex, ErrorAndExceptionResult result) {
        result.setMessage(String.format("服务异常 消息：%s 服务模块%s 请求参数%s", ex.getMessage(), ex.getModule(), Arrays.toString(ex.getParams())));
        result.setCode(ex.getExceptionCode());
    }


    private void doHandleRuntimeException(RuntimeException ex, ErrorAndExceptionResult result) {
        if (ex instanceof JwtException) {
            doHandleJwtException((JwtException) ex, result);
            return;
        }

        result.setMessage(ex.getMessage());
    }

    private void doHandleJwtException(JwtException ex, ErrorAndExceptionResult result) {
        if (ex instanceof ExpiredJwtException) {
            result.setMessage(AuthStatusEnum.NO_TOKEN.getMessage());
            result.setCode(AuthStatusEnum.NO_TOKEN.getCode());
        } else {
            result.setMessage(ex.getMessage());
        }


    }

    /**
     * http 状态异常
     *
     * @param ex
     * @param result
     */
    private void doHandleResponseStatusException(ResponseStatusException ex, ErrorAndExceptionResult result) {

        if (HttpStatus.NOT_FOUND.equals(ex.getStatus())) {
            result.setCode("404");
            result.setMessage("服务或资源不存在");
        }

        if (ex instanceof MethodNotAllowedException) {
            MethodNotAllowedException e = (MethodNotAllowedException) ex;
            result.setMessage("不允许的访问方式 " + e.getHttpMethod());
            return;
        }

        if (ex instanceof NotAcceptableStatusException) {
            NotAcceptableStatusException e = (NotAcceptableStatusException) ex;
            result.setMessage("不支持的文件类型; 仅支持：" + e.getSupportedMediaTypes().toString());
            return;

        }

        if (ex instanceof ServerErrorException) {
            ServerErrorException e = (ServerErrorException) ex;
            result.setMessage(e.getMessage());
            return;

        }
        if (ex instanceof WebExchangeBindException) {
            doHandleWebExchangeBindException((WebExchangeBindException) ex, result);
            return;
        }

        if (ex instanceof ServerWebInputException) {
            ServerWebInputException e = (ServerWebInputException) ex;
            result.setMessage(e.getMessage());
            return;
        }


        if (ex instanceof UnsupportedMediaTypeStatusException) {
            UnsupportedMediaTypeStatusException e = (UnsupportedMediaTypeStatusException) ex;
            result.setMessage(e.getMessage());
        }

    }

    /**
     * 参数校验错误拦截
     *
     * @param ex     异常
     * @param result vo
     */
    private void doHandleWebExchangeBindException(WebExchangeBindException ex, ErrorAndExceptionResult result) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        allErrors.forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        result.setMessage(errorMsg.toString());
    }

}
