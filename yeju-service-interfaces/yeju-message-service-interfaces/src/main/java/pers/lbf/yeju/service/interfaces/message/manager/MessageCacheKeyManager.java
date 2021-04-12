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

    /**
     * 生成群组消息缓存键
     *
     * @param messageId
     * @param receiverId
     * @return
     */
    public static String generateGroupMessageReadCacheKey(Long messageId, Long receiverId) {
        return MessageCacheKeyConstant.READ_PREFIX + messageId + ":" + receiverId;
    }

    /**
     * 生成群组消息查找匹配表达式
     *
     * @param messageId
     * @param receiverIdPattern
     * @return
     */
    public static String generateGroupMessageReadCacheKeyPattern(Long messageId, String receiverIdPattern) {
        return MessageCacheKeyConstant.READ_PREFIX + messageId + receiverIdPattern;
    }

    /**
     * 生成私聊消息缓存建
     *
     * @param messageId
     * @param receiverId
     * @return
     */
    public static String generatePrivateMessageUnReadCacheKey(Long messageId, Long receiverId) {
        return MessageCacheKeyConstant.UN_READ_PREFIX + receiverId + ":" + messageId;
    }

    /**
     * 生成私聊消息查找匹配模式
     *
     * @param receiverId
     * @param pattern
     * @return
     */
    public static String generatePrivateMessageUnReadCacheKeyPattern(Long receiverId, String pattern) {
        return MessageCacheKeyConstant.UN_READ_PREFIX + receiverId + ":" + pattern;
    }

    /**
     * 通过缓存key获取接收者id
     *
     * @param cacheKey
     * @return
     */
    public static String getPrivateMessageReceiverId(String cacheKey) {
        System.out.println("key： " + cacheKey);
        //1. 去掉前缀
        String s1 = cacheKey.substring(MessageCacheKeyConstant.UN_READ_PREFIX.length());

        // 2. 去后缀
        String s2 = s1.substring(0, s1.indexOf(":"));
        System.out.println("id: " + s2);
        return s2;
    }

    public static void main(String[] args) {
        String s = "yeju:message:private:unread:2:1380433657484201985";
        String receiverId = getPrivateMessageReceiverId(s);
        System.out.println("消息接收者：" + receiverId);
    }


}
