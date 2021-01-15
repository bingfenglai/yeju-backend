/*
 * loginLogDTOright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a loginLogDTO of the License at
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
package pers.lbf.yeju.consumer.base.log.service;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.service.interfaces.log.ILoginLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.AddLoginLogRequestBean;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/29 11:21
 */
@Service
public class AsyncLoginLogServiceImpl {

    @DubboReference
    private ILoginLogService loginLogService;

    @Deprecated
    @Async
    public void addLog(AddLoginLogRequestBean loginLogDTO) throws ServiceException {
        if (loginLogDTO==null){
            throw new ServiceException(ParameStatusEnum.Parameter_cannot_be_empty);
        }

        loginLogService.addLog(loginLogDTO);
    }
}
