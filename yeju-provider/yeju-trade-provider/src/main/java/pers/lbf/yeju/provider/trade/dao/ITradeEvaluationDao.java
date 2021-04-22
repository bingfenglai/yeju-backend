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
package pers.lbf.yeju.provider.trade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.lbf.yeju.common.domain.entity.tarde.TradeEvaluation;

/**
 * 交易评价表，记录房源交易评价信息。实时评价记录在mongodb数据库。每天同步评论到mysql。以访问者IP最后一位作为(TableBusinessTradingEvaluation)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-22 20:52:16
 */
public interface ITradeEvaluationDao extends BaseMapper<TradeEvaluation> {

}