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

package pers.lbf.yeju.service.interfaces.log.pojo;

import pers.lbf.yeju.common.core.args.ICreateArgs;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/21 17:54
 */
public class HouseCheckLogCreateArgs implements ICreateArgs, Serializable {

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

    @Override
    public void setCreateBy(String account) {
        this.accountId = Long.valueOf(account);
    }

    @Override
    public void setCreateTime(Date date) {
        this.checkTime = date;
    }
}
