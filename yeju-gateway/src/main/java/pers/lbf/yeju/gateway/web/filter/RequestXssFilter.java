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

package pers.lbf.yeju.gateway.web.filter;

import io.netty.buffer.ByteBufAllocator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import pers.lbf.yeju.gateway.util.XssFilterUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * XSS攻击过滤器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/1 23:17
 */
@Component
public class RequestXssFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(RequestXssFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // grab configuration from Config object
        log.debug("----自定义防XSS攻击网关全局过滤器生效----");
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpMethod method = serverHttpRequest.getMethod();
        String contentType = serverHttpRequest.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);

        Boolean postFlag = (method == HttpMethod.POST || method == HttpMethod.PUT) &&
                (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType) || MediaType.APPLICATION_JSON_VALUE.equals(contentType) || MediaType.APPLICATION_JSON_UTF8_VALUE.equals(contentType));

        // get 请求， 参考的是 org.springframework.cloud.gateway.filter.factory.AddRequestParameterGatewayFilterFactory
        if (method == HttpMethod.GET) {
            URI uri = exchange.getRequest().getURI();

            String rawQuery = uri.getRawQuery();
            if (StringUtils.isBlank(rawQuery)) {
                return chain.filter(exchange);
            }
            rawQuery = XssFilterUtil.xssFilt(rawQuery);
            try {
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(rawQuery)
                        .build(true)
                        .toUri();

                ServerHttpRequest request = exchange.getRequest().mutate()
                        .uri(newUri).build();
                return chain.filter(exchange.mutate().request(request).build());
            } catch (Exception e) {
                log.error("get请求清理xss攻击异常", e);
                throw new IllegalStateException("Invalid URI query: \"" + rawQuery + "\"");
            }
        }
        //post请求时，如果是文件上传之类的请求，不修改请求消息体
        else if (postFlag) {
            // 参考的是 org.springframework.cloud.gateway.filter.factory.AddRequestParameterGatewayFilterFactory

            //从请求里获取Post请求体
            String bodyStr = resolveBodyFromRequest(serverHttpRequest);
            // 这种处理方式，必须保证post请求时，原始post表单必须有数据过来，不然会报错
            if (StringUtils.isBlank(bodyStr)) {
                log.error("请求异常：{} POST请求必须传递参数", serverHttpRequest.getURI().getRawPath());
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                byte[] bytes = "{\"code\":400,\"msg\":\"post data error\"}".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bytes);
                return response.writeWith(Mono.just(buffer));
            }
            bodyStr = XssFilterUtil.xssFilt(bodyStr);

            URI uri = serverHttpRequest.getURI();
            URI newUri = UriComponentsBuilder.fromUri(uri).build(true).toUri();
            ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
            DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

            // 定义新的消息头
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());


            // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
            int length = bodyStr.getBytes().length;
            headers.remove(HttpHeaders.CONTENT_LENGTH);
            headers.setContentLength(length);

            // 设置CONTENT_TYPE
            if (StringUtils.isNotBlank(contentType)) {
                headers.set(HttpHeaders.CONTENT_TYPE, contentType);
            }

            // 由于post的body只能订阅一次，由于上面代码中已经订阅过一次body。所以要再次封装请求到request才行，不然会报错请求已经订阅过
            request = new ServerHttpRequestDecorator(request) {
                @Override
                public HttpHeaders getHeaders() {
                    long contentLength = headers.getContentLength();
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.putAll(super.getHeaders());
                    if (contentLength > 0) {
                        httpHeaders.setContentLength(contentLength);
                    } else {
                        // this causes a 'HTTP/1.1 411 Length Required' on httpbin.org
                        httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                    }
                    return httpHeaders;
                }

                @Override
                public Flux<DataBuffer> getBody() {
                    return bodyFlux;
                }
            };

            //封装request，传给下一级
            request.mutate().header(HttpHeaders.CONTENT_LENGTH, Integer.toString(bodyStr.length()));
            return chain.filter(exchange.mutate().request(request).build());
        } else {
            return chain.filter(exchange);
        }

    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     *
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取request body
        return bodyRef.get();
    }

    /**
     * 字符串转DataBuffer
     *
     * @param value
     * @return
     */
    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }


    @Override
    public int getOrder() {
        return -199;
    }
}
