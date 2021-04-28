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

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/17 15:31
 */
public class SimpleHouseInfoBean implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long houseId;
    /**
     * 房源标题
     */
    private String title;
    /**
     * 租金
     */
    private Double rent;


    /**
     * 出租方式（0整租，1合租，2可合租可整租）详见参数表
     */
    private String rentalMode;

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
     * 创建时间
     */
    private Date createTime;


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

    public String getRentalMode() {
        return rentalMode;
    }

    public void setRentalMode(String rentalMode) {
        this.rentalMode = rentalMode;
    }


    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
