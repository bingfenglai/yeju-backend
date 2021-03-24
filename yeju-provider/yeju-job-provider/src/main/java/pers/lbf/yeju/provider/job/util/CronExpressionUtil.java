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
package pers.lbf.yeju.provider.job.util;

import org.quartz.CronExpression;

/**
 * cron表达式工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/24 21:38
 */
public class CronExpressionUtil {

    private CronExpressionUtil() {

    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression cron 表达式
     * @return boolean
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/24 21:40
     */
    public static boolean isVailable(String cronExpression) {

        if (cronExpression == null || "".equals(cronExpression)) {
            return false;
        }

        return CronExpression.isValidExpression(cronExpression);
    }
}
