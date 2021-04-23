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

package pers.lbf.yeju.service.interfaces.product.status;

import java.io.Serializable;

/**
 * 房源状态枚举
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/23 12:44
 */
public enum HouseStatusEnum implements Serializable {

    /**
     * 未审核
     */
    unCheck,
    /**
     * 可交易,待租
     */
    tradable,

    /**
     * 预交易(房客已付款，房东未交房的状态)
     */
    pre_transaction,

    /**
     * 在租赁中
     */
    renting;

}
