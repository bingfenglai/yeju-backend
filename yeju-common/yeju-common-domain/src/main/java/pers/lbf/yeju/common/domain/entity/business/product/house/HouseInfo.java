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
 * 房源信息表，其中按照添加时的月份进行表分区。
 * 并且仅保留待审核状态的记录。待交易（审核已完成）
 * 的记录搬table_busi(TableBusinessHouseInfo)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-03-17 15:39:05
 */
@TableName("table_business_house_info")
public class HouseInfo extends Model<HouseInfo> implements Serializable, IHouseInfo {
    /**
     * 主键
     */
    @TableId
    private Long houseId;
    /**
     * 业主
     */
    private Long ownerId;
    /**
     * 房源标题
     */
    private String title;
    /**
     * 所属小区
     */
    private Long communityId;
    /**
     * 楼号（栋）
     */
    private String buildingNumber;
    /**
     * 所属单元
     */
    private String buildingUint;
    /**
     * 门牌号
     */
    private String buildingFloorNumber;
    /**
     * 租金
     */
    private Double rent;

    /**
     * 出租方式（0整租，1合租，2可合租可整租）详见参数表
     */
    private String rentalMode;

    /**
     * 户型 详见参数表
     */
    private String houseType;
    /**
     * 建筑面积,单位平方米
     */
    private Integer coveredArea;
    /**
     * 使用面积,单位平方米
     */
    private Integer useArea;
    /**
     * 楼层,例如8/26
     */
    private String floors;
    /**
     * 朝向，1坐北朝南，2坐东朝西，3坐南朝北，4坐西朝东  详见参数表
     */
    private String houseOrientation;
    /**
     * 房屋装修类型，1简装，2精装，3毛胚
     */
    private String houseDecorationType;
    /**
     * 配套设施，详见参数表
     */
    private String houseFacilities;
    /**
     * 描述
     */
    private String descs;
    /**
     * 0审核中
     * 1审核未通过
     * 2待租
     * 3预交易
     * 4交易生效中
     * 详见数据字典
     */
    private String houseStatus;
    /**
     * 房屋图片地址
     */
    private String houseImagesAddress;
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
     * 房源信息添加时的月份
     */
    private Integer monthAdded;
    /**
     * 房源交易完成时的月份。历史表的分区依据
     */
    private Integer monthCompleted;

    @Override
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    @Override
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    @Override
    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @Override
    public String getBuildingUint() {
        return buildingUint;
    }

    public void setBuildingUint(String buildingUint) {
        this.buildingUint = buildingUint;
    }

    @Override
    public String getBuildingFloorNumber() {
        return buildingFloorNumber;
    }

    public void setBuildingFloorNumber(String buildingFloorNumber) {
        this.buildingFloorNumber = buildingFloorNumber;
    }

    @Override
    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    @Override
    public String getRentalMode() {
        return rentalMode;
    }

    public void setRentalMode(String rentalMode) {
        this.rentalMode = rentalMode;
    }


    @Override
    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    @Override
    public Integer getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(Integer coveredArea) {
        this.coveredArea = coveredArea;
    }

    @Override
    public Integer getUseArea() {
        return useArea;
    }

    public void setUseArea(Integer useArea) {
        this.useArea = useArea;
    }

    @Override
    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    @Override
    public String getHouseOrientation() {
        return houseOrientation;
    }

    public void setHouseOrientation(String houseOrientation) {
        this.houseOrientation = houseOrientation;
    }

    @Override
    public String getHouseDecorationType() {
        return houseDecorationType;
    }

    public void setHouseDecorationType(String houseDecorationType) {
        this.houseDecorationType = houseDecorationType;
    }

    @Override
    public String getHouseFacilities() {
        return houseFacilities;
    }

    public void setHouseFacilities(String houseFacilities) {
        this.houseFacilities = houseFacilities;
    }

    @Override
    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    @Override
    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    @Override
    public String getHouseImagesAddress() {
        return houseImagesAddress;
    }

    public void setHouseImagesAddress(String houseImagesAddress) {
        this.houseImagesAddress = houseImagesAddress;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Long getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Long changedBy) {
        this.changedBy = changedBy;
    }

    @Override
    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Override
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public Integer getMonthAdded() {
        return monthAdded;
    }

    public void setMonthAdded(Integer monthAdded) {
        this.monthAdded = monthAdded;
    }

    @Override
    public Integer getMonthCompleted() {
        return monthCompleted;
    }

    public void setMonthCompleted(Integer monthCompleted) {
        this.monthCompleted = monthCompleted;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.houseId;
    }
}