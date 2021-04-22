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
import pers.lbf.yeju.service.interfaces.trade.pojo.SimpleTradeEvaluationInfoBean;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeEvaluationCreateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeEvaluationQueryArgs;

/**
 * 交易评价服务接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 22:41
 */
public interface ITradeEvaluationService {

    PageResult<SimpleTradeEvaluationInfoBean> queryPage(TradeEvaluationQueryArgs args) throws ServiceException;

    IResult<Boolean> create(TradeEvaluationCreateArgs args) throws ServiceException;

    IResult<Boolean> removeById(Long id) throws ServiceException;

}
