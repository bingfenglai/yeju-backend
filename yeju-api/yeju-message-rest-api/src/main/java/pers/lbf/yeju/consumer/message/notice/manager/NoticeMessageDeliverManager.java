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
package pers.lbf.yeju.consumer.message.notice.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.consumer.message.notice.util.NoticeTypeUtil;
import pers.lbf.yeju.consumer.message.notice.web.SystemNoticeWebsocketHandler;
import pers.lbf.yeju.service.interfaces.message.IMessageGroupService;
import pers.lbf.yeju.service.interfaces.message.manager.MessageDeliverManager;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/6 16:10
 */
@Component("noticeMessageDeliverManager")
public class NoticeMessageDeliverManager extends MessageDeliverManager {

    @Autowired
    private SystemNoticeWebsocketHandler noticeWebsocketHandler;

    @DubboReference
    private IMessageGroupService messageGroupService;

    @Override
    public void doDelive(String jsonMsgString) throws ServiceException {
        JSONObject jsonObject = JSONObject.parseObject(jsonMsgString);
        Integer sendTo = (Integer) jsonObject.get("sendTo");
        Map<String, WebSocketSession> sessionMap = SystemNoticeWebsocketHandler.getSessionMap();

        for (String principal : sessionMap.keySet()) {
            if (messageGroupService.receiverExistIn(Long.valueOf(sendTo), principal).isSuccess()) {

                jsonObject.put("type", NoticeTypeUtil.getNoticeType((String) jsonObject.get("type")));

                noticeWebsocketHandler.pushInstantNotice(jsonObject, principal);
            }
        }
    }

    @Override
    @Async
    public void delive(String jsonMsgString) throws ServiceException {
        doDelive(jsonMsgString);

    }
}
