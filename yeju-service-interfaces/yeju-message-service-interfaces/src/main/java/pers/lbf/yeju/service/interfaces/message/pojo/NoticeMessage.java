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
package pers.lbf.yeju.service.interfaces.message.pojo;

import pers.lbf.yeju.service.interfaces.message.BaseMessage;
import pers.lbf.yeju.service.interfaces.message.Message;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 17:05
 */
public class NoticeMessage extends BaseMessage<String> implements Serializable, Message<String> {
    private String title;

    // 通知类型，'warning', 'info',
    private String type;

    private Date endDate;


    @Override
    public String toString() {
        return "NoticeMessage{" +
                "message=" + message +
                ", form='" + form + '\'' +
                ", sendTo=" + sendTo +
                ", date=" + date +
                ", receiverType='" + messageType + '\'' +
                ", messageId=" + messageId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", endDate=" + endDate +
                '}';
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public Long getSendTo() {
        return sendTo;
    }

    @Override
    public Date getSendDate() {
        return this.date;
    }

    @Override
    public String getMessageType() {
        return this.messageType;
    }

    @Override
    public void setSendTo(Long sendTo) {
        this.sendTo = sendTo;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Long getMessageId() {
        return this.messageId;
    }

    @Override
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getForm() {
        return this.form;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }


}
