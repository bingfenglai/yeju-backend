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

package pers.lbf.yeju.service.basedata.community.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import pers.lbf.yeju.common.core.args.ICreateArgs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/19 21:33
 */
public class CommunityCreateArgs implements ICreateArgs {

    /**
     * 名称
     */
    @NotBlank(message = "小区(社区)名称不能为空")
    private String name;

    /**
     * 所在区（县）
     */
    @JsonSerialize(using = ToStringSerializer.class)
    //@JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    @NotNull(message = "小区(社区)所属县（区）不能为空")
    private Long areaId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cityId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long provinceId;
    /**
     * 详细地址
     */
    @NotBlank(message = "小区(社区)详细地址不能为空")
    private String detailedAddress;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 备注
     */
    private String remark;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
