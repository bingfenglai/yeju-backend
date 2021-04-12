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

package pers.lbf.yeju.consumer.message.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import pers.lbf.yeju.base.security.authorization.manager.AuthorizationTokenManager;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.util.JsonUtils;
import pers.lbf.yeju.service.interfaces.message.Message;
import pers.lbf.yeju.service.interfaces.message.TextMessage;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link WebSocketHandler}
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/5 14:35
 */
@Component
@Slf4j
@EnableAsync(proxyTargetClass = true)
public class MessageWebsocketHandler implements WebSocketHandler {

    private static final ConcurrentHashMap<String, WebSocketSession> messageSessionMap = new ConcurrentHashMap<>();

    @Autowired
    private AuthorizationTokenManager tokenManager;

    @Override
    public Mono<Void> handle(WebSocketSession session) {


        return session.receive().doOnSubscribe(subscription -> {
            log.info("收到会话： {}", session.getId());

        })
                .doOnNext(webSocketMessage -> {
                    log.info("收到消息 {}", webSocketMessage.getPayload());
                    try {
                        this.parseMessage(webSocketMessage, session);
                    } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
                        log.error(String.valueOf(e));
                    }

                })
                .then().toProcessor();

    }

    public static ConcurrentHashMap<String, WebSocketSession> getMessageSessionMap() {
        return messageSessionMap;
    }

    private void parseMessage(WebSocketMessage webSocketMessage, WebSocketSession session) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if (webSocketMessage.getType().equals(WebSocketMessage.Type.TEXT)) {
            String jsonStringMsg = webSocketMessage.getPayloadAsText();
            log.info("接收到消息 {}", jsonStringMsg);
            TextMessage msg = new TextMessage();
            try {
                msg = JsonUtils.toBean(jsonStringMsg, TextMessage.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("封装对象：{}", msg.toString());
            String senderId = msg.getForm();

            boolean authcFlags = false;
            if (senderId != null) {
                authcFlags = messageSessionMap.containsKey(senderId);

            } else {
                throw new IllegalArgumentException();
            }
            if (!authcFlags) {
                log.info("会话{}还未认证，进行认证...", session.getId());
                doAuthenticate(msg.getMessage(), session);
                // 认证成功之后，拉去未读消息
                pullUnReadMsg(senderId);
            } else {

                log.info("会话{}已认证", session.getId());

            }
            //更新会话
            log.info("更新会话 key={}", senderId);
            messageSessionMap.put(senderId, session);

        }


    }

    private void pullUnReadMsg(String principal) {
        List<? extends Message<?>> msgList = getUnReadMessageList(principal);
        if (messageSessionMap.containsKey(principal)) {
            WebSocketSession session = messageSessionMap.get(principal);
            this.doPushMessage(session, msgList);
        }
    }


    /**
     * 指定的会话推送方法
     *
     * @param session
     * @param msgList
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/4/5 17:50
     */
    private void doPushMessage(WebSocketSession session, List<? extends Message<?>> msgList) {

    }

    private List<? extends Message<?>> getUnReadMessageList(String principal) {

        return null;
    }


    /**
     * 认证
     *
     * @param token 令牌
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/4/5 17:11
     */
    private void doAuthenticate(String token, WebSocketSession session) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        AuthorityInfoBean authorityInfoBean = null;

        authorityInfoBean = tokenManager.getAuthorityInfo(token);
        log.debug(authorityInfoBean.toString());
        String principal = authorityInfoBean.getPrincipal();


    }


}
