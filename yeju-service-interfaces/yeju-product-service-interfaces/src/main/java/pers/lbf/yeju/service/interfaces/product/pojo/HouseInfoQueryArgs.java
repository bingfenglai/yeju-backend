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

import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.args.IFindPageArgs;

import java.util.Arrays;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/21 19:20
 */
public class HouseInfoQueryArgs extends BaseFindPageArgs implements IFindPageArgs {
    /**
     * 出租方式（0整租，1合租，2可合租可整租）详见参数表
     */
    private String rentalMode;
    /**
     * 支付方式（1押一付一，2押一付二，3押一付三，4押一付六，5押一付年，6其他）详见参数表
     */
    private String paymentMethod;
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
     * 朝向，1坐北朝南，2坐东朝西，3坐南朝北，4坐西朝东  详见参数表
     */
    private String houseOrientation;
    /**
     * 房屋装修类型，1简装，2精装，3毛胚
     */
    private String houseDecorationType;


    /**
     * 创建时间范围
     */
    private String[] dateRange;

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

    public String getRentalMode() {
        return rentalMode;
    }

    public void setRentalMode(String rentalMode) {
        this.rentalMode = rentalMode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String[] getDateRange() {
        return dateRange;
    }

    public void setDateRange(String[] dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    public String toString() {
        return "HouseInfoQueryArgs{" +
                "currentPage=" + currentPage +
                ", size=" + size +
                ", rentalMode='" + rentalMode + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", houseStatus='" + houseStatus + '\'' +
                ", houseOrientation='" + houseOrientation + '\'' +
                ", houseDecorationType='" + houseDecorationType + '\'' +
                ", dateRange=" + Arrays.toString(dateRange) +
                '}';
    }
}
