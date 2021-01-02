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
package pers.lbf.yeju.gateway.security.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.logserver.interfaces.ILoginLogService;
import pers.lbf.yeju.logserver.interfaces.dto.AddLoginLogDTO;

/**异步登录日志服务
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/1 22:49
 */
@Service
public class AsyncLoginLogServiceImpl implements ILoginLogService {

    @DubboReference
    private ILoginLogService loginLogService;

    @Async
    @Override
    public void addLog(AddLoginLogDTO loginLogDTO) throws ServiceException {
        if (loginLogDTO==null){
            throw new ServiceException(ParameStatusEnum.Parameter_cannot_be_empty);
        }

        loginLogService.addLog(loginLogDTO);
    }
}
