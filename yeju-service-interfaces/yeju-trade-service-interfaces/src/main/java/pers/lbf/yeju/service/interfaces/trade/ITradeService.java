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

package pers.lbf.yeju.service.interfaces.trade;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.trade.pojo.HouseTradeCreateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.HouseTradeUpdateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.SimpleTradeInfoBean;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeQueryArgs;
import pers.lbf.yeju.service.interfaces.trade.status.TradeStatusConstants;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 11:02
 */
public interface ITradeService {

    PageResult<SimpleTradeInfoBean> queryPage(TradeQueryArgs args) throws ServiceException;

    /**
     * TODO
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> createHouseTrade(HouseTradeCreateArgs args) throws ServiceException;

    IResult<Boolean> updateById(HouseTradeUpdateArgs args) throws ServiceException;

    IResult<Boolean> removeById(Long id) throws ServiceException;

    IResult<Boolean> changeStatusById(Long id, TradeStatusConstants status) throws ServiceException;

}
