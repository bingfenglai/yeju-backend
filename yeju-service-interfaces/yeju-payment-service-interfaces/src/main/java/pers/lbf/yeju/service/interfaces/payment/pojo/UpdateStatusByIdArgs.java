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

package pers.lbf.yeju.service.interfaces.payment.pojo;

import pers.lbf.yeju.common.core.args.IUpdateArgs;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/29 10:37
 */
public class UpdateStatusByIdArgs implements IUpdateArgs, Serializable {


    private Long id;
    private Long paymentId;
    private String newStatus;
    private Date updateTime;
    private Long updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setChangedBy(String account) {
        this.updateBy = Long.valueOf(account);
    }

    @Override
    public void setUpdateTime(Date date) {
        this.updateTime = date;
    }
}
