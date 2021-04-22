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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.tarde.TradingInformationHouse;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.trade.dao.ITradeDao;
import pers.lbf.yeju.service.interfaces.trade.ITradeService;
import pers.lbf.yeju.service.interfaces.trade.pojo.SimpleTradeInfoBean;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeCreateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeQueryArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeUpdateArgs;
import pers.lbf.yeju.service.interfaces.trade.status.TradeStatusConstants;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/22 11:10
 */
@DubboService(interfaceClass = ITradeService.class, timeout = 10000, retries = 0)
@Slf4j
public class TradeServiceImpl implements ITradeService {

    @Autowired
    private ITradeDao tradeDao;

    @Override
    public PageResult<SimpleTradeInfoBean> queryPage(TradeQueryArgs args) throws ServiceException {
        Page<TradingInformationHouse> page = PageUtil.getPage(TradingInformationHouse.class, args);
        QueryWrapper<TradingInformationHouse> queryWrapper = queryWrapperBuild(args);
        Page<TradingInformationHouse> tradingInformationHousePage = tradeDao.selectPage(page, queryWrapper);
        List<SimpleTradeInfoBean> result = new LinkedList<>();
        for (TradingInformationHouse trade : tradingInformationHousePage.getRecords()) {
            SimpleTradeInfoBean bean = tradeToBean(trade);
            result.add(bean);
        }

        return PageResult.ok(tradingInformationHousePage.getTotal(), tradingInformationHousePage.getCurrent(), tradingInformationHousePage.getSize(), result);
    }

    private SimpleTradeInfoBean tradeToBean(TradingInformationHouse trade) {
        SimpleTradeInfoBean simpleTradeInfoBean = new SimpleTradeInfoBean();
        simpleTradeInfoBean.setFree(trade.getFree());
        simpleTradeInfoBean.setHouseRentUnitFree(trade.getHouseRentUnitFree());
        simpleTradeInfoBean.setRentUnit(trade.getRentUnit());
        simpleTradeInfoBean.setTradeId(trade.getTradeId());
        simpleTradeInfoBean.setHouseId(trade.getHouseId());
        simpleTradeInfoBean.setLandlordId(trade.getLandlordId());
        simpleTradeInfoBean.setTenantId(trade.getTenantId());
        simpleTradeInfoBean.setTradingStatus(trade.getTradingStatus());
        simpleTradeInfoBean.setCreateTime(trade.getCreateTime());
        return simpleTradeInfoBean;
    }

    private QueryWrapper<TradingInformationHouse> queryWrapperBuild(TradeQueryArgs args) {
        QueryWrapper<TradingInformationHouse> queryWrapper = new QueryWrapper<>();
        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getTradingStatus())) {
            queryWrapper.eq("trading_status", args.getTradingStatus());
        }
        if (YejuStringUtils.isNotEmpty(args.getDataRange()) && args.getDataRange().length == 2) {
            String[] dataRange = args.getDataRange();
            queryWrapper.between("create_time", dataRange[0], dataRange[1]);
        }

        return queryWrapper;
    }

    @Override
    public IResult<Boolean> create(TradeCreateArgs args) throws ServiceException {
        //
        return null;
    }

    @Override
    public IResult<Boolean> updateById(TradeUpdateArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> removeById(Long id) throws ServiceException {
        int i = tradeDao.deleteById(id);
        return Result.ok(i == 1);
    }

    @Override
    public IResult<Boolean> changeStatusById(Long id, TradeStatusConstants status) throws ServiceException {
        return null;
    }
}