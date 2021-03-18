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
package pers.lbf.yeju.provider.message.notice;

import pers.lbf.yeju.service.interfaces.message.constant.NoticeConstant;

/**
 * 通知消息 缓存 key 生成
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/10 11:47
 */
public class NoticeCacheKeyManager {

    public static String generateNoticeKey(Long noticeId, Long sendTo) {
        return NoticeConstant.REDIS_KEY_PREFIX + sendTo + noticeId;
    }

    /**
     * 用于获取指定的notice key
     *
     * @param noticeId
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/10 11:58
     */
    public static String generateAllNoticeKeyPattern(String noticeId) {

        return NoticeConstant.REDIS_KEY_PREFIX + "**" + noticeId;
    }


    /**
     * 用于获取subject所有未读消息 key
     *
     * @param sendTo
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/10 11:57
     */
    public static String generateNoticeKeyAllMessagePattern(Long sendTo) {

        return NoticeConstant.REDIS_KEY_PREFIX + sendTo + "**";
    }
}
