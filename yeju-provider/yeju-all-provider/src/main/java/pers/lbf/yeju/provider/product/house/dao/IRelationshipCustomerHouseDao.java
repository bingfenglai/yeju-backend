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
package pers.lbf.yeju.provider.product.house.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.business.product.house.RelationshipCustomerHouse;

/**
 * 客户-房源关系表。主要有租赁关系、归属关系，收藏关系，推荐关系，踩 关系，待交租金关系，待需交租金关系、租赁过 关系.关(TableRelationshipCustomerHouse)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-23 08:11:06
 */
public interface IRelationshipCustomerHouseDao extends BaseMapper<RelationshipCustomerHouse> {

    /**
     * 根据房源主键和关系类型查询客户主键
     *
     * @param houseId
     * @return
     */
    @Select("SELECT \n" +
            "  t.`customer_id` \n" +
            "FROM\n" +
            "  table_relationship_customer_house t \n" +
            "WHERE t.`house_id` = #{houseId} \n" +
            "  AND t.`relationship_type` = #{relationshipType} \n" +
            "LIMIT 1 ;")
    Long getCustomerIdByHouseIdAndRelationshipType(
            @Param("houseId") Long houseId,
            @Param("relationshipType") String relationshipType);
}