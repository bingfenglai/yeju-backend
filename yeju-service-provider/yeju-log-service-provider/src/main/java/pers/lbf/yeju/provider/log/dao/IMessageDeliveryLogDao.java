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
package pers.lbf.yeju.provider.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.log.MessageDeliveryLog;

/**
 * 消息投递记录(TableMessageDeliveryLog)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-03-06 13:39:58
 */
public interface IMessageDeliveryLogDao extends BaseMapper<MessageDeliveryLog> {

    @Select("SELECT \n" +
            "  COUNT(*) \n" +
            "FROM\n" +
            "  table_message_delivery_log t \n" +
            "WHERE t.`receiver_id` = #{accountId} \n" +
            "  AND t.`message_id` = #{messageId} \n" +
            "  AND t.`delivery_status` = 1 \n" +
            "LIMIT 1 ")
    Integer isExist(@Param("accountId") Long accountId, @Param("messageId") Long messageId);
}