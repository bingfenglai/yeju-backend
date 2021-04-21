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
package pers.lbf.yeju.provider.payment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.lbf.yeju.common.domain.entity.business.payment.AccountBalanceChangeLog;

/**
 * 对于平台内部充值账户的余额转入转出记录,  交易费用记录  以月份作为分区标识
 * 账户余额变动记录表(TableBusinessAccountBalanceChangeLog)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-04-22 00:44:21
 */
public interface AccountBalanceChangeLogDao extends BaseMapper<AccountBalanceChangeLog> {

}