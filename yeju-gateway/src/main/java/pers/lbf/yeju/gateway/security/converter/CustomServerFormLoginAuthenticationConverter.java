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
package pers.lbf.yeju.gateway.security.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.exception.service.rpc.RpcServiceException;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.gateway.config.VerificationCodeConfig;
import pers.lbf.yeju.gateway.exception.GatewayException;
import pers.lbf.yeju.gateway.security.pojo.LoginRequestToken;
import pers.lbf.yeju.gateway.web.pojo.status.RequestStatusEnum;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Objects;

/**将表单参数转换为AuthenticationToken
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 15:22
 */
@Component
@Slf4j
public class CustomServerFormLoginAuthenticationConverter extends ServerFormLoginAuthenticationConverter {

    @Autowired
    private VerificationCodeConfig verificationCodeConfig;

    public CustomServerFormLoginAuthenticationConverter() {
        super();
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {

        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress address = request.getRemoteAddress();
        assert address != null;
        InetAddress address1 = address.getAddress();
        String hostAddress = address1.getHostAddress();
        log.info("登录IP {}",hostAddress);

        HttpHeaders headers = exchange.getRequest().getHeaders();
        String hostName = Objects.requireNonNull(headers.getHost()).getHostName();
        String key = headers.getFirst("verificationCodeKey");

        if (YejuStringUtils.isEmpty(key)){
            throw new GatewayException(RequestStatusEnum.illegalRequest);
        }

    return exchange
        .getFormData()
        .map(
            data -> {
                String principal = data.getFirst("principal");
                String certificate = data.getFirst("certificate");
                String code = data.getFirst("verificationCode");
                log.info("principal {}",principal);
                // 账号判空
                if (YejuStringUtils.isEmpty(principal)) {
                    throw new RpcServiceException(AuthStatusEnum.accountCannotBeEmpty);
                }

              // 判断登录方式，根据不同的登录方式返回不同的token

              // 手机号+验证码登录的情况
              if ((code == null || "".equals(code))) {
                if (YejuStringUtils.isEmpty(certificate)) {
                  throw new RpcServiceException(AuthStatusEnum.passwordCanNotBeBlank);
                }

                if (certificate != null
                    && certificate.length() == verificationCodeConfig.getLength()) {
                  return new LoginRequestToken(principal, certificate, hostName, key);
                } else {
                  throw new GatewayException(AuthStatusEnum.verificationCodeError);

                }
              }

              return new LoginRequestToken(principal, certificate, hostName, key, code);
            });
    }
}
