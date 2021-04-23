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
import org.apache.ibatis.annotations.Update;
import pers.lbf.yeju.common.domain.entity.business.product.house.HouseInfo;

/**
 * 房源信息表，其中按照添加时的月份进行表分区。并且仅保留待审核状态的记录。待交易（审核已完成）的记录搬table_busi(TableBusinessHouseInfo)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-03-17 15:39:12
 */
public interface HouseInfoDao extends BaseMapper<HouseInfo> {

    @Update("UPDATE \n" +
            "  table_business_house_info t \n" +
            "SET\n" +
            "  t.`house_status` = #{houseStatus}' \n" +
            "WHERE t.`house_id` = #{id} ;")
    int updateHouseStatusById(Long id, String houseStatus) throws RuntimeException;
}