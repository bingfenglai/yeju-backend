/*
 * operationLogDTOright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a operationLogDTO of the License at
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
import org.springframework.validation.annotation.Validated;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.domain.entity.OperationLog;
import pers.lbf.yeju.logserver.interfaces.IOperationLogService;
import pers.lbf.yeju.logserver.interfaces.dto.AddOperationLogDTO;
import pers.lbf.yeju.provider.currency.log.dao.OperationLogDao;

/**操作日志服务
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/1 19:19
 */
@DubboService(interfaceClass = IOperationLogService.class)
public class RemoteOperationLogServiceImpl implements IOperationLogService {
    
    @Autowired
    private OperationLogDao operationLogDao;
    
    @Override
    public void addOperationLog( @Validated AddOperationLogDTO operationLogDTO) throws ServiceException {
        
        if (operationLogDTO==null){
            throw new ServiceException(ParameStatusEnum.Parameter_cannot_be_empty);
        }

        OperationLog operationLog = new OperationLog();
        
        operationLog.setTitle(operationLogDTO.getTitle());
        operationLog.setBusinessType(operationLogDTO.getBusinessType());
        operationLog.setMethod(operationLogDTO.getMethod());
        operationLog.setRequestMethod(operationLogDTO.getRequestMethod());
        operationLog.setOperatorType(operationLogDTO.getOperatorType());
        operationLog.setOperatorName(operationLogDTO.getOperatorName());
        operationLog.setDepartmentName(operationLogDTO.getDepartmentName());
        operationLog.setUrl(operationLogDTO.getUrl());
        operationLog.setIp(operationLogDTO.getIp());
        operationLog.setLocation(operationLogDTO.getLocation());
        operationLog.setParam(operationLogDTO.getParam());
        operationLog.setResult(operationLogDTO.getResult());
        operationLog.setOperationStatus(operationLogDTO.getOperationStatus());
        operationLog.setErrorMessage(operationLogDTO.getErrorMessage());
        operationLog.setOperationTime(operationLogDTO.getOperationTime());
        operationLog.setLastIpNumber(operationLogDTO.getLastIpNumber());
        
        operationLogDao.insert(operationLog);
        
    }
}
