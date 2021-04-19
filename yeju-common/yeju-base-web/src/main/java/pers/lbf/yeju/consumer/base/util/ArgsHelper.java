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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.args.ICreateArgs;
import pers.lbf.yeju.common.core.args.IUpdateArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;

import java.util.Date;

/**
 * 参数帮助者 注入 创建者
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/4 10:10
 */
@Slf4j
@Component
public class ArgsHelper {

    @Autowired
    private static IAccountService accountService;


    public static void createArgsHelper(ICreateArgs args, ServerHttpRequest request) throws ServiceException {
        String subjectAccount = getSubjectAccount(request);
        args.setCreateBy(subjectAccount);
        args.setCreateTime(new Date());
    }

    public static void createArgsHelper(ICreateArgs args, ServerWebExchange exchange) throws ServiceException {
        createArgsHelper(args, exchange.getRequest());
    }

    public static void updateArgsHelper(IUpdateArgs args, ServerHttpRequest request) {
        String subjectAccount = getSubjectAccount(request);

        args.setChangedBy(subjectAccount);
        args.setUpdateTime(new Date());

    }

    public static void updateArgsHelper(IUpdateArgs args, ServerWebExchange webExchange) {
        ServerHttpRequest request = webExchange.getRequest();
        updateArgsHelper(args, request);
    }


    private static String getSubjectAccount(ServerHttpRequest request) {
        String subjectAccount;
        try {
            subjectAccount = SubjectHelper.getSubjectAccount(request);
            return subjectAccount;
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new ServiceException(e.getMessage(), ServiceStatusEnum.UNKNOWN_ERROR.getCode());
        }

    }


}
