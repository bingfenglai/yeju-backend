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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.domain.entity.TaskProperties;
import pers.lbf.yeju.provider.job.dao.ITaskPropertiesDao;
import pers.lbf.yeju.service.interfaces.job.IJobPropertiesService;
import pers.lbf.yeju.service.interfaces.job.pojo.TaskPropertiesCreateArgs;

import java.util.List;
import java.util.Properties;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/27 19:33
 */
@DubboService(interfaceClass = IJobPropertiesService.class)
public class JobPropertiesServiceImpl implements IJobPropertiesService {

    @Autowired
    private ITaskPropertiesDao JobPropertiesDao;

    /**
     * 通过任务id 查询 执行任务所需参数名 参数值
     *
     * @param jobId job id
     * @return job data
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/27 16:39
     */
    @Override
    public IResult<Properties> findPropertiesByJobId(Long jobId) throws ServiceException {
        QueryWrapper<TaskProperties> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("task_id", jobId);
        queryWrapper.select("properties_name", "properties_value");
        List<TaskProperties> taskProperties = JobPropertiesDao.selectList(queryWrapper);
        Properties result = new Properties();
        for (TaskProperties taskProperty : taskProperties) {
            result.put(taskProperty.getPropertiesName(), taskProperty.getPropertiesValue());
        }
        return Result.ok(result);
    }

    @Override
    public IResult<Object> create(TaskPropertiesCreateArgs args) throws ServiceException {
        TaskProperties taskProperties = taskPropertiesCreateArgsToTaskProperties(args);
        JobPropertiesDao.insert(taskProperties);
        return SimpleResult.ok();
    }


    private TaskProperties taskPropertiesCreateArgsToTaskProperties(TaskPropertiesCreateArgs args) {
        TaskProperties taskProperties = new TaskProperties();
        taskProperties.setTaskId(args.getTaskId());
        taskProperties.setPropertiesName(args.getPropertiesName());
        taskProperties.setPropertiesValue(args.getPropertiesValue());
        taskProperties.setCreateTime(args.getCreateTime());
        taskProperties.setCreateBy(args.getCreateBy());
        return taskProperties;

    }
}