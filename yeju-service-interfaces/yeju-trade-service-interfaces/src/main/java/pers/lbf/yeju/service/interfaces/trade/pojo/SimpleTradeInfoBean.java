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

package pers.lbf.yeju.service.interfaces.trade.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 11:05
 */
public class SimpleTradeInfoBean implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tradeId;
    /**
     * 房源id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long houseId;
    /**
     * 房东标识
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long landlordId;
    /**
     * (准)房客标识
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantId;
    /**
     * 本单交易最终支付金额
     */
    private Double free;

    /**
     * 房屋租赁的单位时间价格
     */
    private Double houseRentUnitFree;

    /**
     * 交易状态
     * 0交易创建成功
     * 1已支付
     * 2已收货（房东已交房、卡卷已到账）
     * 3商家已收款（房租已转入房东账户）
     * 4交易异常申诉中(此时创建申述工单，并进入状态10)
     * 5交易正常结束（货款两清）
     * 9交易非正常结束
     * 10交易暂停
     * 11交易取消
     * <p>
     * 详细见数据字典
     */
    private String tradingStatus;
    /**
     * 交易创建时间
     */
    private Date createTime;

    public Double getFree() {
        return free;
    }

    public void setFree(Double free) {
        this.free = free;
    }

    public Double getHouseRentUnitFree() {
        return houseRentUnitFree;
    }

    public void setHouseRentUnitFree(Double houseRentUnitFree) {
        this.houseRentUnitFree = houseRentUnitFree;
    }
    
    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Long landlordId) {
        this.landlordId = landlordId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTradingStatus() {
        return tradingStatus;
    }

    public void setTradingStatus(String tradingStatus) {
        this.tradingStatus = tradingStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
