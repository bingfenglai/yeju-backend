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
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.domain.entity.JobTrigger;
import pers.lbf.yeju.provider.job.dao.IJobTriggerDao;
import pers.lbf.yeju.service.interfaces.job.IJobTriggerService;
import pers.lbf.yeju.service.interfaces.job.pojo.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/28 15:07
 */
@DubboService(interfaceClass = IJobTriggerService.class)
public class TriggerServiceImpl implements IJobTriggerService {

    @Autowired
    private IJobTriggerDao triggerDao;

    /**
     * 查询任务触发器
     *
     * @param taskId 任务id
     * @return list triggerInfoBeanList
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/27 16:43
     */
    @Override
    public IResult<List<JobTriggerInfoBean>> findTriggerInfoBeanListByJobId(Long taskId) throws ServiceException {
        QueryWrapper<JobTrigger> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id", taskId);
        List<JobTrigger> jobTriggers = triggerDao.selectList(queryWrapper);
        List<JobTriggerInfoBean> result = new LinkedList<>();
        for (JobTrigger jobTrigger : jobTriggers) {
            JobTriggerInfoBean bean = this.triggerToInfoBean(jobTrigger);
            result.add(bean);
        }
        return Result.ok(result);
    }

    @Override
    public IResult<Object> create(TriggerCreateArgs args) throws ServiceException {
        JobTrigger trigger = triggerCreateArgsToJobTrigger(args);
        triggerDao.insert(trigger);
        return SimpleResult.ok();
    }

    private JobTrigger triggerCreateArgsToJobTrigger(TriggerCreateArgs args) {
        JobTrigger jobTrigger = new JobTrigger();
        jobTrigger.setTaskId(args.getTaskId());
        jobTrigger.setTriggerName(args.getTriggerName());
        jobTrigger.setTriggerGroupName(args.getTriggerGroupName());
        jobTrigger.setCron(args.getCron());
        jobTrigger.setTriggerType(args.getTriggerType());
        jobTrigger.setCreateTime(args.getCreateTime());
        jobTrigger.setCreateBy(args.getCreateBy());
        jobTrigger.setStartTime(args.getStartTime());
        jobTrigger.setEndTime(args.getEndTime());
        jobTrigger.setTimezone(args.getTimezone());
        jobTrigger.setDescription(args.getDescription());
        return jobTrigger;

    }


    @Override
    public IResult<Object> update(TriggerUpdateArgs args) throws ServiceException {

        JobTrigger trigger = triggerUpdateArgsToJobTrigger(args);
        triggerDao.updateById(trigger);
        return SimpleResult.ok();
    }

    @Override
    public IResult<Object> deleteByJobId(Long jobId) throws ServiceException {
        QueryWrapper<JobTrigger> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id", jobId);
        triggerDao.delete(queryWrapper);
        return SimpleResult.ok();
    }

    @Override
    public IResult<Object> deleteByJobIds(Long[] jobIds) throws ServiceException {
        if (jobIds == null || jobIds.length == 0) {
            throw ServiceException.getInstance("任务id不能为空", ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        } else {
            triggerDao.deleteByJobIds(jobIds);
        }
        return SimpleResult.ok();
    }


    @Override
    public IResult<List<FindTriggerNameAndGroupByJobIdsResponse>> findTriggerNameAndGroupByJobIds(Long[] jobIds)
            throws ServiceException {
        if (jobIds == null || jobIds.length == 0) {
            throw ServiceException.getInstance("任务id不能为空", ParameStatusEnum.invalidParameter.getCode());
        } else {
            List<FindTriggerNameAndGroupByJobIdsResponse> result = new LinkedList<>();
            for (Long jobId : jobIds) {
                List<TriggerNameAndGroupInfoBean> triggerNameAndGroupInfoBeans =
                        triggerDao.findTriggerNameAndGroupByJobId(jobId);
                FindTriggerNameAndGroupByJobIdsResponse response = new FindTriggerNameAndGroupByJobIdsResponse();

                response.setJobId(jobId);
                response.setTriggerList(triggerNameAndGroupInfoBeans);
                result.add(response);
            }
            return Result.ok(result);

        }

    }

    private JobTrigger triggerUpdateArgsToJobTrigger(TriggerUpdateArgs args) {
        JobTrigger jobTrigger = new JobTrigger();
        jobTrigger.setTaskId(args.getTaskId());
        jobTrigger.setTriggerId(args.getTriggerId());
        jobTrigger.setTriggerName(args.getTriggerName());
        jobTrigger.setTriggerGroupName(args.getTriggerGroupName());
        jobTrigger.setCron(args.getCron());
        jobTrigger.setTriggerType(args.getTriggerType());
        jobTrigger.setCreateTime(args.getCreateTime());
        jobTrigger.setCreateBy(args.getCreateBy());
        jobTrigger.setUpdateTime(args.getUpdateTime());
        jobTrigger.setChangedBy(args.getChangedBy());
        jobTrigger.setStartTime(args.getStartTime());
        jobTrigger.setEndTime(args.getEndTime());
        jobTrigger.setTimezone(args.getTimezone());
        jobTrigger.setDescription(args.getDescription());
        return jobTrigger;

    }

    private JobTrigger triggerToInfoBeanToJobTrigger(JobTriggerInfoBean triggerInfoBean) throws ServiceException {
        JobTrigger jobTrigger = new JobTrigger();
        jobTrigger.setTriggerName(triggerInfoBean.getName());
        jobTrigger.setTriggerGroupName(triggerInfoBean.getGroup());
        jobTrigger.setCreateTime(new Date());
        jobTrigger.setRemark(triggerInfoBean.getDescription());
        jobTrigger.setStartTime(triggerInfoBean.getStartTime());
        jobTrigger.setEndTime(triggerInfoBean.getEndTime());
        jobTrigger.setTimezone(String.valueOf(triggerInfoBean.getTimeZone()));
        jobTrigger.setDescription(triggerInfoBean.getDescription());
        return jobTrigger;
    }


    private JobTriggerInfoBean triggerToInfoBean(JobTrigger jobTrigger) {
        JobTriggerInfoBean jobTriggerInfoBean = new JobTriggerInfoBean();
        jobTriggerInfoBean.setName(jobTrigger.getTriggerName());
        jobTriggerInfoBean.setGroup(jobTrigger.getTriggerGroupName());
        jobTriggerInfoBean.setStartTime(jobTrigger.getStartTime());
        jobTriggerInfoBean.setEndTime(jobTrigger.getEndTime());
        jobTriggerInfoBean.setTimeZone(TimeZone.getTimeZone(jobTrigger.getTimezone()));
        jobTriggerInfoBean.setDescription(jobTrigger.getDescription());
        return jobTriggerInfoBean;
    }
}