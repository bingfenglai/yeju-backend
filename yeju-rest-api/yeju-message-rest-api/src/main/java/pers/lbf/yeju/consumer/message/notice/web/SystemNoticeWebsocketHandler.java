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
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.consumer.base.security.manager.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.base.security.pojo.AuthorityInfo;
import pers.lbf.yeju.consumer.message.notice.pojo.NoticeMessageVO;
import pers.lbf.yeju.consumer.message.notice.util.NoticeTypeUtil;
import pers.lbf.yeju.service.interfaces.message.INoticeService;
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
                    log.info(authorityInfo.toString());

                    sessionMap.put(authorityInfo.getPrincipal(), session);
                    log.info("会话key {}", authorityInfo.getPrincipal());
                } catch (Exception e) {
                    log.error(String.valueOf(e));
                }
                if (authorityInfo == null) {
                    throw new ServiceException(AuthStatusEnum.NO_TOKEN);
                }

                this.send(session);
            }


        }).doOnError(throwable -> {
            log.error("ws 发生错误");
            log.error(String.valueOf(throwable));
        }).doOnRequest(value -> {

            log.info("do on request");
        }).then();
    }

    private void send(WebSocketSession session) {
        List<SimpleNoticeInfoBean> list = noticeService.findEffectiveNoticeList().getData();
        List<NoticeMessageVO> msgList = new LinkedList<>();
        for (SimpleNoticeInfoBean bean : list) {
            NoticeMessageVO message = new NoticeMessageVO();
            message.setMessage(bean.getContent());
            message.setTitle(bean.getTitle());
            msgList.add(message);
            String noticeType = NoticeTypeUtil.getNoticeType(bean.getNoticeType());
            message.setType(noticeType);
            log.info(bean.getNoticeType());
        }

        for (NoticeMessageVO message : msgList) {
            log.info(message.toString());
            String s = JSONObject.toJSONString(message);
            session.send(Flux.just(session.textMessage(s))).then().toProcessor();
        }


    }

    public void send(NoticeMessageVO message) {
        WebSocketSession session = sessionMap.get("969391");
        send(session, message);
    }

    private void send(WebSocketSession session, NoticeMessageVO message) {

        log.info(message.toString());
        String s = JSONObject.toJSONString(message);
        if (session == null) {
            log.info("session is null");
        }
        session.send(Flux.just(session.textMessage(s))).then().doOnError(throwable -> {
            log.error("消息未发送");
        }).toProcessor();
    }
}
