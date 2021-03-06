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
package pers.lbf.yeju.provider.message.group.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.MessageGroup;

import java.util.List;

/**
 * 消息群组信息表(TableMessageGroup)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-03-06 13:38:01
 */
public interface IMessageGroupDao extends BaseMapper<MessageGroup> {

    @Select("SELECT \n" +
            "  COUNT(*) \n" +
            " FROM\n" +
            "  r_t_account_message_group t \n" +
            " WHERE t.`account_id` = #{accountId} \n" +
            "  AND t.`message_group_id` = #{groupId} \n" +
            " LIMIT 1 ")
    Integer receiverExistIn(@Param("groupId") Long groupId, @Param("accountId") Long accountId);

    @Select("SELECT \n" +
            "  t.`message_group_id` \n" +
            "FROM\n" +
            "  r_t_account_message_group t \n" +
            "WHERE t.`account_id` = #{accountId} \n" +
            "AND t.`group_type` = '1'\n" +
            "  AND t.`is_delete` = '0' ")
    List<Long> findSystemMessageGroupByPrincipal(Long accountId);
}