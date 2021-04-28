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

package pers.lbf.yeju.service.interfaces.product.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/17 15:36
 */
public class HouseDetailsInfoBean implements Serializable {

    private Long houseId;
    /**
     * 业主
     */
    private Long ownerId;

    /**
     * 业主姓名
     */
    private String ownerName;

    /**
     * 房源标题
     */
    private String title;
    /**
     * 所属小区
     */
    private Long communityId;

    /**
     * 小区姓名
     */
    private Long communityName;

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
     * 详细地址
     */
    private String detailsAddress;

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
     * 2待租
     * 3预租
     * 4在租
     * 详见参数表
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
     * 是否有热水
     * 0否1有
     */
    private Boolean hotWater;
    /**
     * 是否有厨房
     */
    private Boolean haveKitchen;
    /**
     * 是否有客厅
     */
    private Boolean haveLivingRoot;
    /**
     * 是否有阳台
     */
    private Boolean haveBalcony;
    /**
     * 是否有窗户
     */
    private Boolean haveWindow;
    /**
     * 是否是独卫
     */
    private Boolean separateToilet;
    /**
     * 是否支持拎包入住
     */
    private Boolean checkInWithBags;
    /**
     * 是否有电梯
     * 3楼以上的房源需要选择
     * -1房间在二楼及以下
     * 0没有
     * 1有
     */
    private Boolean haveElevator;
    /**
     * 是否有车位
     */
    private Boolean parkingSpace;
    /**
     * 电价
     */
    private Double electricityPrice;
    /**
     * 水价
     */
    private Double waterPrice;

    /**
     * 性别限制
     * 0不限
     * 1男
     * 2女
     */
    private Boolean genderRestrictions;
    /**
     * 是否有空调
     */
    private Boolean itAirConditioned;
    /**
     * 是否供暖
     */
    private Boolean whetherTheHeating;

    private List<HouseResourceInfoBean> houseResourceInfoBeanList;


    public void addResource(HouseResourceInfoBean resource) {
        if (houseResourceInfoBeanList == null) {
            houseResourceInfoBeanList = new LinkedList<>();
        }
        houseResourceInfoBeanList.add(resource);
    }


    @Override
    public String toString() {
        return "HouseDetailsInfoBean{" +
                "houseId=" + houseId +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", title='" + title + '\'' +
                ", communityId=" + communityId +
                ", communityName=" + communityName +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", buildingUint='" + buildingUint + '\'' +
                ", buildingFloorNumber='" + buildingFloorNumber + '\'' +
                ", detailsAddress='" + detailsAddress + '\'' +
                ", rent=" + rent +
                ", rentalMode='" + rentalMode + '\'' +
                ", houseType='" + houseType + '\'' +
                ", coveredArea=" + coveredArea +
                ", useArea=" + useArea +
                ", floors='" + floors + '\'' +
                ", houseOrientation='" + houseOrientation + '\'' +
                ", houseDecorationType='" + houseDecorationType + '\'' +
                ", houseFacilities='" + houseFacilities + '\'' +
                ", descs='" + descs + '\'' +
                ", houseStatus='" + houseStatus + '\'' +
                ", houseImagesAddress='" + houseImagesAddress + '\'' +
                ", createTime=" + createTime +
                ", hotWater=" + hotWater +
                ", haveKitchen=" + haveKitchen +
                ", haveLivingRoot=" + haveLivingRoot +
                ", haveBalcony=" + haveBalcony +
                ", haveWindow=" + haveWindow +
                ", separateToilet=" + separateToilet +
                ", checkInWithBags=" + checkInWithBags +
                ", haveElevator=" + haveElevator +
                ", parkingSpace=" + parkingSpace +
                ", electricityPrice=" + electricityPrice +
                ", waterPrice=" + waterPrice +
                ", genderRestrictions=" + genderRestrictions +
                ", itAirConditioned=" + itAirConditioned +
                ", whetherTheHeating=" + whetherTheHeating +
                ", houseResourceInfoBeanList=" + houseResourceInfoBeanList +
                '}';
    }

    public Boolean getGenderRestrictions() {
        return genderRestrictions;
    }

    public void setGenderRestrictions(Boolean genderRestrictions) {
        this.genderRestrictions = genderRestrictions;
    }

    public Boolean getItAirConditioned() {
        return itAirConditioned;
    }

    public void setItAirConditioned(Boolean itAirConditioned) {
        this.itAirConditioned = itAirConditioned;
    }

    public Boolean getWhetherTheHeating() {
        return whetherTheHeating;
    }

    public void setWhetherTheHeating(Boolean whetherTheHeating) {
        this.whetherTheHeating = whetherTheHeating;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Long getCommunityName() {
        return communityName;
    }

    public void setCommunityName(Long communityName) {
        this.communityName = communityName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingUint() {
        return buildingUint;
    }

    public void setBuildingUint(String buildingUint) {
        this.buildingUint = buildingUint;
    }

    public String getBuildingFloorNumber() {
        return buildingFloorNumber;
    }

    public void setBuildingFloorNumber(String buildingFloorNumber) {
        this.buildingFloorNumber = buildingFloorNumber;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getRentalMode() {
        return rentalMode;
    }

    public void setRentalMode(String rentalMode) {
        this.rentalMode = rentalMode;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Integer getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(Integer coveredArea) {
        this.coveredArea = coveredArea;
    }

    public Integer getUseArea() {
        return useArea;
    }

    public void setUseArea(Integer useArea) {
        this.useArea = useArea;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getHouseOrientation() {
        return houseOrientation;
    }

    public void setHouseOrientation(String houseOrientation) {
        this.houseOrientation = houseOrientation;
    }

    public String getHouseDecorationType() {
        return houseDecorationType;
    }

    public void setHouseDecorationType(String houseDecorationType) {
        this.houseDecorationType = houseDecorationType;
    }

    public String getHouseFacilities() {
        return houseFacilities;
    }

    public void setHouseFacilities(String houseFacilities) {
        this.houseFacilities = houseFacilities;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getHouseImagesAddress() {
        return houseImagesAddress;
    }

    public void setHouseImagesAddress(String houseImagesAddress) {
        this.houseImagesAddress = houseImagesAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getHotWater() {
        return hotWater;
    }

    public void setHotWater(Boolean hotWater) {
        this.hotWater = hotWater;
    }

    public Boolean getHaveKitchen() {
        return haveKitchen;
    }

    public void setHaveKitchen(Boolean haveKitchen) {
        this.haveKitchen = haveKitchen;
    }

    public Boolean getHaveLivingRoot() {
        return haveLivingRoot;
    }

    public void setHaveLivingRoot(Boolean haveLivingRoot) {
        this.haveLivingRoot = haveLivingRoot;
    }

    public Boolean getHaveBalcony() {
        return haveBalcony;
    }

    public void setHaveBalcony(Boolean haveBalcony) {
        this.haveBalcony = haveBalcony;
    }

    public Boolean getHaveWindow() {
        return haveWindow;
    }

    public void setHaveWindow(Boolean haveWindow) {
        this.haveWindow = haveWindow;
    }

    public Boolean getSeparateToilet() {
        return separateToilet;
    }

    public void setSeparateToilet(Boolean separateToilet) {
        this.separateToilet = separateToilet;
    }

    public Boolean getCheckInWithBags() {
        return checkInWithBags;
    }

    public void setCheckInWithBags(Boolean checkInWithBags) {
        this.checkInWithBags = checkInWithBags;
    }

    public Boolean getHaveElevator() {
        return haveElevator;
    }

    public void setHaveElevator(Boolean haveElevator) {
        this.haveElevator = haveElevator;
    }

    public Boolean getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(Boolean parkingSpace) {
        this.parkingSpace = parkingSpace;
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

    public List<HouseResourceInfoBean> getHouseResourceInfoBeanList() {
        return houseResourceInfoBeanList;
    }

    public void setHouseResourceInfoBeanList(List<HouseResourceInfoBean> houseResourceInfoBeanList) {
        this.houseResourceInfoBeanList = houseResourceInfoBeanList;
    }
}
