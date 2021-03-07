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
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.message.IGroupMessageService;
import pers.lbf.yeju.service.interfaces.message.manager.MessageCacheKeyManager;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;

import java.util.Arrays;
import java.util.Collection;

/**
 * 群组消息服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/7 15:30
 */
@DubboService(interfaceClass = IGroupMessageService.class)
@Slf4j
public class GroupMessageServiceImpl implements IGroupMessageService {

    @Autowired
    private IRedisService redisService;

    @DubboReference
    private IAccountService accountService;

    @Override
    public IResult<Boolean> isDeliveredSuccessfully(String principal, Long messageId) throws ServiceException {
        Long receiverId = accountService.findAccountIdByPrincipal(principal).getData();
        String key = MessageCacheKeyManager.generateGroupMessageReadCacheKey(messageId, receiverId);
        log.info("表达式 {}" + key);
        Collection<String> keys = redisService.keys(key);
        log.info(Arrays.toString(keys.toArray()));
        if (keys.size() > 0) {
            return Result.ok(true);
        } else {
            return Result.ok(false);
        }
    }
}