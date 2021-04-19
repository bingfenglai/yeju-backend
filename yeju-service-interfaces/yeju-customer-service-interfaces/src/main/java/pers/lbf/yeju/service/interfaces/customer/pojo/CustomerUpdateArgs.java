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

package pers.lbf.yeju.service.interfaces.customer.pojo;

import pers.lbf.yeju.common.core.args.IUpdateArgs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/22 10:31
 */
public class CustomerUpdateArgs extends CustomerAuthenticationArgs implements IUpdateArgs, Serializable {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式不正确")
    private String phoneNumber;

    /**
     * 手机区号，比如中国是+86，详见属性表
     */
    private String phoneNumberPrefix;


    @Override
    public void setChangedBy(String account) {
        this.changedBy = Long.valueOf(account);

    }

    @Override
    public void setUpdateTime(Date date) {
        this.updateTime = date;
    }
}
