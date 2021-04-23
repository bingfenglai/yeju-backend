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

package pers.lbf.yeju.service.interfaces.product.constants;

/**
 * 房源客户关系常量
 * 主要有租赁关系、归属关系，收藏关系，推荐关系，踩 关系，待交租金关系，待续交租金关系、租赁过 关系
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/23 16:40
 */
public class RelationshipCustomerHouseTypeConstants {
    /**
     * 租赁关系
     */
    public static final String LEASE_RELATIONSHIP = "0";

    /**
     * 归属关系
     */
    public static final String ADSCRIPTION_RELATIONSHIP = "1";

    /**
     * 收藏关系
     */
    public static final String COLLECTION_RELATIONSHIP = "2";

    /**
     * 推荐关系
     */
    public static final String RECOMMEND_RELATIONSHIP = "3";

    /**
     * 踩 关系
     */
    public static final String DISGUSTING_RELATIONSHIP = "4";

    /**
     * 待交租金关系
     */
    public static final String PENDING_RENTAL_RELATIONSHIP = "5";

    /**
     * 待续交租金关系
     */
    public static final String to_be_renewed_rental_relationship = "6";

    /**
     * 租赁过 关系
     */
    public static final String leased_Relationship = "7";
}
