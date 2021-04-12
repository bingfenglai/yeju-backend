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
package pers.lbf.yeju.service.interfaces.message;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 21:15
 */
public class TextMessage extends BaseMessage<String> implements Message<String>, Serializable {
    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getForm() {
        return this.form;
    }

    @Override
    public Long getSendTo() {
        return this.sendTo;
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
    public Long getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "message=" + message +
                ", form='" + form + '\'' +
                ", sendTo=" + sendTo +
                ", date=" + date +
                ", messageType='" + messageType + '\'' +
                ", messageId=" + messageId +
                '}';
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public void setSendTo(Long sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
