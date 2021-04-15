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
package pers.lbf.yeju.provider.message.message.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.message.IMessageService;
import pers.lbf.yeju.service.interfaces.message.TextMessage;
import pers.lbf.yeju.service.interfaces.message.constant.ReceiverTypeConstant;
import pers.lbf.yeju.service.interfaces.message.manager.MessageCacheKeyManager;
import pers.lbf.yeju.service.interfaces.message.privated.IPrivateMessageService;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;

import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/7 18:24
 */
@DubboService(interfaceClass = IMessageService.class, timeout = 10000, retries = 0)
@Slf4j
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private IRedisService redisService;

    @DubboReference
    private IAccountService accountService;

    @DubboReference
    private IPrivateMessageService privateMessageService;

    /**
     * 确认读
     *
     * @param messageId    消息标识
     * @param receiverId   接收者标识
     * @param receiverType 消息接收者类型 1群组 2个人
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/7 18:35
     */
    @Override
    public void confirmRead(Long messageId, Long receiverId, String receiverType) throws ServiceException {

        if (ReceiverTypeConstant.GROUP.equals(receiverType)) {
            // 添加已读缓存
            String readMgsKey = MessageCacheKeyManager.generateGroupMessageReadCacheKey(messageId, receiverId);
            redisService.addCacheObject(readMgsKey, "");
        }

        if (ReceiverTypeConstant.PERSONAL.equals(receiverType)) {
            // 从缓存数据库中移除消息
            String messageKey = MessageCacheKeyManager.generateMessageCacheKey(messageId);
            redisService.deleteObject(messageKey);
        }
    }

    /**
     * 确认读
     *
     * @param messageId    消息标识
     * @param principal    消息接收者抽象账号
     * @param receiverType 消息接收者类型 1，群组 2 个人
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/7 18:36
     */
    @Override
    public void confirmRead(Long messageId, String principal, String receiverType) throws ServiceException {

        Long receiverId = accountService.findAccountIdByPrincipal(principal).getData();
        confirmRead(messageId, receiverId, receiverType);
    }

    @Override
    public void addMessage(TextMessage message) throws ServiceException {


    }

    @Override
    public IResult<List<String>> pullMessageByAccountId(Long accountId) throws ServiceException {
        // 1. 拉取系统未读消息
        // 2. 拉去群消息
        // 3. 拉取私人消息
        // 暂时只实现拉取私人消息
        return privateMessageService.pullMessageByAccountId(accountId);

    }
}