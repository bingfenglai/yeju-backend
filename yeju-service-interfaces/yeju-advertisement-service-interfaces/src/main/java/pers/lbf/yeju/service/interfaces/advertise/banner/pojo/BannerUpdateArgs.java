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

package pers.lbf.yeju.service.interfaces.advertise.banner.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import pers.lbf.yeju.common.core.args.UpdateArgs;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/15 20:42
 */
public class BannerUpdateArgs extends BannerCreateArgs implements Serializable, UpdateArgs {

    @NotNull(message = "轮播图标识不能为空")
    @JsonSerialize(as = StringSerializer.class)
    @JsonDeserialize(as = NumberDeserializers.LongDeserializer.class)
    private Long id;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更改者
     */
    private Long changedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Long getChangedBy() {
        return changedBy;
    }

    @Override
    public void setChangedBy(String account) {
        this.changedBy = Long.valueOf(account);
    }

    @Override
    public void setUpdateTime(Date date) {
        this.updateTime = date;
    }
}
