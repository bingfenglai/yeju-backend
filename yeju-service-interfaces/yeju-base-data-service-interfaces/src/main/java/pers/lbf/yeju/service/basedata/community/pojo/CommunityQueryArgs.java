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

package pers.lbf.yeju.service.basedata.community.pojo;

import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.args.IFindPageArgs;

import java.io.Serializable;
import java.util.Arrays;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/19 21:31
 */
public class CommunityQueryArgs extends BaseFindPageArgs implements IFindPageArgs, Serializable {
    private String name;
    private String detailedAddress;
    private String[] between;

    public String[] getBetween() {
        return between;
    }

    public void setBetween(String[] between) {
        this.between = between;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }


    @Override
    public String toString() {
        return "CommunityQueryArgs{" +
                "currentPage=" + currentPage +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", between=" + Arrays.toString(between) +
                '}';
    }
}
