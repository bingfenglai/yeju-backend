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
package pers.lbf.yeju.provider.message.privated.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.provider.base.util.IdHelper;
import pers.lbf.yeju.provider.message.privated.sender.PrivateMessageSender;
import pers.lbf.yeju.service.interfaces.message.TextMessage;
import pers.lbf.yeju.service.interfaces.message.manager.MessageCacheKeyManager;
import pers.lbf.yeju.service.interfaces.message.privated.IPrivateMessageService;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/6 21:13
 */
@DubboService(interfaceClass = IPrivateMessageService.class, timeout = 10000, retries = 0)
@Slf4j
public class PrivateMessageServiceImpl implements IPrivateMessageService {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PrivateMessageSender messageSender;

    @Autowired
    private IdHelper idHelper;


    @Override
    public IResult<Boolean> addMessage(TextMessage message) throws ServiceException {
        // 1. 根据接收者id存到对应的未读队列当中，
        // 2. 广播事件
        // 2.1 接收者在线，读消息
        // 2.2 接收者不在线 不进行额外处理，等待其上线后读取
        message.setMessageId(idHelper.nextId());
        String key = MessageCacheKeyManager
                .generatePrivateMessageUnReadCacheKey(message.getMessageId(), message.getSendTo());
        String jsonMsg = JSONObject.toJSONString(message);
        redisTemplate.opsForValue().set(key, jsonMsg);
        Boolean flag = redisTemplate.hasKey(key);
        log.info("已存入缓存 {}", flag);
        messageSender.send(key, null);
        return Result.success();
    }

    @Override
    public IResult<String> pullMessage(String messageId) throws ServiceException {
        log.info("拉取消息 消息key {}", messageId);
        Boolean flag = redisTemplate.hasKey(messageId);
        log.info("消息存在：{}", flag);
        String jsonMsg = (String) redisTemplate.opsForValue().get(messageId);
        log.info("拉取到消息：{}", jsonMsg);
        return Result.ok(jsonMsg);
    }

    @Override
    public IResult<List<String>> pullMessageByAccountId(Long accountId) throws ServiceException {
        log.info("拉取{}私信", accountId);
        List<String> result = new LinkedList<>();
        String pattern = MessageCacheKeyManager.generatePrivateMessageUnReadCacheKeyPattern(accountId, "**");
        Collection<String> keys = redisService.keys(pattern);
        for (String key : keys) {
            String jsonMessageStr = (String) redisService.getCacheObject(key);
            result.add(jsonMessageStr);
        }

        return Result.ok(result);
    }
}