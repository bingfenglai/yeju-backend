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

import pers.lbf.yeju.common.core.args.ICreateArgs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/18 20:47
 */
public class DistrictCreateArgs implements ICreateArgs, Serializable {

    /**
     * 所属上一级城市id
     */
    @NotNull(message = "上一级地域标识不能为空")
//    @JsonSerialize(using = ToStringSerializer.class)
//    @JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    private Long parentId;
    /**
     * 城市名称
     */
    @NotBlank(message = "地域名字不能为空")
    private String name;
    /**
     * 城市类型0国1省2市3区
     */
    @NotNull(message = "地域类型不能为空")
    private Integer type;
    /**
     * 地区所处的层级
     */
    @NotNull(message = "地域所属层级不能为空")
    private Integer hierarchy;

    private Long createBy;
    private Date createTime;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateBy(String account) {
        this.createBy = Long.valueOf(account);
    }

    @Override
    public void setCreateTime(Date date) {
        this.createTime = date;
    }
}
