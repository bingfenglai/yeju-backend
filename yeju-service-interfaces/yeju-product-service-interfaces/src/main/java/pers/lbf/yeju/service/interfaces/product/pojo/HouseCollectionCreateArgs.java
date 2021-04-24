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

import pers.lbf.yeju.common.core.args.ICreateArgs;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/24 21:02
 */
public class HouseCollectionCreateArgs implements ICreateArgs {

    /**
     * 客户主键
     */
    @NotNull(message = "客户标识不能为空")
    private Long customerId;
    /**
     * 房源标识
     */
    @NotNull(message = "房源标识不能为空")
    private Long houseId;
    /**
     * 收藏状态0取消收藏1收藏
     */
    private Integer status = 1;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private Long createBy;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public void setCreateBy(String account) {
        this.createBy = Long.valueOf(account);
    }

    @Override
    public void setCreateTime(Date date) {
        this.createTime = date;
    }
}
