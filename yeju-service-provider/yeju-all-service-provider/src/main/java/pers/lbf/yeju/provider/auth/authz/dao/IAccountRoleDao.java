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
package pers.lbf.yeju.provider.auth.authz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.AccountRole;
import pers.lbf.yeju.provider.auth.authz.pojo.AccountSimpleInfoBean;

import java.util.List;

/**
 * 账户-角色关系表 N-1(RTAccountRole)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-03-14 11:53:22
 */
public interface IAccountRoleDao extends BaseMapper<AccountRole> {

    /**
     * 根据角色id查询账户信息
     *
     * @param roleId 角色id
     * @return account id list
     */
    @Select("SELECT \n" +
            "  t.`account_id` AS id,\n" +
            "  t.`account_number`,\n" +
            "  t.`phone_number` \n" +
            "FROM\n" +
            "  r_t_account_role t \n" +
            "WHERE t.`role_id` = 1 ")
    List<AccountSimpleInfoBean> findAccountIdListByRoleId(Long roleId);
}