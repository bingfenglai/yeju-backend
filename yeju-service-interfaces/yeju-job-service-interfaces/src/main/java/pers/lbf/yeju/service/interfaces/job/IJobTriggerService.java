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
package pers.lbf.yeju.service.interfaces.job;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.job.pojo.FindTriggerNameAndGroupByJobIdsResponse;
import pers.lbf.yeju.service.interfaces.job.pojo.JobTriggerInfoBean;
import pers.lbf.yeju.service.interfaces.job.pojo.TriggerCreateArgs;
import pers.lbf.yeju.service.interfaces.job.pojo.TriggerUpdateArgs;

import java.util.List;

/**
 * 任务触发器接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/27 16:20
 */
public interface IJobTriggerService {

    /**
     * 查询任务触发器
     *
     * @param jobId 任务id
     * @return list triggerInfoBeanList
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/27 16:43
     */
    IResult<List<JobTriggerInfoBean>> findTriggerInfoBeanListByJobId(Long jobId) throws ServiceException;

    IResult<Object> create(TriggerCreateArgs args) throws ServiceException;

    IResult<Object> update(TriggerUpdateArgs args) throws ServiceException;

    IResult<Object> deleteByJobId(Long jobId) throws ServiceException;

    IResult<Object> deleteByJobIds(Long[] jobIds) throws ServiceException;

    IResult<List<FindTriggerNameAndGroupByJobIdsResponse>> findTriggerNameAndGroupByJobIds(Long[] jobIds) throws ServiceException;


}
