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
package pers.lbf.yeju.common.domain.entity.log;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 记录房源审核日志信息(TableBusinessHouseCheckLog)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-03-21 15:43:29
 */
@TableName("table_business_house_check_log")
public class HouseCheckLog extends Model<HouseCheckLog> {
    /**
     * 记录标识
     */
    @TableId
    private Long id;
    /**
     * 房源标识
     */
    private Long houseId;
    /**
     * 操作人账户标识
     */
    private Long accountId;
    /**
     * 房源审核前状态
     */
    private String houseOldStatus;
    /**
     * 房源审核后的状态
     */
    private String houseNewStatus;
    /**
     * 审核时间
     */
    private Date checkTime;
    /**
     * 审核意见
     */
    private String opinions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getHouseOldStatus() {
        return houseOldStatus;
    }

    public void setHouseOldStatus(String houseOldStatus) {
        this.houseOldStatus = houseOldStatus;
    }

    public String getHouseNewStatus() {
        return houseNewStatus;
    }

    public void setHouseNewStatus(String houseNewStatus) {
        this.houseNewStatus = houseNewStatus;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getOpinions() {
        return opinions;
    }

    public void setOpinions(String opinions) {
        this.opinions = opinions;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}