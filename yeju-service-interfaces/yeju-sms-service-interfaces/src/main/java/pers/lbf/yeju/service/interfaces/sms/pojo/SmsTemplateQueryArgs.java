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

package pers.lbf.yeju.service.interfaces.sms.pojo;

import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.args.IFindPageArgs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/15 22:01
 */
public class SmsTemplateQueryArgs extends BaseFindPageArgs implements IFindPageArgs, Serializable {

    /**
     * 模板类型1有参2无参
     */
    private String type;
    /**
     * 模板状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Date[] between;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date[] getBetween() {
        return between;
    }

    public void setBetween(Date[] between) {
        this.between = between;
    }

    @Override
    public String toString() {
        return "SmsTemplateQueryArgs{" +
                "currentPage=" + currentPage +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", between=" + Arrays.toString(between) +
                '}';
    }
}
