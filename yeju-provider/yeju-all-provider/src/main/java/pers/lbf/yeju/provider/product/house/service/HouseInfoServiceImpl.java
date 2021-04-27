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
package pers.lbf.yeju.provider.product.house.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import pers.lbf.yeju.common.core.args.ICreateArgs;
import pers.lbf.yeju.common.core.args.IUpdateArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.business.product.house.*;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.product.house.constant.HouseConstant;
import pers.lbf.yeju.provider.product.house.constant.HouseStatusConstant;
import pers.lbf.yeju.provider.product.house.dao.HouseImagesAndVideoDao;
import pers.lbf.yeju.provider.product.house.dao.HouseInfoDao;
import pers.lbf.yeju.provider.product.house.dao.HouseInfoTradableDao;
import pers.lbf.yeju.provider.product.house.dao.HouseOtherAttributeDao;
import pers.lbf.yeju.provider.product.house.repository.HouseInfoElasticsearchRepository;
import pers.lbf.yeju.service.basedata.community.interfaces.ICommunityService;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.product.IHouseInfoService;
import pers.lbf.yeju.service.interfaces.product.pojo.*;
import pers.lbf.yeju.service.interfaces.product.status.HouseStatusEnum;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 房源信息服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/17 15:37
 */
@DubboService(interfaceClass = IHouseInfoService.class)
@Slf4j
public class HouseInfoServiceImpl implements IHouseInfoService {

    @Autowired
    private HouseInfoDao houseInfoDao;

    @Autowired
    private HouseInfoTradableDao houseInfoTradableDao;

    @Autowired
    private HouseOtherAttributeDao houseOtherAttributeDao;

    @Autowired
    private HouseImagesAndVideoDao houseImagesAndVideoDao;

    @Autowired
    private IDataDictionaryInfoService dataDictionaryInfoService;

    @Autowired
    private HouseInfoElasticsearchRepository houseInfoElasticsearchRepository;

    @DubboReference
    private ICommunityService communityService;

    /**
     * 房源搜索接口
     *
     * @param searchArgs
     * @return
     * @throws ServiceException
     */
    @Override
    public PageResult<HouseInfoDoc> search(HouseSearchArgs searchArgs) throws ServiceException {
        int page = Math.toIntExact(searchArgs.getCurrentPage());
        int size = Math.toIntExact(searchArgs.getSize());
        PageRequest pageRequest = PageRequest.of(page, size);
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(
                searchArgs.getKeyWord(),
                "title",
                "paymentMethod",
                "rentalMode",
                "houseType",
                "houseDecorationType");

        org.springframework.data.domain.Page<HouseInfoDoc> search = houseInfoElasticsearchRepository.search(queryBuilder, pageRequest);
        List<HouseInfoDoc> content = search.getContent();
        log.info(content.toString());
        return PageResult.ok(search.getTotalElements(), (long) page, (long) size, content);

    }

    @Override
    public IResult<Boolean> removeByIdFromES(String id) throws ServiceException {
        if (houseInfoElasticsearchRepository.existsById(id)) {
            houseInfoElasticsearchRepository.deleteById(id);
        }
        return Result.success();
    }

    /**
     * 拷贝 房源信息进可交易表、es数据库
     *
     * @param houseId
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<Boolean> copyHouseInfoToTradable(Long houseId) throws ServiceException {

        HouseInfo houseInfo = houseInfoDao.selectById(houseId);
        HouseInfoTradable houseInfoTradable = houseInfoToTradable(houseInfo);

        houseInfoTradableDao.insert(houseInfoTradable);
        HouseInfoDoc houseInfoDoc = buildHouseInfoDoc(houseInfo);
        houseInfoElasticsearchRepository.save(houseInfoDoc);
        return Result.success();
    }

    private HouseInfoDoc buildHouseInfoDoc(HouseInfo houseInfo) {
        HouseInfoDoc houseInfoDoc = new HouseInfoDoc();
        houseInfoDoc.setHouseId(houseInfo.getHouseId());
        houseInfoDoc.setTitle(houseInfo.getTitle());
        houseInfoDoc.setRent(houseInfo.getRent());

        houseInfoDoc.setHouseType(dataConversion(HouseConstant.HOUSE_TYPE, houseInfo.getHouseType()));
        houseInfoDoc.setCoveredArea(houseInfo.getCoveredArea());
        houseInfoDoc.setHouseOrientation(dataConversion(HouseConstant.HOUSE_ORIENTATION, houseInfo.getHouseOrientation()));
        houseInfoDoc.setHouseDecorationType(dataConversion(HouseConstant.HOUSE_DECORATION_TYPE, houseInfo.getHouseDecorationType()));
        houseInfoDoc.setHouseImagesAddress(houseInfo.getHouseImagesAddress());
        houseInfoDoc.setRentUnit(dataConversion(HouseConstant.RENT_UNIT, houseInfo.getRentUnit()));
        houseInfoDoc.setRentalMode(dataConversion(HouseConstant.RENTAL_MODE, houseInfo.getRentalMode()));
        houseInfoDoc.setPaymentMethod(dataConversion(HouseConstant.PAYMENT_METHOD, houseInfo.getPaymentMethod()));
        houseInfoDoc.setCreateTime(houseInfo.getCreateTime());
        if (houseInfo.getCommunityId() != null) {
            String address = communityService.findDetailsAddressById(houseInfo.getCommunityId()).getData();
            houseInfoDoc.setDetailsAddress(address);
        }

        return houseInfoDoc;
    }

    private String dataConversion(String name, String value) {
        return dataDictionaryInfoService.getDictMap(name).getData().get(value);
    }

    private HouseInfoTradable houseInfoToTradable(HouseInfo houseInfo) {
        HouseInfoTradable houseInfoTradable = new HouseInfoTradable();
        houseInfoTradable.setHouseId(houseInfo.getHouseId());
        houseInfoTradable.setOwnerId(houseInfo.getOwnerId());
        houseInfoTradable.setTitle(houseInfo.getTitle());
        houseInfoTradable.setCommunityId(houseInfo.getCommunityId());
        houseInfoTradable.setBuildingNumber(houseInfo.getBuildingNumber());
        houseInfoTradable.setBuildingUint(houseInfo.getBuildingUint());
        houseInfoTradable.setBuildingFloorNumber(houseInfo.getBuildingFloorNumber());
        houseInfoTradable.setRent(houseInfo.getRent());
        houseInfoTradable.setRentalMode(houseInfo.getRentalMode());
        houseInfoTradable.setPaymentMethod(houseInfo.getPaymentMethod());
        houseInfoTradable.setHouseType(houseInfo.getHouseType());
        houseInfoTradable.setCoveredArea(houseInfo.getCoveredArea());
        houseInfoTradable.setUseArea(houseInfo.getUseArea());
        houseInfoTradable.setFloors(houseInfo.getFloors());
        houseInfoTradable.setHouseOrientation(houseInfo.getHouseOrientation());
        houseInfoTradable.setHouseDecorationType(houseInfo.getHouseDecorationType());
        houseInfoTradable.setHouseFacilities(houseInfo.getHouseFacilities());
        houseInfoTradable.setDescs(houseInfo.getDescs());
        houseInfoTradable.setHouseStatus(houseInfo.getHouseStatus());
        houseInfoTradable.setHouseImagesAddress(houseInfo.getHouseImagesAddress());
        houseInfoTradable.setCreateTime(houseInfo.getCreateTime());
        houseInfoTradable.setCreateBy(houseInfo.getCreateBy());
        houseInfoTradable.setUpdateTime(new Date());
        houseInfoTradable.setChangedBy(houseInfo.getChangedBy());
        houseInfoTradable.setVersionNumber(houseInfo.getVersionNumber());
        houseInfoTradable.setIsDelete(houseInfo.getIsDelete());
        houseInfoTradable.setMonthAdded(houseInfo.getMonthAdded());
        houseInfoTradable.setMonthCompleted(houseInfo.getMonthCompleted());
        return houseInfoTradable;
    }

    /**
     * 房源综合查询接口
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @Cacheable(cacheNames = "house:query", key = "#args")
    public PageResult<SimpleHouseInfoBean> query(HouseInfoQueryArgs args) throws ServiceException {
        Page<HouseInfo> page = PageUtil.getPage(HouseInfo.class, args.getCurrentPage(), args.getSize());
        QueryWrapper<HouseInfo> queryWrapper = buildQueryWrapper(args);
        Page<HouseInfo> houseInfoPage = houseInfoDao.selectPage(page, queryWrapper);
        List<SimpleHouseInfoBean> result = new LinkedList<>();
        for (HouseInfo houseInfo : houseInfoPage.getRecords()) {
            SimpleHouseInfoBean bean = houseInfoToBean(houseInfo);
            result.add(bean);
        }
        return PageResult.ok(houseInfoPage.getTotal(), houseInfoPage.getCurrent(), houseInfoPage.getSize(), result);
    }

    private QueryWrapper<HouseInfo> buildQueryWrapper(HouseInfoQueryArgs args) {
        QueryWrapper<HouseInfo> queryWrapper = new QueryWrapper<>();
        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getHouseDecorationType())) {
            queryWrapper.eq("house_decoration_type", args.getHouseDecorationType());
        }
        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getHouseStatus())) {
            queryWrapper.eq("house_status", args.getHouseStatus());
        }

        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getHouseOrientation())) {
            queryWrapper.eq("house_orientation", args.getHouseOrientation());
        }

        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getPaymentMethod())) {
            queryWrapper.eq("payment_method", args.getPaymentMethod());
        }

        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getRentalMode())) {
            queryWrapper.eq("rental_mode", args.getRentalMode());
        }

        if (YejuStringUtils.isNotEmpty(args.getDateRange()) && args.getDateRange().length == 2) {
            String[] dateRange = args.getDateRange();
            queryWrapper.between("create_time", dateRange[0], dateRange[1]);
        }

        return queryWrapper;
    }

    @Override
    @Cacheable(cacheNames = "house", keyGenerator = "yejuKeyGenerator")
    public PageResult<SimpleHouseInfoBean> findPage(Long currentPage, Long size) throws ServiceException {
        Page<HouseInfo> page = PageUtil.getPage(HouseInfo.class, currentPage, size);
        Page<HouseInfo> houseInfoPage = houseInfoDao.selectPage(page, null);
        List<SimpleHouseInfoBean> result = new LinkedList<>();
        for (HouseInfo houseInfo : houseInfoPage.getRecords()) {
            SimpleHouseInfoBean bean = houseInfoToBean(houseInfo);
            result.add(bean);
        }
        return PageResult.ok(houseInfoPage.getTotal(), currentPage, size, result);
    }

    private SimpleHouseInfoBean houseInfoToBean(HouseInfo houseInfo) {
        SimpleHouseInfoBean simpleHouseInfoBean = new SimpleHouseInfoBean();
        simpleHouseInfoBean.setHouseId(houseInfo.getHouseId());
        simpleHouseInfoBean.setTitle(houseInfo.getTitle());
        simpleHouseInfoBean.setRent(houseInfo.getRent());
        simpleHouseInfoBean.setRentUnit(houseInfo.getRentUnit());
        simpleHouseInfoBean.setRentalMode(houseInfo.getRentalMode());
        simpleHouseInfoBean.setHouseStatus(houseInfo.getHouseStatus());
        simpleHouseInfoBean.setCreateTime(houseInfo.getCreateTime());
        simpleHouseInfoBean.setPaymentMethod(houseInfo.getPaymentMethod());

        return simpleHouseInfoBean;
    }

    @Override
    public IResult<Boolean> addOne(ICreateArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> update(IUpdateArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> deleteBatch(String[] ids) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> deleteById(Long id) throws ServiceException {
        return null;
    }

    @Cacheable(cacheNames = "house", keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<HouseDetailsInfoBean> findDetailsByIdAndStatus(Long id, String houseStatus) throws ServiceException {
        HouseDetailsInfoBean houseDetails = new HouseDetailsInfoBean();

        //查询基础信息
        if (HouseStatusConstant.auditFailed.equals(houseStatus) || HouseStatusConstant.pendingReview.equals(houseStatus)) {
            HouseInfo houseInfo = houseInfoDao.selectById(id);
            if (houseInfo != null) {
                houseDetails = houseInfoToDetails(houseInfo);
            }
        } else {
            HouseInfoTradable houseInfoTradable = houseInfoTradableDao.selectById(id);
            if (houseInfoTradable != null) {
                houseDetails = houseInfoToDetails(houseInfoTradable);
            }
        }

        // 查询其他信息

        HouseOtherAttribute houseOtherAttribute = houseOtherAttributeDao.selectById(id);

        houseOtherAttributeToDetails(houseOtherAttribute, houseDetails);

        //查询图片、视频地址
        QueryWrapper<HouseImagesAndVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("house_id", id);
        queryWrapper.select("file_name", "file_url", "resource_id", "resource_status", "resource_type");
        List<HouseImagesAndVideo> houseImagesAndVideoList = houseImagesAndVideoDao.selectList(queryWrapper);
        houseImagesAndVideoToDetails(houseImagesAndVideoList, houseDetails);


        return Result.ok(houseDetails);
    }

    @Override
    public IResult<HouseStatusEnum> getHouseStatusEnumById(Long id) throws ServiceException {

        return null;
    }

    /**
     * 房源信息审核操作 更改状态
     *
     * @param id          房源标识
     * @param houseStatus 房源状态
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/20 15:43
     */
    @Override
    public IResult<Boolean> verifyById(Long id, String houseStatus) throws ServiceException {
        int i = houseInfoDao.updateHouseStatusById(id, houseStatus);
        return Result.ok(i == 1);
    }

    private void houseImagesAndVideoToDetails(List<HouseImagesAndVideo> imagesAndVideoList, HouseDetailsInfoBean houseDetails) {
        for (HouseImagesAndVideo houseImagesAndVideo : imagesAndVideoList) {
            HouseResourceInfoBean bean = houseImagesAndVideoToInfoBean(houseImagesAndVideo);
            houseDetails.addResource(bean);
        }
    }

    private HouseResourceInfoBean houseImagesAndVideoToInfoBean(HouseImagesAndVideo houseImagesAndVideo) {
        HouseResourceInfoBean houseResourceInfoBean = new HouseResourceInfoBean();
        houseResourceInfoBean.setHouseId(houseImagesAndVideo.getHouseId());
        houseResourceInfoBean.setFileName(houseImagesAndVideo.getFileName());
        houseResourceInfoBean.setFileUrl(houseImagesAndVideo.getFileUrl());
        houseResourceInfoBean.setResourceStatus(houseImagesAndVideo.getResourceStatus());
        houseResourceInfoBean.setResourceType(houseImagesAndVideo.getResourceType());
        houseResourceInfoBean.setResourceId(houseImagesAndVideo.getResourceId());
        return houseResourceInfoBean;
    }

    private void houseOtherAttributeToDetails(HouseOtherAttribute attribute, HouseDetailsInfoBean houseDetails) {

        houseDetails.setHotWater(attribute.getIsHotWater() == 1);
        houseDetails.setHaveKitchen(attribute.getIsHaveKitchen() == 1);
        houseDetails.setHaveLivingRoot(attribute.getIsHaveLivingRoot() == 1);
        houseDetails.setHaveBalcony(attribute.getIsHaveBalcony() == 1);
        houseDetails.setHaveWindow(attribute.getIsHaveWindow() == 1);
        houseDetails.setSeparateToilet(attribute.getIsSeparateToilet() == 1);
        houseDetails.setCheckInWithBags(attribute.getIsCheckInWithBags() == 1);
        houseDetails.setHaveElevator(attribute.getIsHaveElevator() == 1);
        houseDetails.setParkingSpace(attribute.getIsThereAParkingSpace() == 1);
        houseDetails.setElectricityPrice(attribute.getElectricityPrice());
        houseDetails.setWaterPrice(attribute.getWaterPrice());

    }

    private HouseDetailsInfoBean houseInfoToDetails(IHouseInfo houseInfo) {
        HouseDetailsInfoBean houseDetailsInfoBean = new HouseDetailsInfoBean();
        houseDetailsInfoBean.setHouseId(houseInfo.getHouseId());
        houseDetailsInfoBean.setOwnerId(houseInfo.getOwnerId());

        houseDetailsInfoBean.setTitle(houseInfo.getTitle());
        houseDetailsInfoBean.setCommunityId(houseInfo.getCommunityId());

        houseDetailsInfoBean.setBuildingNumber(houseInfo.getBuildingNumber());
        houseDetailsInfoBean.setBuildingUint(houseInfo.getBuildingUint());
        houseDetailsInfoBean.setBuildingFloorNumber(houseInfo.getBuildingFloorNumber());

        houseDetailsInfoBean.setRent(houseInfo.getRent());
        houseDetailsInfoBean.setRentalMode(houseInfo.getRentalMode());
        houseDetailsInfoBean.setPaymentMethod(houseInfo.getPaymentMethod());
        houseDetailsInfoBean.setHouseType(houseInfo.getHouseType());
        houseDetailsInfoBean.setCoveredArea(houseInfo.getCoveredArea());
        houseDetailsInfoBean.setUseArea(houseInfo.getUseArea());
        houseDetailsInfoBean.setFloors(houseInfo.getFloors());
        houseDetailsInfoBean.setHouseOrientation(houseInfo.getHouseOrientation());
        houseDetailsInfoBean.setHouseDecorationType(houseInfo.getHouseDecorationType());
        houseDetailsInfoBean.setHouseFacilities(houseInfo.getHouseFacilities());
        houseDetailsInfoBean.setDescs(houseInfo.getDescs());
        houseDetailsInfoBean.setHouseStatus(houseInfo.getHouseStatus());
        houseDetailsInfoBean.setHouseImagesAddress(houseInfo.getHouseImagesAddress());
        houseDetailsInfoBean.setCreateTime(houseInfo.getCreateTime());
        return houseDetailsInfoBean;
    }


}