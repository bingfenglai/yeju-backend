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
package pers.lbf.yeju.consumer.message.notice.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 通知消息显示类型工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/18 11:02
 */
public class NoticeTypeUtil {

    private static final Map<String, String> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put("1", "warning");
        typeMap.put("2", "info");
    }

    private NoticeTypeUtil() {

    }

    public static String getNoticeType(String type) {
        return typeMap.get(type);
    }
}
