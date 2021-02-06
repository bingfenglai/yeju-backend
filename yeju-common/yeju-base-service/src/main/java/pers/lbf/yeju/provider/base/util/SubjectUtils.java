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
package pers.lbf.yeju.provider.base.util;

import pers.lbf.yeju.common.core.status.enums.SubjectTypeEnum;
import pers.lbf.yeju.common.util.PhoneUtils;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/17 9:12
 */
public class SubjectUtils {

    private SubjectUtils() {

    }

    /**获取账户类型
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/17 14:42
     * @param principal 抽象账户
     * @return pers.lbf.yeju.common.core.enumes.SubjectType
     */
    public static SubjectTypeEnum getAccountType(String principal){

        boolean phone = PhoneUtils.isPhone(principal);

        return phone? SubjectTypeEnum.is_mobile: SubjectTypeEnum.is_system_account;
    }
}
