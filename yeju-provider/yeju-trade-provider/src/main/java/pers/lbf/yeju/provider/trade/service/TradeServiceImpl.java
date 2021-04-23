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
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.tarde.TradingInformationHouse;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.trade.constants.TradingStatusConstants;
import pers.lbf.yeju.provider.trade.constants.TradingTypeConstants;
import pers.lbf.yeju.provider.trade.dao.ITradeDao;
import pers.lbf.yeju.provider.trade.sender.TradingSender;
import pers.lbf.yeju.provider.trade.status.TradeServiceStatusEnum;
import pers.lbf.yeju.service.interfaces.product.IHouseInfoService;
import pers.lbf.yeju.service.interfaces.product.IHouseRelationshipService;
import pers.lbf.yeju.service.interfaces.product.constants.RelationshipCustomerHouseTypeConstants;
import pers.lbf.yeju.service.interfaces.product.status.HouseStatusEnum;
import pers.lbf.yeju.service.interfaces.trade.ITradeService;
import pers.lbf.yeju.service.interfaces.trade.pojo.HouseTradeCreateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.HouseTradeUpdateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.SimpleTradeInfoBean;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeQueryArgs;
import pers.lbf.yeju.service.interfaces.trade.status.TradeStatusConstants;

import java.time.Duration;
import java.util.Date;
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

    @DubboReference
    private IHouseInfoService houseInfoService;

    @DubboReference
    private IHouseRelationshipService houseRelationshipService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TradingSender tradeSender;

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


    @Override
    public IResult<Boolean> createHouseTrade(HouseTradeCreateArgs args) throws ServiceException {
        // 判断房源状态，如果可用 则锁上
        IResult<HouseStatusEnum> rpcResult = houseInfoService.getHouseStatusEnumById(args.getHouseId());
        if (!rpcResult.isSuccess()) {
            throw ServiceException.getInstance(TradeServiceStatusEnum.create_trade_fail_unkonw_house_status);
        }

        if (!HouseStatusEnum.tradable.equals(rpcResult.getData())) {
            throw ServiceException.getInstance(TradeServiceStatusEnum.create_trade_fail_house_un_tradable);
        }

        // 加分布式锁
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(String.valueOf(args.getHouseId()), 1, Duration.ofHours(24));

        assert flag != null;
        if (!flag) {
            throw ServiceException.getInstance(TradeServiceStatusEnum.create_trade_fail_house_un_tradable);
        }

        TradingInformationHouse trade = buildTrade(args);

        tradeDao.insert(trade);


        // 发布 订单创建成功事件 走支付流程
        

        return null;
    }

    private TradingInformationHouse buildTrade(HouseTradeCreateArgs args) {
        TradingInformationHouse tradingInformationHouse = new TradingInformationHouse();
        tradingInformationHouse.setTenancy(args.getTenancy());
        tradingInformationHouse.setFree(args.getFree());
        tradingInformationHouse.setHouseRentUnitFree(args.getHouseRentUnitFree());
        tradingInformationHouse.setRentUnit(args.getRentUnit());

        tradingInformationHouse.setHouseId(args.getHouseId());
        Long landlordId = houseRelationshipService.getCustomerIdByHouseIdAndRelationshipType(args.getHouseId(),
                RelationshipCustomerHouseTypeConstants.ADSCRIPTION_RELATIONSHIP).getData();
        tradingInformationHouse.setLandlordId(landlordId);
        tradingInformationHouse.setTenantId(args.getTenantId());
        tradingInformationHouse.setTradingStatus(args.getTradingStatus());
        tradingInformationHouse.setCreateTime(new Date());
        tradingInformationHouse.setTradingStatus(TradingStatusConstants.CREATED);
        tradingInformationHouse.setRemark(args.getRemark());

        tradingInformationHouse.setTradingType(TradingTypeConstants.HOUSE_TRADE);
        return tradingInformationHouse;
    }

    @Override
    public IResult<Boolean> updateById(HouseTradeUpdateArgs args) throws ServiceException {
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

}