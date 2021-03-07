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
package pers.lbf.yeju.service.interfaces.message.manager;

import pers.lbf.yeju.service.interfaces.message.constant.MessageCacheKeyConstant;

/**
 * 管理key的生成 避免拼接错误
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/7 15:35
 */
public class MessageCacheKeyManager {

    public static String generateMessageCacheKey(Long messageId) {
        return MessageCacheKeyConstant.PREFIX + messageId;
    }

    public static String generateGroupMessageReadCacheKey(Long messageId, Long receiverId) {
        return MessageCacheKeyConstant.READ_PREFIX + messageId + ":" + receiverId;
    }

    public static String generateGroupMessageReadCacheKeyPattern(Long messageId, String receiverIdPattern) {
        return MessageCacheKeyConstant.READ_PREFIX + messageId + receiverIdPattern;
    }
}
