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
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.lbf.yeju.common.domain.entity.JobTrigger;
import pers.lbf.yeju.service.interfaces.job.pojo.TriggerNameAndGroupInfoBean;

import java.util.List;

/**
 * (TableSystemJobTrigger)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-27 16:14:53
 */
public interface IJobTriggerDao extends BaseMapper<JobTrigger> {

    List<JobTrigger> findTriggerNameAndGroupByJobIds(Long[] jobIds);

    @Select(" SELECT \n" +
            "  t.`trigger_name` AS name,\n" +
            "  t.`trigger_group_name` AS group_name \n" +
            " FROM\n" +
            "  table_system_job_trigger t \n" +
            " WHERE t.`task_id` = #{jobId} ;\n ")
    List<TriggerNameAndGroupInfoBean> findTriggerNameAndGroupByJobId(Long jobId);

    @Update("<script>" +
            " UPDATE \n" +
            "  table_system_job_trigger t \n" +
            " SET\n" +
            "  t.`is_delete` = 1 \n" +
            " WHERE t.`task_id` IN" +
            " <foreach item='item' index='index' collection='jobIds' open='(' separator=',' close=')'> " +
            " #{item}" +
            " </foreach> " +
            "</script>")
    void deleteByJobIds(Long[] jobIds);
}