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

package pers.lbf.yeju.service.basedata.district.pojo;

import pers.lbf.yeju.common.core.args.IUpdateArgs;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/18 21:01
 */
public class DistrictUpdateArgs extends DistrictCreateArgs implements IUpdateArgs {
    @NotNull(message = "地域标识不能为空")
    private Long districtId;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更改者
     */
    private Long changedBy;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Override
    public void setChangedBy(String account) {
        this.changedBy = Long.valueOf(account);
    }

    @Override
    public void setUpdateTime(Date date) {
        this.updateTime = date;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Long getChangedBy() {
        return changedBy;
    }

}
