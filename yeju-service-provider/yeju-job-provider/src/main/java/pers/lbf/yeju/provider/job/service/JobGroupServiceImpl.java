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
import pers.lbf.yeju.common.domain.entity.TaskSchedulerGroup;
import pers.lbf.yeju.provider.job.dao.ITaskSchedulerGroupDao;
import pers.lbf.yeju.service.interfaces.job.IJobGroupService;
import pers.lbf.yeju.service.interfaces.job.pojo.JobGroupInfoBean;

import java.util.LinkedList;
import java.util.List;

/**
 * 定时任务组服务实现类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/27 16:47
 */
@DubboService(interfaceClass = IJobGroupService.class)
public class JobGroupServiceImpl implements IJobGroupService {

    @Autowired
    private ITaskSchedulerGroupDao jobGroupDao;

    /**
     * 根据任务组id查询任务组信息
     *
     * @param groupId 任务组id
     * @return pers.lbf.yeju.common.core.result.IResult<pers.lbf.yeju.service.interfaces.job.pojo.JobGroupInfoBean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/27 16:48
     */
    @Override
    public IResult<JobGroupInfoBean> getJobGroupInfoByGroupId(Long groupId) throws ServiceException {
        TaskSchedulerGroup taskSchedulerGroup = jobGroupDao.selectById(groupId);
        JobGroupInfoBean bean = taskSchedulerGroupToGroupInfoBean(taskSchedulerGroup);

        return Result.ok(bean);
    }

    /**
     * 获取任务组名
     *
     * @param groupId 任务组id
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.String>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/27 16:56
     */
    @Override
    public IResult<String> findJobGroupNameByGroupId(Long groupId) throws ServiceException {
        QueryWrapper<TaskSchedulerGroup> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("group_name");
        TaskSchedulerGroup group = jobGroupDao.selectById(groupId);
        return Result.ok(group.getGroupName());
    }

    /**
     * 获取任务组名和id 创建任务时需要调用
     *
     * @return pers.lbf.yeju.common.core.result.IResult<java.util.List < pers.lbf.yeju.service.interfaces.job.pojo.JobGroupInfoBean>>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/3 10:41
     */
    @Override
    public IResult<List<JobGroupInfoBean>> getJobGroupNameAndId() throws ServiceException {

        QueryWrapper<TaskSchedulerGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("task_group_id", "group_name");
        List<TaskSchedulerGroup> taskSchedulerGroupList = jobGroupDao.selectList(queryWrapper);
        List<JobGroupInfoBean> result = new LinkedList<>();
        for (TaskSchedulerGroup taskSchedulerGroup : taskSchedulerGroupList) {
            JobGroupInfoBean jobGroupInfoBean = taskSchedulerGroupToGroupInfoBean(taskSchedulerGroup);
            result.add(jobGroupInfoBean);
        }
        return Result.ok(result);
    }


    private JobGroupInfoBean taskSchedulerGroupToGroupInfoBean(TaskSchedulerGroup group) {
        JobGroupInfoBean jobGroupInfoBean = new JobGroupInfoBean();
        jobGroupInfoBean.setTaskGroupId(group.getTaskGroupId());
        jobGroupInfoBean.setGroupName(group.getGroupName());
        jobGroupInfoBean.setCreateTime(group.getCreateTime());
        jobGroupInfoBean.setCreateBy(group.getCreateBy());
        jobGroupInfoBean.setUpdateTime(group.getUpdateTime());
        jobGroupInfoBean.setChangedBy(group.getChangedBy());
        jobGroupInfoBean.setRemark(group.getRemark());
        return jobGroupInfoBean;
    }
}