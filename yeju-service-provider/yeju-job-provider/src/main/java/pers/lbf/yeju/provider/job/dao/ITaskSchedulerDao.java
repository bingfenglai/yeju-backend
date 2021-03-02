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
package pers.lbf.yeju.provider.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.lbf.yeju.common.domain.entity.TaskScheduler;
import pers.lbf.yeju.service.interfaces.job.pojo.JobInfoBean;

import java.util.List;

/**
 * 定时任务调度表(TableSystemTimingTaskScheduler)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-22 15:45:16
 */
public interface ITaskSchedulerDao extends BaseMapper<TaskScheduler> {

    List<JobInfoBean> selectJobAll();

    @Update("UPDATE table_system_timing_task_scheduler t\n " +
            "SET t.`status` = #{newStatus} \n " +
            "WHERE t.task_id = # {taskId}; ")
    boolean updateStatusById(@Param("taskId") Long taskId, @Param("newStatus") Integer newStatus);

    /**
     * 根据jobId查询job名字和组名字
     *
     * @param jobIds ids
     * @return
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/2 10:43
     */
    @Select("<script>" +
            "SELECT \n" +
            "  t1.task_id as job_id,\n" +
            "  t1.`task_name` as job_name,\n" +
            "  t2.`group_name` as job_group\n" +
            " FROM\n " +
            "  table_system_timing_task_scheduler t1 \n" +
            "  LEFT JOIN table_system_timing_task_scheduler_group t2 \n" +
            "    ON t1.`task_group_id` = t2.`task_group_id` \n" +
            "    AND t1.`task_id` IN " +
            " <foreach item='item' index='index' collection='jobIds' open='(' separator=',' close=')'> " +
            " #{item}" +
            " </foreach> " +
            "</script>")
    List<JobInfoBean> findNameAndGroupNameByIds(Long[] jobIds);
}