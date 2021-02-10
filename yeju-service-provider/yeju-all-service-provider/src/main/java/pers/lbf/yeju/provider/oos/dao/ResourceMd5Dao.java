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
package pers.lbf.yeju.provider.oos.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pers.lbf.yeju.common.domain.entity.ResourceMd5;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/1 16:34
 */
public interface ResourceMd5Dao extends BaseMapper<ResourceMd5> {

    /**
     * 查询md5是否存在
     * @param resourceMd5 md5值
     * @return 1 存在 0 不存在
     */
    @Select("select t.resource from table_system_resource_md5 t " +
            " where t.md5=#{resourceMd5}" +
            " and t.resource_type = 1" +
            " limit 1")
    String selectResourceUrlByMd5(String resourceMd5);

}
