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
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.constant.EnvironmentConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.ErrorAndExceptionResult;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.exception.status.DubboRpcExceptionMessageHelper;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/9 22:04
 */
//@Configuration
//@Order(-2)
@Deprecated
public class GrableExceptionHandler implements ErrorWebExceptionHandler {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(GrableExceptionHandler.class);

    @Value("spring.profiles.active")
    private String prefix;

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
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        String message = "内部服务错误，请联系客服";
        String code = "e9999";
        IResult<Object> result;
        String path = String.valueOf(exchange.getRequest().getPath());
        if (ex instanceof ResponseStatusException) {
            if (HttpStatus.NOT_FOUND.equals(((ResponseStatusException) ex).getStatus())) {
                message = "服务不存在";
                code = "404";

            } else {
                message = ex.getMessage();
                code = String.valueOf(((ResponseStatusException) ex).getStatus());
            }

        } else if (ex instanceof ServiceException) {
            message = ex.getMessage();
            code = ((ServiceException) ex).getExceptionCode();
            log.info("[服务异常处理]请求路径:{},异常信息:{}", path, ex.getMessage());
            log.info(Arrays.toString(ex.getStackTrace()));
        }


        if (ex instanceof WebExchangeBindException) {
            WebExchangeBindException exception = (WebExchangeBindException) ex;
            //List<FieldError> allErrors = exception.getBindingResult().getFieldErrors();
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();

            StringBuffer errorMsg = new StringBuffer();
            allErrors.forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));


            message = errorMsg.toString();
            log.error("[服务异常处理]请求路径:{},异常信息:{}", path, message);
        } else if (ex instanceof NullPointerException) {
            NullPointerException e = (NullPointerException) ex;
            message = "空指针异常";
            log.error("[服务异常处理]请求路径:{},异常信息:{}", path, e.getMessage());
            log.error(String.valueOf(e));
        } else if (ex instanceof RpcException) {
            RpcException e = (RpcException) ex;
            String dubboMsg = DubboRpcExceptionMessageHelper.getMessage(e);
            if (!EnvironmentConstant.prod.equals(prefix)) {
                message = dubboMsg;
            }
            log.error("[服务异常处理]请求路径:{},异常信息:{}", path, dubboMsg);
            log.error(String.valueOf(ex));
        } else {
            if (EnvironmentConstant.prod.equals(prefix)) {
                message = "内部服务错误，请联系客服" + ex.getMessage();
            }
        }

        if (ex instanceof ExpiredJwtException) {
            message = AuthStatusEnum.NO_TOKEN.getMessage();
            code = AuthStatusEnum.NO_TOKEN.getCode();
        }


        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);
        result = ErrorAndExceptionResult.getInstance(code, message, path);
        IResult<Object> finalResult = result;

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(MAPPER.writeValueAsBytes(finalResult));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }));

    }
}
