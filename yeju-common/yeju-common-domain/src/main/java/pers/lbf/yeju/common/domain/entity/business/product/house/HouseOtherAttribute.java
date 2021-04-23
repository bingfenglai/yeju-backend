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
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源其他属性表.包括一些可选的属性以及客户自定义属性（<=3）
 * 。(TableBusinessHouseOtherAttribute)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-03-17 15:40:02
 */
@TableName("table_business_house_other_attribute")
public class HouseOtherAttribute extends Model<HouseOtherAttribute> {
    /**
     * 房源标识
     */
    @TableId
    private Long houseId;
    /**
     * 是否有热水
     * 0否1有
     */
    private Integer isHotWater;
    /**
     * 是否有厨房
     */
    private Integer isHaveKitchen;
    /**
     * 是否有客厅
     */
    private Integer isHaveLivingRoot;
    /**
     * 是否有阳台
     */
    private Integer isHaveBalcony;
    /**
     * 是否有窗户
     */
    private Integer isHaveWindow;
    /**
     * 是否是独卫
     */
    private Integer isSeparateToilet;
    /**
     * 是否支持拎包入住
     */
    private Integer isCheckInWithBags;
    /**
     * 是否有电梯
     * 3楼以上的房源需要选择
     * -1房间在二楼及以下
     * 0没有
     * 1有
     */
    private Integer isHaveElevator;
    /**
     * 是否有车位
     */
    private Integer isThereAParkingSpace;
    /**
     * 电价
     */
    private Double electricityPrice;
    /**
     * 水价
     */
    private Double waterPrice;
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
    private Integer versionNumber;
    /**
     * 删除标识
     */
    private Integer isDelete;
    /**
     * 性别限制
     * 0不限
     * 1男
     * 2女
     */
    private Integer isGenderRestrictions;
    /**
     * 是否有空调
     */
    private Integer isItAirConditioned;
    /**
     * 是否供暖
     */
    private Integer whetherTheHeating;


    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Integer getIsHotWater() {
        return isHotWater;
    }

    public void setIsHotWater(Integer isHotWater) {
        this.isHotWater = isHotWater;
    }

    public Integer getIsHaveKitchen() {
        return isHaveKitchen;
    }

    public void setIsHaveKitchen(Integer isHaveKitchen) {
        this.isHaveKitchen = isHaveKitchen;
    }

    public Integer getIsHaveLivingRoot() {
        return isHaveLivingRoot;
    }

    public void setIsHaveLivingRoot(Integer isHaveLivingRoot) {
        this.isHaveLivingRoot = isHaveLivingRoot;
    }

    public Integer getIsHaveBalcony() {
        return isHaveBalcony;
    }

    public void setIsHaveBalcony(Integer isHaveBalcony) {
        this.isHaveBalcony = isHaveBalcony;
    }

    public Integer getIsHaveWindow() {
        return isHaveWindow;
    }

    public void setIsHaveWindow(Integer isHaveWindow) {
        this.isHaveWindow = isHaveWindow;
    }

    public Integer getIsSeparateToilet() {
        return isSeparateToilet;
    }

    public void setIsSeparateToilet(Integer isSeparateToilet) {
        this.isSeparateToilet = isSeparateToilet;
    }

    public Integer getIsCheckInWithBags() {
        return isCheckInWithBags;
    }

    public void setIsCheckInWithBags(Integer isCheckInWithBags) {
        this.isCheckInWithBags = isCheckInWithBags;
    }

    public Integer getIsHaveElevator() {
        return isHaveElevator;
    }

    public void setIsHaveElevator(Integer isHaveElevator) {
        this.isHaveElevator = isHaveElevator;
    }

    public Integer getIsThereAParkingSpace() {
        return isThereAParkingSpace;
    }

    public void setIsThereAParkingSpace(Integer isThereAParkingSpace) {
        this.isThereAParkingSpace = isThereAParkingSpace;
    }

    public Double getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(Double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public Double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(Double waterPrice) {
        this.waterPrice = waterPrice;
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

    public Integer getIsGenderRestrictions() {
        return isGenderRestrictions;
    }

    public void setIsGenderRestrictions(Integer isGenderRestrictions) {
        this.isGenderRestrictions = isGenderRestrictions;
    }

    public Integer getIsItAirConditioned() {
        return isItAirConditioned;
    }

    public void setIsItAirConditioned(Integer isItAirConditioned) {
        this.isItAirConditioned = isItAirConditioned;
    }

    public Integer getWhetherTheHeating() {
        return whetherTheHeating;
    }

    public void setWhetherTheHeating(Integer whetherTheHeating) {
        this.whetherTheHeating = whetherTheHeating;
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