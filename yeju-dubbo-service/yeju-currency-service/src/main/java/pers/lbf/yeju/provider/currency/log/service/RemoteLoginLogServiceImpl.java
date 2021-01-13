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
package pers.lbf.yeju.provider.currency.log.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.domain.entity.LoginLog;
import pers.lbf.yeju.logserver.interfaces.ILoginLogService;
import pers.lbf.yeju.logserver.interfaces.dto.AddLoginLogDTO;
import pers.lbf.yeju.provider.currency.log.dao.LoginLogDao;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/1 17:05
 */
@DubboService(interfaceClass = ILoginLogService.class)
@Service
public class RemoteLoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    @Async
    public void addLog(AddLoginLogDTO loginLogDTO) throws ServiceException {
        LoginLog loginLog = new LoginLog();

        loginLog.setAccount(loginLogDTO.getAccount());
        loginLog.setSubjectName(loginLogDTO.getSubjectName());
        loginLog.setIp(loginLogDTO.getIp());
        loginLog.setLoginStatus(loginLogDTO.getLoginStatus());
        loginLog.setMessage(loginLogDTO.getMessage());
        loginLog.setAccentTime(loginLogDTO.getAccentTime());
        loginLog.setLastIpNumber(loginLogDTO.getLastIpNumber());


        loginLogDao.insert(loginLog);


    }
}
