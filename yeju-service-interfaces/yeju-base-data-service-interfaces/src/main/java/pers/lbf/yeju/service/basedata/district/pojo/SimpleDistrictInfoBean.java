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

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 区域条件分页查询结果封装类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/17 19:57
 */
public class SimpleDistrictInfoBean implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    private Long districtId;
    /**
     * 所属上一级城市id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    private Long parentId;
    /**
     * 城市名称
     */
    private String name;
    /**
     * 城市类型0国1省2市3区
     */
    private Integer type;
    /**
     * 地区所处的层级
     */
    private Integer hierarchy;

    private Date createTime;

    private String remark;

    private Boolean hasChildren;

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    private List<SimpleDistrictInfoBean> childrenList;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void addChildren(SimpleDistrictInfoBean children) {
        if (childrenList == null) {
            childrenList = new LinkedList<>();
        }

        childrenList.add(children);
    }

    public List<SimpleDistrictInfoBean> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<SimpleDistrictInfoBean> childrenList) {
        this.childrenList = childrenList;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

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

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }
}
