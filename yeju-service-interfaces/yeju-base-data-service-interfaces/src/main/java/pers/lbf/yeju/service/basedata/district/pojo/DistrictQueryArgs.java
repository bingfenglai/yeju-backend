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

package pers.lbf.yeju.service.basedata.district.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.args.IFindPageArgs;

import java.io.Serializable;

/**
 * 地域条件查询参数封装类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/17 19:58
 */
public class DistrictQueryArgs extends BaseFindPageArgs implements IFindPageArgs, Serializable {

    /**
     * 所属上一级城市id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    private Long parentId;
    /**
     * 地域名称
     */
    private String name;
    /**
     * 城市类型0国1省2市3区
     */
    private Integer type;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DistrictQueryArgs{" +
                "currentPage=" + currentPage +
                ", size=" + size +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
