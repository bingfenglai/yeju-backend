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
package pers.lbf.yeju.common.domain.entity.tarde;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房屋交易记录表，按照交易月份进行分区(TableBusinessTradingInformationHouse)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-22 10:37:28
 */
@TableName("table_business_trading_information_house")
public class TradingInformationHouse extends Model<TradingInformationHouse> {
    /**
     * 主键
     */
    @TableId
    private Long tradeId;
    /**
     * 房源id
     */
    private Long houseId;
    /**
     * 房东标识
     */
    private Long landlordId;
    /**
     * (准)房客标识
     */
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
     * 按租金单位计算的租用期限
     */
    private Integer tenancy;


    /**
     * 交易创建时间
     */
    private Date createTime;
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
     * 交易取消时间
     */
    private Date cancelTime;
    /**
     * 交易类型，详见数据字典
     * 0租房
     * 1退房
     * 3续交押金
     * 4退押金
     */
    private String tradingType;

    public Integer getTenancy() {
        return tenancy;
    }

    public void setTenancy(Integer tenancy) {
        this.tenancy = tenancy;
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

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.tradeId;
    }
}