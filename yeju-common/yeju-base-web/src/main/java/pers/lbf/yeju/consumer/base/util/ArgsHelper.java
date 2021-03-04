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
import org.springframework.http.server.reactive.ServerHttpRequest;
import pers.lbf.yeju.common.core.args.CreateArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;

import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/4 10:10
 */
@Slf4j
public class ArgsHelper {

    private ArgsHelper() {

    }

    public static CreateArgs createArgsHelper(CreateArgs args, ServerHttpRequest request) throws ServiceException {
        String subjectAccount = null;
        try {
            subjectAccount = SubjectHelper.getSubjectAccount(request);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new ServiceException(e.getMessage(), ServiceStatusEnum.UNKNOWN_ERROR.getCode());
        }
        args.setCreateBy(subjectAccount);
        args.setCreateTime(new Date());

        return args;
    }
}
