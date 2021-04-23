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
package pers.lbf.yeju.common.domain.entity.business;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约看房信息表，完成看房搬历史，按照创建月份本区()表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-24 00:19:49
 */
@TableName("table_business_reservation")
public class Reservation extends Model<Reservation> {
    /**
     * 主键
     */
    private Long reservationId;
    /**
     * 意向客户id
     */
    private Long prospectiveCustomerId;
    /**
     * 房东id
     */
    private Long landlordId;
    /**
     * 房源id
     */
    private Long houseId;
    /**
     * 0关闭
     * 1等待房东确认
     * 2等待意向客户确认
     * 3完成看房
     * 4意向客户爽约
     * 5房东爽约
     * 6取消
     */
    private Integer status;
    /**
     * 预约看房时间
     */
    private Date reservationTime;
    /**
     * 创建时间
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
     * 异常结束原因
     */
    private String causeOfAbnormalEnd;


    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getProspectiveCustomerId() {
        return prospectiveCustomerId;
    }

    public void setProspectiveCustomerId(Long prospectiveCustomerId) {
        this.prospectiveCustomerId = prospectiveCustomerId;
    }

    public Long getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Long landlordId) {
        this.landlordId = landlordId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
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

    public String getCauseOfAbnormalEnd() {
        return causeOfAbnormalEnd;
    }

    public void setCauseOfAbnormalEnd(String causeOfAbnormalEnd) {
        this.causeOfAbnormalEnd = causeOfAbnormalEnd;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.reservationId;
    }
}