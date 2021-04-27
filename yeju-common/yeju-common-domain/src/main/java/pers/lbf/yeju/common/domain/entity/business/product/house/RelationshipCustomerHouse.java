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
package pers.lbf.yeju.common.domain.entity.business.product.house;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户-房源关系表。主要有租赁关系、归属关系，收藏关系，推荐关系，踩 关系，待交租金关系，待需交租金关系、租赁过 关系.关(TableRelationshipCustomerHouse)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-23 08:10:55
 */
@TableName("table_relationship_customer_house")
public class RelationshipCustomerHouse extends Model<RelationshipCustomerHouse> {

    @TableId
    private Long id;

    /**
     * 客户主键
     */
    private Long customerId;
    /**
     * 房源主键
     */
    private Long houseId;
    /**
     * 关系类型,主要有
     * 0租赁关系、
     * 1归属关系，
     * 2收藏关系，
     * 3推荐关系，
     * 4踩 关系，
     * 5待交租金关系（预租，抢占资源），
     * 6待续交租金关系、
     * 7租赁过 关系
     */
    private String relationshipType;
    /**
     * 关系开始时间
     */
    private Date startTime;
    /**
     * 关系结束时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更改者
     */
    private Long changedBy;
    /**
     * 备注
     */
    private String remark;
    /**
     * 字段版本
     */
    @Version
    private Integer versionNumber;
    /**
     * 删除标识
     */
    @TableLogic
    private Integer isDelete;
    /**
     * 建立关系月份，用于表分区
     */
    private Integer createMonth;
    /**
     * 0已结束
     * 1正常
     * 2异常
     */
    private Integer relationshipStatus;
    /**
     * 是否开启关系到期预警0不开启1开启 仅当关系类型为租赁使用关系时有效
     */
    private Integer isWarning;


    /**
     * 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Long changedBy) {
        this.changedBy = changedBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCreateMonth() {
        return createMonth;
    }

    public void setCreateMonth(Integer createMonth) {
        this.createMonth = createMonth;
    }

    public Integer getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(Integer relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public Integer getIsWarning() {
        return isWarning;
    }

    public void setIsWarning(Integer isWarning) {
        this.isWarning = isWarning;
    }


}