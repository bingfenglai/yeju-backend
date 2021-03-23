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
package pers.lbf.yeju.consumer.base.util;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.base.security.authorization.manager.AuthorizationTokenManager;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.consumer.base.service.NativeAccountService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

/**
 * 登录对象获取类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/4 9:43
 */

public class SubjectHelper {
    private SubjectHelper() {

    }

    /**
     * 获取当前访问对象账号
     *
     * @param webExchange web
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/4 9:52
     */
    public static String getSubjectAccount(ServerWebExchange webExchange) throws Exception {
        ServerHttpRequest request = webExchange.getRequest();
        return getSubjectAccount(request);

    }

    public static String getSubjectAccount(ServerHttpRequest request) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String token = Objects.requireNonNull(request.getHeaders().get(TokenConstant.TOKEN_KEY)).get(0);
        AuthorizationTokenManager tokenManager = SpringContextUtils.getBean(AuthorizationTokenManager.class);
        AuthorityInfoBean authorityInfoBean = tokenManager.getAuthorityInfo(token);
        return authorityInfoBean.getPrincipal();
    }


    public static Long getAccountId(ServerHttpRequest request) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String subjectAccount = getSubjectAccount(request);
        NativeAccountService accountService = SpringContextUtils.getBean(NativeAccountService.class);
        return accountService.findAccountIdByPrincipal(subjectAccount).getData();
    }

    public static Long getAccountId(ServerWebExchange webExchange) throws Exception {
        return getAccountId(webExchange.getRequest());
    }
}
