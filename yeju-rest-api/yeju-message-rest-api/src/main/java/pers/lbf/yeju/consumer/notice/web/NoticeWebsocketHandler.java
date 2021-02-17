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
package pers.lbf.yeju.consumer.notice.web;

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
import pers.lbf.yeju.consumer.base.security.manger.AuthorizationTokenManager;
import pers.lbf.yeju.consumer.base.security.pojo.AuthorityInfo;
import pers.lbf.yeju.consumer.notice.pojo.NoticeMessage;
import pers.lbf.yeju.service.interfaces.message.INoticeService;
import pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 15:17
 */
@Component
@Slf4j
public class NoticeWebsocketHandler implements WebSocketHandler {

    @Autowired
    private AuthorizationTokenManager tokenManager;

    @DubboReference
    private INoticeService noticeService;


    @Override
    public Mono<Void> handle(WebSocketSession session) {

        return session.receive().doOnSubscribe(subscription -> {
            log.info("收到会话 {}",session.toString());

        }).doOnTerminate(() -> {
            log.info(" doOnTerminate");
        }).doOnComplete(() -> {
            log.info("on complete");
        }).doOnCancel(() -> {
            log.info("on cancel");
        }).doOnNext(webSocketMessage -> {
           if (webSocketMessage.getType().equals(WebSocketMessage.Type.TEXT)){
               String token = webSocketMessage.getPayloadAsText();
               AuthorityInfo authorityInfo = null;
               try {
                   authorityInfo = tokenManager.getAuthorityInfo(token);
               } catch (Exception e) {
                   log.error(String.valueOf(e));
               }
               if (authorityInfo == null) {
                   throw new ServiceException(AuthStatusEnum.NO_TOKEN);
               }

//               log.info(token);
//               NoticeMessage msg = new NoticeMessage();
//               msg.setTitle("系统停服通知！！！");
//               msg.setMessage("椰居平台将于2021年2月19日0时开始停服维护，对于给您带来的不便深表歉意");
//               String s = JSONObject.toJSONString(msg);
//               session.send(Flux.just(session.textMessage(s))).then().toProcessor();
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
        List<NoticeMessage> msgList = new LinkedList<>();
        for (SimpleNoticeInfoBean bean : list) {
            NoticeMessage message = new NoticeMessage();
            message.setMessage(bean.getContent());
            message.setTitle(bean.getTitle());
            msgList.add(message);
        }

        for (NoticeMessage message : msgList) {
            String s = JSONObject.toJSONString(message);
            session.send(Flux.just(session.textMessage(s))).then().toProcessor();
        }


    }
}
