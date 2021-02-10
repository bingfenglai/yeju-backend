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
package pers.lbf.yeju.provider.session.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.domain.entity.Account;
import pers.lbf.yeju.provider.auth.account.dao.IAccountDao;
import pers.lbf.yeju.provider.base.util.SpringContextUtils;
import pers.lbf.yeju.provider.session.strategy.ISessionStrategy;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/5 15:01
 */

public enum PhoneNumberSessionStrategy implements ISessionStrategy {
    /**
     * 枚举类型实现单例模式
     */
    INSTANCE;

    private IAccountDao accountDao;



    @Override
    public void initSessionByAccountType(String principal) throws ServiceException {
        // 1.获取账户信息
        IAccountDao accountDao = SpringContextUtils.getBean(IAccountDao.class);
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number",principal);
        Account account = accountDao.selectOne(wrapper);
        // 2.获取账户关联的角色

    }

    @Override
    public void destroySession(String principal) throws ServiceException {

    }
}
