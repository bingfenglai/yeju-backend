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
package pers.lbf.yeju.provider.job.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.domain.entity.TaskLog;
import pers.lbf.yeju.provider.job.dao.ITaskLogDao;
import pers.lbf.yeju.service.interfaces.job.ITaskLogService;
import pers.lbf.yeju.service.interfaces.job.pojo.TaskLogCreateArgs;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/22 23:44
 */
@DubboService(interfaceClass = ITaskLogService.class)
@Service("taskLogService")
public class TaskLogServiceImpl implements ITaskLogService {

    @Autowired
    private ITaskLogDao TaskLogDao;

    /**
     * 添加一个
     *
     * @param args task log info
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/22 13:57
     */
    @Override
    public void addOne(TaskLogCreateArgs args) throws ServiceException {
        TaskLogDao.insert(argsToTaskLog(args));
    }


    private TaskLog argsToTaskLog(TaskLogCreateArgs args) {
        TaskLog taskLog = new TaskLog();
        taskLog.setTaskName(args.getTaskName());
        taskLog.setTaskGroup(args.getTaskGroup());
        taskLog.setInvokeTarget(args.getInvokeTarget());
        taskLog.setTaskLog(args.getTaskLog());
        taskLog.setTaskStatus(args.getTaskStatus());
        taskLog.setExceptionInfo(args.getExceptionInfo());
        taskLog.setCreateTime(args.getCreateTime());
        taskLog.setStartTime(args.getStartTime());
        taskLog.setStopTime(args.getStopTime());
        return taskLog;
    }
}