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
package pers.lbf.yeju.provider.auth.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.Role;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/5 15:33
 */
public interface IRoleDao extends BaseMapper<Role> {


    @Select("select t.role_code " +
            "from r_t_account_role t " +
            "where t.account_number = #{principal}")
    List<String> selectRoleNamesBySystemAccount(String principal);

    @Select("select t.role_code " +
            "from r_t_account_role t " +
            "where t.phone_number = #{principal}")
    List<String> selectRoleNamesByPhoneNumber(String principal);

    @Select("SELECT \n" +
            "  COUNT(*) \n" +
            "FROM\n" +
            "  table_system_role t \n" +
            "WHERE t.`role_id` = #{roleId} \n" +
            "LIMIT 1 ;\n")
    Integer isExist(Long roleId) throws RuntimeException;
}
