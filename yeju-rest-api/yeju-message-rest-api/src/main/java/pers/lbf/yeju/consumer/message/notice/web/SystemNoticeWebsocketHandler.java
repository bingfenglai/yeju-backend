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
package pers.lbf.yeju.consumer.message.notice.web;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import pers.lbf.yeju.consumer.base.security.manager.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.base.security.pojo.AuthorityInfo;
import pers.lbf.yeju.consumer.message.notice.util.NoticeTypeUtil;
import pers.lbf.yeju.service.interfaces.message.INoticeService;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeMessage;
import pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 15:17
 */
@Component
@Slf4j
@EnableAsync(proxyTargetClass = true)
public class SystemNoticeWebsocketHandler implements WebSocketHandler {

    @Autowired
    private AuthorizationTokenManager tokenManager;

    @DubboReference
    private INoticeService noticeService;

    private static Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();


    @Override
    public Mono<Void> handle(WebSocketSession session) {

        return session.receive().doOnSubscribe(subscription -> {
            log.info("收到会话 {}", session.toString());

        }).doOnTerminate(() -> {
            log.info(" doOnTerminate");
        }).doOnComplete(() -> {
            log.info("on complete");
        }).doOnCancel(() -> {
            log.info("on cancel");
        }).doOnNext(webSocketMessage -> {
            if (webSocketMessage.getType().equals(WebSocketMessage.Type.TEXT)) {
                String token = webSocketMessage.getPayloadAsText();
                AuthorityInfo authorityInfo = null;
                try {
                    authorityInfo = tokenManager.getAuthorityInfo(token);
                    log.debug(authorityInfo.toString());
                    String principal = authorityInfo.getPrincipal();
                    sessionMap.put(principal, session);
                    log.info("会话key {}", principal);
                    this.send(session, principal);
                } catch (Exception e) {
                    log.error(String.valueOf(e));
                }


            }


        }).doOnError(throwable -> {
            log.error("ws 发生错误");
            log.error(String.valueOf(throwable));
        }).doOnRequest(value -> {

            log.info("do on request");
        }).then();
    }

    @Async
    void send(WebSocketSession session, String principal) {

        List<String> messageList = noticeService.findEffectiveNoticeList(principal).getData();
        log.info("消息列表 {}", messageList.toString());
        for (String msgJson : messageList) {
            JSONObject jsonObject = JSONObject.parseObject(msgJson);
            jsonObject.put("type", NoticeTypeUtil.getNoticeType((String) jsonObject.get("type")));
            String s = JSONObject.toJSONString(jsonObject);
            session.send(Flux.just(session.textMessage(s))).then()
                    // 投递成功发送成功日志
                    .doOnSuccess(unused -> {

                    })
                    //投递失败发送失败日志
                    .doOnError(throwable -> {

                    }).toProcessor();
        }

    }

    @Deprecated
    private void send(WebSocketSession session) {
        List<SimpleNoticeInfoBean> list = noticeService.findEffectiveNoticeList().getData();
        List<NoticeMessage> msgList = new LinkedList<>();
        for (SimpleNoticeInfoBean bean : list) {
            NoticeMessage message = new NoticeMessage();
            message.setMessage(bean.getContent());
            message.setTitle(bean.getTitle());
            msgList.add(message);
            String noticeType = NoticeTypeUtil.getNoticeType(bean.getNoticeType());
            message.setType(noticeType);
            log.info(bean.getNoticeType());
        }

        for (NoticeMessage message : msgList) {
            log.info(message.toString());
            String s = JSONObject.toJSONString(message);
            session.send(Flux.just(session.textMessage(s))).then().toProcessor();
        }


    }

    /**
     * 给所有在线用户发送推送
     *
     * @param message
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/3 22:08
     */
    public void send(NoticeMessage message) {

        for (String s : sessionMap.keySet()) {
            WebSocketSession session = sessionMap.get(s);
            send(session, message);
        }

    }

    public void send(String jsonString) {
        for (String s : sessionMap.keySet()) {
            WebSocketSession session = sessionMap.get(s);
            send(jsonString, session, s);
        }
    }

    private void send(String jsonMsg, WebSocketSession session) {
        send(jsonMsg, session, null);
    }

    public void send(String jsonMsg, WebSocketSession session, String sessionKey) {
        if (session == null) {
            log.info("session is null");
        } else {
            session.send(Flux.just(session.textMessage(jsonMsg))).then().doOnError(throwable -> {
                log.error("消息未发送");

                // 会话出现异常时 移除会话信息
                //  等其客户端重新建立会话
                if (sessionKey != null) {
                    sessionMap.remove(sessionKey);
                }
            }).toProcessor();
        }
    }

    /**
     * 给指定用户发送推送
     *
     * @param message
     * @param sessionKey
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/3 22:08
     */
    public void send(NoticeMessage message, String sessionKey) {

        WebSocketSession session = sessionMap.get(sessionKey);
        send(session, message);
    }

    private void send(WebSocketSession session, NoticeMessage message) {

        log.info(message.toString());
        String s = JSONObject.toJSONString(message);
        send(s, session);

    }

    public static Map<String, WebSocketSession> getSessionMap() {
        return sessionMap;
    }
}
