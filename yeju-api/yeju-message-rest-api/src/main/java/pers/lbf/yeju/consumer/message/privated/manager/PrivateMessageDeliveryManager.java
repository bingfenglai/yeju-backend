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

package pers.lbf.yeju.consumer.message.privated.manager;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.consumer.message.web.handler.MessageWebsocketHandler;
import pers.lbf.yeju.service.interfaces.message.manager.MessageCacheKeyManager;
import pers.lbf.yeju.service.interfaces.message.manager.MessageDeliverManager;
import pers.lbf.yeju.service.interfaces.message.privated.IPrivateMessageService;
import reactor.core.publisher.Flux;

import java.util.Objects;

/**
 * 私信消息投递管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/7 11:10
 */
@Component("privateMessageDeliveryManager")
@Slf4j
public class PrivateMessageDeliveryManager extends MessageDeliverManager {

    @Autowired
    private MessageWebsocketHandler messageWebsocketHandler;

    @DubboReference
    private IPrivateMessageService privateMessageService;


    @Override
    @Async
    public void delive(String jsonMsgString) throws ServiceException {
        this.doDelive(jsonMsgString);
    }

    /**
     * 投递消息
     *
     * @param jsonMsgString 消息
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/6 16:30
     */
    @Override

    public void doDelive(String jsonMsgString) throws ServiceException {
        //私信消息广播的是消息在redis中的key值，需要根据key确定目标是否在线
        //如果在线则取消息并投递
        //不在线则不做任何处理


        //1.  获取id
//        String s1 = jsonMsgString.substring(MessageCacheKeyConstant.UN_READ_PREFIX.length() + 1);
//        log.info(s1);
//        String receiverId = s1.substring(0, s1.indexOf(":"));
        String receiverId = MessageCacheKeyManager.getPrivateMessageReceiverId(jsonMsgString);
        log.info("key {}", receiverId);
        if (Objects.equals(receiverId, "")) {
            log.info("id is null");
        }
        log.info(receiverId);
        // 2.判断目标是否在线
        boolean flag = MessageWebsocketHandler.getMessageSessionMap().containsKey(receiverId);

        if (flag) {
            log.info("消息接收者{}在线，开始推送", receiverId);
            //2,1 目标在线,进行推送
            String jsonMsg = privateMessageService.pullMessage(jsonMsgString).getData();
            log.info("未读消息：{}", jsonMsg);
            JSONObject jsonObject = JSONObject.parseObject(jsonMsg);
            
            jsonObject.put("type", "0");
            jsonObject.put("pic", "/static/logo.png");
            WebSocketSession session = MessageWebsocketHandler.getMessageSessionMap().get(receiverId);

            session
                    .send(Flux.just(session.textMessage(jsonObject.toJSONString())))
                    .doOnSuccess(unused -> {
                        log.info("消息推送成功");
                    }).toProcessor();


        } else {
            log.info("目标不在本台服务器上");
        }

    }
}
