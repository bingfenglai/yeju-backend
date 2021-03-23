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
package pers.lbf.yeju.provider.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.domain.entity.log.OperationLog;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.log.dao.OperationLogDao;
import pers.lbf.yeju.service.interfaces.log.IOperationLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.AddOperationLogRequestBean;
import pers.lbf.yeju.service.interfaces.log.pojo.OperationLogInfoBean;

import java.util.LinkedList;
import java.util.List;

/**
 * 操作日志服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/1 19:19
 */
@Service(value = "operationLogService")
@DubboService(interfaceClass = IOperationLogService.class)
public class RemoteOperationLogServiceImpl implements IOperationLogService {

    @Autowired
    private OperationLogDao operationLogDao;

    @Override
    public void addOperationLog(@Validated AddOperationLogRequestBean operationLogDTO) throws ServiceException {

        if (operationLogDTO == null) {
            throw ServiceException.getInstance("操作日志录入参数不能为空", ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

        OperationLog operationLog = new OperationLog();

        operationLog.setTitle(operationLogDTO.getTitle());
        operationLog.setBusinessType(operationLogDTO.getBusinessType());
        operationLog.setMethod(operationLogDTO.getMethod());
        operationLog.setRequestMethod(operationLogDTO.getRequestMethod());
        operationLog.setOperatorType(operationLogDTO.getOperatorType());
        operationLog.setOperatorName(operationLogDTO.getOperatorName());

        operationLog.setUrl(operationLogDTO.getUrl());
        operationLog.setIp(operationLogDTO.getIp());
        operationLog.setLocation(operationLogDTO.getLocation());
        operationLog.setParam(operationLogDTO.getParam());
        operationLog.setResult(operationLogDTO.getResult());
        operationLog.setOperationStatus(operationLogDTO.getOperationStatus());
        operationLog.setErrorMessage(operationLogDTO.getErrorMessage());
        operationLog.setOperationTime(operationLogDTO.getOperationTime());
        operationLog.setLastIpNumber(operationLogDTO.getLastIpNumber());
        operationLog.setExecuteTime(operationLogDTO.getExecuteTime());
        operationLog.setOperatorAccount(operationLogDTO.getOperatorAccount());
        operationLogDao.insert(operationLog);

    }

    @Override
    public PageResult<OperationLogInfoBean> findList(Long currentPage, Long size) throws ServiceException {
        Page<OperationLog> operationLogPage = PageUtil.getPage(OperationLog.class, currentPage, size);
        Page<OperationLog> page = operationLogDao.selectPage(operationLogPage, null);
        List<OperationLogInfoBean> result = new LinkedList<>();
        for (OperationLog operationLog : page.getRecords()) {
            OperationLogInfoBean bean = operationLogToInfoBean(operationLog);
            result.add(bean);
        }
        return PageResult.ok(page.getTotal(), currentPage, size, result);
    }

    private OperationLogInfoBean operationLogToInfoBean(OperationLog operationLog) {
        OperationLogInfoBean operationLogInfoBean = new OperationLogInfoBean();
        operationLogInfoBean.setOperationLogId(operationLog.getOperationLogId());
        operationLogInfoBean.setTitle(operationLog.getTitle());
        operationLogInfoBean.setBusinessType(operationLog.getBusinessType());
        operationLogInfoBean.setMethod(operationLog.getMethod());
        operationLogInfoBean.setRequestMethod(operationLog.getRequestMethod());
        operationLogInfoBean.setOperatorType(operationLog.getOperatorType());
        operationLogInfoBean.setOperatorName(operationLog.getOperatorName());
        operationLogInfoBean.setOperatorAccount(operationLog.getOperatorAccount());
        operationLogInfoBean.setUrl(operationLog.getUrl());
        operationLogInfoBean.setIp(operationLog.getIp());
        operationLogInfoBean.setLocation(operationLog.getLocation());
        operationLogInfoBean.setParam(operationLog.getParam());
        operationLogInfoBean.setResult(operationLog.getResult());
        operationLogInfoBean.setOperationStatus(operationLog.getOperationStatus());
        operationLogInfoBean.setErrorMessage(operationLog.getErrorMessage());
        operationLogInfoBean.setOperationTime(operationLog.getOperationTime());
        operationLogInfoBean.setExecuteTime(operationLog.getExecuteTime());
        return operationLogInfoBean;
    }
}
