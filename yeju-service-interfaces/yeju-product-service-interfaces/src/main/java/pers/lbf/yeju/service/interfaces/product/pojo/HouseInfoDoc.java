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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源搜素结果封装类、参数封装类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/27 13:13
 */
@Document(indexName = "index_house_info", type = "_doc")
public class HouseInfoDoc implements Serializable {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long houseId;
    /**
     * 房源标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    /**
     * 租金
     */

    @Field(type = FieldType.Double)
    private Double rent;

    /**
     * 户型 详见参数表
     */
    @Field(type = FieldType.Keyword)
    private String houseType;
    /**
     * 建筑面积,单位平方米
     */
    @Field(type = FieldType.Keyword)
    private Integer coveredArea;

    @Field(type = FieldType.Keyword)
    private String houseOrientation;
    /**
     * 房屋装修类型，1简装，2精装，3毛胚
     */
    @Field(type = FieldType.Keyword)
    private String houseDecorationType;

    /**
     * 房源图片 略缩图
     */
    private String houseImagesAddress;

    /**
     * 出租方式（0整租，1合租，2可合租可整租）详见参数表
     */
    @Field(type = FieldType.Keyword)
    private String rentalMode;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 详细地址
     */
    private String detailsAddress;

    public HouseInfoDoc() {

    }

    public HouseInfoDoc(Long houseId, String title, Double rent, String houseType, Integer coveredArea, String houseOrientation, String houseDecorationType, String houseImagesAddress, String rentalMode, Date createTime, String detailsAddress) {
        this.houseId = houseId;
        this.title = title;
        this.rent = rent;
        this.houseType = houseType;
        this.coveredArea = coveredArea;
        this.houseOrientation = houseOrientation;
        this.houseDecorationType = houseDecorationType;
        this.houseImagesAddress = houseImagesAddress;
        this.rentalMode = rentalMode;
        this.createTime = createTime;
        this.detailsAddress = detailsAddress;
    }

    @Override
    public String toString() {
        return "HouseInfoDoc{" +
                "houseId=" + houseId +
                ", title='" + title + '\'' +
                ", rent=" + rent +
                ", houseType='" + houseType + '\'' +
                ", coveredArea=" + coveredArea +
                ", houseOrientation='" + houseOrientation + '\'' +
                ", houseDecorationType='" + houseDecorationType + '\'' +
                ", houseImagesAddress='" + houseImagesAddress + '\'' +
                ", rentalMode='" + rentalMode + '\'' +
                ", createTime=" + createTime +
                ", detailsAddress='" + detailsAddress + '\'' +
                '}';
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
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

    public String getHouseImagesAddress() {
        return houseImagesAddress;
    }

    public void setHouseImagesAddress(String houseImagesAddress) {
        this.houseImagesAddress = houseImagesAddress;
    }


    public String getRentalMode() {
        return rentalMode;
    }

    public void setRentalMode(String rentalMode) {
        this.rentalMode = rentalMode;
    }

  
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }
}
