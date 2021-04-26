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
package pers.lbf.yeju.provider.product.collection.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import pers.lbf.yeju.common.domain.entity.HouseCollection;

/**
 * 客户收藏信息表,用于记录用户的收藏页信息。收藏的内容可以是图文、视频和房源此表存于mongodb。本表主要用来记录用户收(TableBusinessCollection)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-24 20:55:17
 */
public interface ICollectionDao extends BaseMapper<HouseCollection> {

    @Update("UPDATE \n" +
            "  table_business_collection t \n" +
            "SET\n" +
            "  t.`status` = #{collectionStatus} \n" +
            "WHERE t.`collection_id` = #{collectionId} ")
    Boolean changeCollectionStatusById(
            @Param("collectionId") Long collectionId,
            @Param("collectionStatus") Integer collectionStatus);
}