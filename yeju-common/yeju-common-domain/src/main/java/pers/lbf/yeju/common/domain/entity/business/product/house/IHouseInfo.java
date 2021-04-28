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

package pers.lbf.yeju.common.domain.entity.business.product.house;

import java.util.Date;

/**
 * 房源基础信息接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/18 10:36
 */
public interface IHouseInfo {

    Long getHouseId();

    Long getOwnerId();


    String getTitle();


    Long getCommunityId();


    String getBuildingNumber();


    String getBuildingUint();


    String getBuildingFloorNumber();


    Double getRent();

    String getRentalMode();


    String getHouseType();


    Integer getCoveredArea();


    Integer getUseArea();


    String getFloors();


    String getHouseOrientation();


    String getHouseDecorationType();


    String getHouseFacilities();


    String getDescs();


    String getHouseStatus();


    String getHouseImagesAddress();


    Date getCreateTime();


    Long getCreateBy();


    Date getUpdateTime();


    Long getChangedBy();


    Integer getVersionNumber();


    Integer getIsDelete();


    Integer getMonthAdded();


    Integer getMonthCompleted();


}
