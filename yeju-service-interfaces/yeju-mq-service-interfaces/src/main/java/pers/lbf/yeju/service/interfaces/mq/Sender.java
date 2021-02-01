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
package pers.lbf.yeju.service.interfaces.mq;

import pers.lbf.yeju.common.core.exception.service.ServiceException;

import java.util.Map;

/**
 * TODO
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/15 16:36
 */
public interface Sender {

    /**
     * 发送消息方法
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/15 16:38
     * @param message 消息对象
     * @return void
     * @throws ServiceException e
     */
    void sendMesage(Object message, Map<String, Object> properties) throws ServiceException;

    /**
     * 发送消息方法
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/15 16:38
     * @param message 消息对象
     * @return void
     * @throws ServiceException e
     */
    void sendMesage(Object message) throws ServiceException;
}
