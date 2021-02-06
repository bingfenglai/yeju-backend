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
package pers.lbf.yeju.provider.auth.session.strategy.impl;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.provider.auth.session.strategy.ISessionStrategy;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/5 15:01
 */
public enum SystemAccountSessionStrategy implements ISessionStrategy {
    /**
     * 枚举类型实现单例模式
     */
    INSTANCE;
    @Override
    public void initSessionByAccountType(String principal) throws ServiceException {

    }

    @Override
    public void destroySession(String principal) throws ServiceException {

    }
}
