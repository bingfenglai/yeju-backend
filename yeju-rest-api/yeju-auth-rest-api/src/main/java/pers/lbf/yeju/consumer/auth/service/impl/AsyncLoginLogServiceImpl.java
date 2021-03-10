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
package pers.lbf.yeju.consumer.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.consumer.auth.sender.LoginLogSender;
import pers.lbf.yeju.consumer.auth.service.AsyncLoginLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.AddLoginLogRequestBean;

/**
 * 异步登录日志服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/1 22:49
 */
@Service
public class AsyncLoginLogServiceImpl implements AsyncLoginLogService {

    private static final Logger log = LoggerFactory.getLogger(AsyncLoginLogServiceImpl.class);
    @Autowired
    private LoginLogSender loginSender;

    @Override
    @Async
    public void addLog(AddLoginLogRequestBean loginLogDTO) throws ServiceException {
        if (loginLogDTO == null) {
            throw new ServiceException(ParameStatusEnum.Parameter_cannot_be_empty);
        }

        try {
            log.info("发送登录日志");
            loginSender.send(loginLogDTO, null);
        } catch (Exception e) {
            log.info("发送登录日志异常，{}", e.getMessage());
        }
    }
}
