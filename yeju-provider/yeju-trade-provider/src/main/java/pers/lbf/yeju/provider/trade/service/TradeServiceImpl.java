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
import pers.lbf.yeju.service.interfaces.auth.enums.AccountOwnerTypeEnum;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.payment.IPaymentAdvanceService;
import pers.lbf.yeju.service.interfaces.payment.pojo.CreatePaymentAdvanceArgs;
import pers.lbf.yeju.service.interfaces.payment.status.PaymentStatusEnum;
import pers.lbf.yeju.service.interfaces.product.IHouseInfoService;
import pers.lbf.yeju.service.interfaces.product.IHouseRelationshipService;
import pers.lbf.yeju.service.interfaces.product.constants.RelationshipCustomerHouseTypeConstants;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseAboutTradeInfoBean;
import pers.lbf.yeju.service.interfaces.product.status.HouseStatusEnum;
import pers.lbf.yeju.service.interfaces.trade.ITradeService;
import pers.lbf.yeju.service.interfaces.trade.pojo.HouseTradeCreateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.HouseTradeUpdateArgs;
import pers.lbf.yeju.service.interfaces.trade.pojo.SimpleTradeInfoBean;
import pers.lbf.yeju.service.interfaces.trade.pojo.TradeQueryArgs;
import pers.lbf.yeju.service.interfaces.trade.status.TradeStatusConstants;

import java.math.BigDecimal;
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

    @DubboReference
    private IPaymentAdvanceService paymentAdvanceService;

    @DubboReference
    private IAccountService accountService;


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
    public IResult<String> createHouseTrade(HouseTradeCreateArgs args) throws ServiceException {

        IResult<HouseAboutTradeInfoBean> rpcResult = houseInfoService.findAboutTradeInfoById(args.getHouseId());
        if (!rpcResult.isSuccess()) {
            throw ServiceException.getInstance(TradeServiceStatusEnum.create_trade_fail_unkonw_house_status);
        }

        if (!HouseStatusEnum.tradable.getValue().equals(rpcResult.getData().getStatus())) {
            throw ServiceException.getInstance(TradeServiceStatusEnum.create_trade_fail_house_un_tradable);
        }

        // 加分布式锁
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(String.valueOf(args.getHouseId()), 1, Duration.ofHours(24));

        assert flag != null;
        if (!flag) {
            throw ServiceException.getInstance(TradeServiceStatusEnum.create_trade_fail_house_un_tradable);
        }

        //更新房源状态
        IResult<Boolean> changeHouseStatusRpcResult = houseInfoService.updateStatusById(args.getHouseId(), HouseStatusEnum.pre_transaction);
        if (!changeHouseStatusRpcResult.isSuccess()) {
            throw ServiceException.getInstance(TradeServiceStatusEnum.update_house_status_failed);
        }

        TradingInformationHouse trade = buildTrade(args, rpcResult.getData());

        tradeDao.insert(trade);


        //生成预支付单
        CreatePaymentAdvanceArgs createPaymentAdvanceArgs = buildCreateDomainPaymentArgs(trade);
        IResult<String> createPaymentAdvanceRpcResult = paymentAdvanceService.createPaymentAdvance(createPaymentAdvanceArgs);
        if (!createPaymentAdvanceRpcResult.isSuccess()) {
            throw ServiceException.getInstance(TradeServiceStatusEnum.create_domain_payment_failed);
        }


        return Result.ok(createPaymentAdvanceRpcResult.getData());
    }

    private CreatePaymentAdvanceArgs buildCreateDomainPaymentArgs(TradingInformationHouse trade) {
        CreatePaymentAdvanceArgs createPaymentAdvanceArgs = new CreatePaymentAdvanceArgs();
        createPaymentAdvanceArgs.setTradeId(trade.getTradeId());
        Long tenantAccountId = accountService.findAccountIdBySubjectIdAndAccountType(
                trade.getTenantId(), AccountOwnerTypeEnum.Customer_account).getData();
        createPaymentAdvanceArgs.setTransferOutAccountId(tenantAccountId);
        Long landlordAccountId = accountService.findAccountIdBySubjectIdAndAccountType(
                trade.getLandlordId(), AccountOwnerTypeEnum.Customer_account).getData();
        createPaymentAdvanceArgs.setTargetToAccountId(landlordAccountId);
        createPaymentAdvanceArgs.setFree(trade.getFree());
        createPaymentAdvanceArgs.setStatus(PaymentStatusEnum.Pending_payment.value);

        createPaymentAdvanceArgs.setCreateTime(trade.getCreateTime());
        return createPaymentAdvanceArgs;
    }

    private TradingInformationHouse buildTrade(HouseTradeCreateArgs args, HouseAboutTradeInfoBean houseTradeInfo) {
        TradingInformationHouse tradingInformationHouse = new TradingInformationHouse();
        tradingInformationHouse.setTenancy(args.getTenancy());
        Double free = calculateTheTotalPrice(args.getTenancy(), houseTradeInfo.getRent());
        tradingInformationHouse.setFree(free);
        tradingInformationHouse.setHouseRentUnitFree(houseTradeInfo.getRent().doubleValue());

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

    /**
     * 结算方法 抽取出来便以后续引入优惠策略
     *
     * @param tenancy
     * @param rent
     * @return
     */
    private Double calculateTheTotalPrice(Integer tenancy, BigDecimal rent) {
        return rent.doubleValue() * tenancy;
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