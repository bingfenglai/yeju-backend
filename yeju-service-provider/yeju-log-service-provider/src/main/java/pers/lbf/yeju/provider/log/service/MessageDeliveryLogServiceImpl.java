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
package pers.lbf.yeju.provider.log.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.constant.StatusConstants;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.log.MessageDeliveryLog;
import pers.lbf.yeju.provider.log.dao.IMessageDeliveryLogDao;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.log.IMessageDeliveryLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.MessageDeliveryLogCreateArgs;
import pers.lbf.yeju.service.interfaces.message.constant.ReceiverTypeConstant;
import pers.lbf.yeju.service.interfaces.message.manager.MessageCacheKeyManager;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/6 19:53
 */
@DubboService(interfaceClass = IMessageDeliveryLogService.class)
@Slf4j
@Service
public class MessageDeliveryLogServiceImpl implements IMessageDeliveryLogService {

    @Autowired
    private IMessageDeliveryLogDao messageDeliveryLogDao;

    @DubboReference
    private IAccountService accountService;

    @Autowired
    private IRedisService redisService;

    @Override
    @Async
    public void addOneLog(MessageDeliveryLogCreateArgs args) throws ServiceException {
        Long receiverId = accountService.findAccountIdByPrincipal(args.getPrincipal()).getData();

        MessageDeliveryLog log = messageDeliveryLogCreateArgsToMessageDeliveryLog(args);
        log.setReceiverId(receiverId);

        if (StatusConstants.ABLE.toString().equals(args.getDeliveryStatus())) {

            if (args.getReceiverType().equals(ReceiverTypeConstant.GROUP)) {
                // 添加已读缓存
                String readMgsKey = MessageCacheKeyManager.generateGroupMessageReadCacheKey(args.getMessageId(), receiverId);

                redisService.addCacheObject(readMgsKey, "");
            }

            if (args.getReceiverType().equals(ReceiverTypeConstant.PERSONAL)) {
                // 从缓存数据库中移除消息
                String messageKey = MessageCacheKeyManager.generateMessageCacheKey(args.getMessageId());
                redisService.deleteObject(messageKey);
            }
        }

        messageDeliveryLogDao.insert(log);

    }

    @Override
    public IResult<Boolean> isExistsAndDeliveredSuccessfully(String principal, Long messageId) throws ServiceException {
        Long accountId = accountService.findAccountIdByPrincipal(principal).getData();
        Integer count = messageDeliveryLogDao.isExist(accountId, messageId);
        boolean flag = count > 0;
        return Result.ok(flag);
    }

    private MessageDeliveryLog messageDeliveryLogCreateArgsToMessageDeliveryLog(MessageDeliveryLogCreateArgs args) {
        MessageDeliveryLog messageDeliveryLog = new MessageDeliveryLog();

        messageDeliveryLog.setMessageType(args.getMessageType());
        messageDeliveryLog.setReceiverType(args.getReceiverType());
        messageDeliveryLog.setMessageId(args.getMessageId());
        messageDeliveryLog.setPrincipal(args.getPrincipal());
        messageDeliveryLog.setReceiverId(args.getReceiverId());
        messageDeliveryLog.setDeliveryStatus(args.getDeliveryStatus());
        messageDeliveryLog.setDeliveryTime(args.getDeliveryTime());
        return messageDeliveryLog;
    }
}