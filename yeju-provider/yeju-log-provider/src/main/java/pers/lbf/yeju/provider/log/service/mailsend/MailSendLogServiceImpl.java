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
package pers.lbf.yeju.provider.log.service.mailsend;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.provider.log.dao.mailsendlog.IMailLogDao;
import pers.lbf.yeju.service.interfaces.log.IMailSendLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.mailsend.MailSendLogCreateArgs;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/26 10:12
 */
@DubboService(interfaceClass = IMailSendLogService.class, timeout = 10000, retries = 0)
@Slf4j
public class MailSendLogServiceImpl implements IMailSendLogService {

    @Autowired
    private IMailLogDao IMailSendLogDao;

    @Async
    @Override
    public IResult<Boolean> create(MailSendLogCreateArgs args) throws ServiceException {

        return null;
    }
}