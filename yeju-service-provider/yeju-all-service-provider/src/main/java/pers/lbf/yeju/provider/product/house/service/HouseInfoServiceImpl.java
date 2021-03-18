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
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.args.CreateArgs;
import pers.lbf.yeju.common.core.args.UpdateArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.domain.entity.business.*;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.product.house.constant.HouseStatusConstant;
import pers.lbf.yeju.provider.product.house.dao.HouseImagesAndVideoDao;
import pers.lbf.yeju.provider.product.house.dao.HouseInfoDao;
import pers.lbf.yeju.provider.product.house.dao.HouseInfoTradableDao;
import pers.lbf.yeju.provider.product.house.dao.HouseOtherAttributeDao;
import pers.lbf.yeju.service.interfaces.product.IHouseInfoService;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseDetailsInfoBean;
import pers.lbf.yeju.service.interfaces.product.pojo.SimpleHouseInfoBean;

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
        simpleHouseInfoBean.setRentalMode(houseInfo.getRentalMode());
        simpleHouseInfoBean.setHouseStatus(houseInfo.getHouseStatus());
        simpleHouseInfoBean.setCreateTime(houseInfo.getCreateTime());
        simpleHouseInfoBean.setPaymentMethod(houseInfo.getPaymentMethod());
        return simpleHouseInfoBean;
    }

    @Override
    public IResult<Boolean> addOne(CreateArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> update(UpdateArgs args) throws ServiceException {
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

    @Override
    public IResult<HouseDetailsInfoBean> findDetailsByIdAndStatus(Long id, String houseStatus) throws ServiceException {
        HouseDetailsInfoBean houseDetails = new HouseDetailsInfoBean();

        //查询基础信息
        if (HouseStatusConstant.auditFailed.equals(houseStatus) || HouseStatusConstant.pendingReview.equals(houseStatus)) {
            HouseInfo houseInfo = houseInfoDao.selectById(id);
            houseDetails = houseInfoToDetails(houseInfo);
        } else {
            HouseInfoTradable houseInfoTradable = houseInfoTradableDao.selectById(id);
            houseDetails = houseInfoToDetails(houseInfoTradable);
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


        return null;
    }

    private void houseImagesAndVideoToDetails(List<HouseImagesAndVideo> imagesAndVideo, HouseDetailsInfoBean houseDetails) {

    }

    private void houseOtherAttributeToDetails(HouseOtherAttribute attribute, HouseDetailsInfoBean houseDetails) {

        houseDetails.setHotWater(attribute.getIsHotWater() == 1);
        houseDetails.setHaveKitchen(attribute.getIsHaveKitchen() == 1);


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