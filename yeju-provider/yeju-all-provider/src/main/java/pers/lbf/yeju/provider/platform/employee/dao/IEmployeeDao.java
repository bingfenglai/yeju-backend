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
package pers.lbf.yeju.provider.platform.employee.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.Employee;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/7 21:45
 */
public interface IEmployeeDao extends BaseMapper<Employee> {

    @Select("SELECT \n" +
            "  t.name \n" +
            "FROM\n" +
            "  table_platform_employees t \n" +
            "WHERE t.employees_number = #{account} \n" +
            "LIMIT 1 ;")
    String selectEmployeeNameByEmployeeNumber(@Param("account") String account);


    @Select("SELECT \n" +
            "  t.name \n" +
            "FROM\n" +
            "  table_platform_employees t \n" +
            "WHERE t.phone_number = #{account} \n" +
            "LIMIT 1 ;")
    String selectEmployeeNameByPhoneNumber(@Param("account") String account);
}
