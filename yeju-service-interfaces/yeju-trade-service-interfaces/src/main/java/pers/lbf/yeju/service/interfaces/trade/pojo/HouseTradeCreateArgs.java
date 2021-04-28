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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import pers.lbf.yeju.common.core.args.ICreateArgs;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 11:06
 */
public class HouseTradeCreateArgs implements ICreateArgs {

    /**
     * 房源id
     */
    @JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    @NotNull(message = "房源标识不能为空")
    private Long houseId;

    /**
     * (准)房客标识
     */
    @JsonDeserialize(using = NumberDeserializers.LongDeserializer.class)
    @NotNull(message = "(准)房客标识不能为空")
    private Long tenantId;
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
     * 本单交易最终支付金额
     */
    private Double free;

    /**
     * 房屋租赁的单位时间价格
     */
    private Double houseRentUnitFree;

    /**
     * 租金单位
     */
    private String rentUnit;

    /**
     * 按租金单位计算的租用期限
     */
    private Integer tenancy;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTenancy() {
        return tenancy;
    }

    public void setTenancy(Integer tenancy) {
        this.tenancy = tenancy;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
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

    public String getRentUnit() {
        return rentUnit;
    }

    public void setRentUnit(String rentUnit) {
        this.rentUnit = rentUnit;
    }

    @Override
    public void setCreateBy(String account) {

    }

    @Override
    public void setCreateTime(Date date) {

    }
}
