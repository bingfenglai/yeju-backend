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
package pers.lbf.yeju.provider.basedata.district.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.data.District;
import pers.lbf.yeju.service.basedata.pojo.SimpleDistrictInfoBean;

import java.util.List;

/**
 * 中国省市表(TableBusinessDistrict)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-17 19:48:04
 */
public interface IDistrictDao extends BaseMapper<District> {

    @Select("select \n" +
            "  t.name \n" +
            "from\n" +
            "  table_data_district t \n" +
            "where t.`district_id` = #{id} ; ")
    String findCityNameByCityId(Long id) throws RuntimeException;


    @Select("SELECT \n" +
            "  t.`district_id`,\n" +
            "  t.`name`,\n" +
            "  t.`type`,\n" +
            "  t.`hierarchy` \n" +
            "FROM\n" +
            "  table_data_district t \n" +
            "WHERE t.`parent_id` = #{parentId} \n" +
            "  AND t.`is_delete` = 0 ")
    List<SimpleDistrictInfoBean> findDirectDistrictById(Long parentId);
}