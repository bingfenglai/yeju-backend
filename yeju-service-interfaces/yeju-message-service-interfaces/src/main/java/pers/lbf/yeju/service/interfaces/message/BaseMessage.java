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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 21:15
 */
public abstract class BaseMessage<T> implements Message<T> {
    protected T message;
    protected String form;
    
    protected Long sendTo;
    protected Date date = new Date();
    protected String messageType;

    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    protected Long messageId;

    @Override
    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    @Override
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public Long getSendTo() {
        return sendTo;
    }

    public void setSendTo(Long sendTo) {
        this.sendTo = sendTo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
