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

import pers.lbf.yeju.common.core.message.BaseMessage;
import pers.lbf.yeju.common.core.message.Message;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 17:05
 */
public class NoticeMessageVO extends BaseMessage<String> implements Serializable, Message<String> {
    private String title;

    // 通知类型，'warning', 'info',
    private String type;

    @Override
    public String toString() {
        return "NoticeMessageVO{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", sendTo='" + sendTo + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String getSendTo() {
        return sendTo;
    }

    @Override
    public Date getSendDate() {
        return this.date;
    }

    @Override
    public String getReceiverType() {
        return this.receiverType;
    }

    public void setSendTo(String sendTo) {
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
    public String getMessage() {
        return message;
    }

    @Override
    public String getForm() {
        return this.form;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}