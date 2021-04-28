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
package pers.lbf.yeju.service.interfaces.auth.enums;

import java.io.Serializable;

/**账户类型枚举 0内部账户 1客户账户
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/7 21:49
 */
public enum AccountOwnerTypeEnum implements Serializable {

    /**
     * 内部账号
     */
    Internal_account("0"),

    /**
     * 客户账号
     */
    Customer_account("1");

    String value;

    AccountOwnerTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
