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
package pers.lbf.yeju.provider.trade.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.tarde.TradeEvaluation;
import pers.lbf.yeju.provider.trade.dao.ITradeEvaluationDao;
import pers.lbf.yeju.service.interfaces.trade.ITradeEvaluationService;
import pers.lbf.yeju.service.interfaces.trade.pojo.SimpleTradeEvaluationInfoBean;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeEvaluationCreateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeEvaluationQueryArgs;

/**
 * 交易评价服务实现
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 22:43
 */
@DubboService(interfaceClass = ITradeEvaluationService.class, timeout = 10000, retries = 0)
@Slf4j
public class TradeEvaluationServiceImpl implements ITradeEvaluationService {

    @Autowired
    private ITradeEvaluationDao tradeEvaluationDao;

    @Override
    public PageResult<SimpleTradeEvaluationInfoBean> queryPage(TradeEvaluationQueryArgs args) throws ServiceException {
        
        return null;
    }

    @Override
    public IResult<Boolean> create(TradeEvaluationCreateArgs args) throws ServiceException {
        TradeEvaluation tradeEvaluation = createArgsToTradeEvaluation(args);
        int i = tradeEvaluationDao.insert(tradeEvaluation);
        return Result.ok(i == 1);
    }

    private TradeEvaluation createArgsToTradeEvaluation(TradeEvaluationCreateArgs args) {
        TradeEvaluation tradeEvaluation = new TradeEvaluation();
        tradeEvaluation.setParentId(args.getParentId());
        tradeEvaluation.setTradingId(args.getTradingId());
        tradeEvaluation.setContent(args.getContent());
        tradeEvaluation.setCommentTime(args.getCommentTime());
        tradeEvaluation.setCreateTime(args.getCreateTime());
        tradeEvaluation.setCreateBy(args.getCreateBy());
        tradeEvaluation.setDescriptiveCoincidence(args.getDescriptiveCoincidence());
        tradeEvaluation.setLandlordService(args.getLandlordService());
        tradeEvaluation.setTenantBehavior(args.getTenantBehavior());
        tradeEvaluation.setEvaluationType(args.getEvaluationType());
        tradeEvaluation.setEvaluationStatus(args.getEvaluationStatus());
        tradeEvaluation.setEvaluationLikeCount(0L);

        return tradeEvaluation;

    }

    @Override
    public IResult<Boolean> removeById(Long id) throws ServiceException {
        int i = tradeEvaluationDao.deleteById(id);
        return Result.ok(i == 1);
    }


}