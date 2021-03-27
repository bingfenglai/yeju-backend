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

package pers.lbf.yeju.common.blur.strategy.impl;

import pers.lbf.yeju.common.blur.strategy.BlurStrategy;
import pers.lbf.yeju.common.util.YejuInfoSecurityUtil;

/**
 * 姓名模糊处理类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/27 22:19
 */
public enum NameBlurStrategy implements BlurStrategy {
    /**
     * 实例
     */
    INSTANCE;
    private int prefix = 1;
    private int suffix = 1;

    @Override
    public int getPrefix() {
        return this.prefix;
    }

    @Override
    public int getSuffix() {
        return this.suffix;
    }

    @Override
    public String doBlur(String value) {
        if (value == null) {
            return value;
        }
        if (value.length() >= 3) {
            return YejuInfoSecurityUtil.around(value, prefix, suffix);
        } else {
            return YejuInfoSecurityUtil.left(value, prefix);
        }

    }
}
