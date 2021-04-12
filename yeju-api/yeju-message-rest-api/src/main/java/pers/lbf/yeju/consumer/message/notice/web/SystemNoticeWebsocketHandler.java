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
import pers.lbf.yeju.base.security.authorization.manager.AuthorizationTokenManager;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.constant.StatusConstants;
import pers.lbf.yeju.consumer.message.notice.sender.NoticeDeliverLogSender;
import pers.lbf.yeju.consumer.message.notice.util.NoticeTypeUtil;
import pers.lbf.yeju.service.interfaces.log.pojo.MessageDeliveryLogCreateArgs;
import pers.lbf.yeju.service.interfaces.message.constant.MessageTypeConstant;
import pers.lbf.yeju.service.interfaces.message.constant.ReceiverTypeConstant;
import pers.lbf.yeju.service.interfaces.message.notice.INoticeService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
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

    @Autowired
    private NoticeDeliverLogSender logSender;

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
            doAuthenticate(webSocketMessage, session);

        }).doOnError(throwable -> {
            log.error("ws 发生错误");
            log.error(String.valueOf(throwable));
        }).doOnRequest(value -> {

            log.info("do on request");
        }).then();
    }


    /**
     * 用户上线拉去未读消息方法
     *
     * @param principal
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/7 16:44
     */
    @Async
    void pullUnReadNotice(String principal) {

        List<String> messageList = noticeService.findEffectiveNoticeList(principal).getData();
        log.info("消息列表 {}", messageList.toString());
        WebSocketSession session = sessionMap.get(principal);
        for (String msgJson : messageList) {
            JSONObject jsonObject = JSONObject.parseObject(msgJson);
            jsonObject.put("type", NoticeTypeUtil.getNoticeType((String) jsonObject.get("type")));
            //前端直接接收Long类型，js转number会丢失精度，一次需要转String类型
            jsonObject.put("messageId", jsonObject.get("messageId").toString());
            String s = JSONObject.toJSONString(jsonObject);
            log.info(s);
            session.send(Flux.just(session.textMessage(s))).then()
                    // 投递成功发送成功日志
                    .doOnSuccess(unused -> {
                        sendLog(jsonObject, principal, true);
                    })
                    //投递失败发送失败日志
                    .doOnError(throwable -> {
                        sendLog(jsonObject, principal, false);
                    }).toProcessor();
        }

    }

    /**
     * 推送即时通知
     *
     * @param jsonObject 消息 json对象
     * @param principal  账号
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/7 17:07
     */
    public void pushInstantNotice(JSONObject jsonObject, String principal) {

        String jsonMsg = jsonObject.toJSONString();
        WebSocketSession session = sessionMap.get(principal);
        if (session == null) {
            log.info("session is null");
        } else {
            session.send(Flux.just(session.textMessage(jsonMsg)))
                    .then()
                    .doOnSuccess(unused -> {
                        sendLog(jsonObject, principal, true);
                    })
                    .doOnError(throwable -> {
                        log.error("消息未发送");
                        // 会话出现异常时 移除会话信息
                        //  等其客户端重新建立会话
                        if (principal != null) {
                            sessionMap.remove(principal);
                        }
                        sendLog(jsonObject, principal, false);
                    }).toProcessor();
        }
    }


    public static Map<String, WebSocketSession> getSessionMap() {
        return sessionMap;
    }

    private void sendLog(JSONObject jsonObject, String principal, boolean flag) {
        log.info("发送消息日志 {}", jsonObject.toJSONString());
        Long messageId = Long.valueOf(jsonObject.get("messageId").toString());
        log.info("消息 id {}", messageId);
        MessageDeliveryLogCreateArgs args = new MessageDeliveryLogCreateArgs();
        args.setMessageId(messageId);
        args.setMessageType(MessageTypeConstant.SYSTEM_MESSAGE);
        args.setReceiverType(ReceiverTypeConstant.GROUP);
        if (flag) {
            args.setDeliveryStatus(StatusConstants.ABLE.toString());
        } else {
            args.setDeliveryStatus(StatusConstants.DISABLE.toString());
        }
        args.setDeliveryTime(new Date());
        args.setPrincipal(principal);
        logSender.send(args);

    }

    private void doAuthenticate(WebSocketMessage webSocketMessage, WebSocketSession session) {
        if (webSocketMessage.getType().equals(WebSocketMessage.Type.TEXT)) {
            String token = webSocketMessage.getPayloadAsText();
            AuthorityInfoBean authorityInfoBean = null;
            try {
                authorityInfoBean = tokenManager.getAuthorityInfo(token);
                log.debug(authorityInfoBean.toString());
                String principal = authorityInfoBean.getPrincipal();
                sessionMap.put(principal, session);
                log.info("会话key {}", principal);
                this.pullUnReadNotice(principal);
            } catch (Exception e) {
                log.error(String.valueOf(e));
            }


        }
    }

}
