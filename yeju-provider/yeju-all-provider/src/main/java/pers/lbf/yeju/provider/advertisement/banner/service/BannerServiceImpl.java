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
package pers.lbf.yeju.provider.advertisement.banner.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.business.Banner;
import pers.lbf.yeju.provider.advertisement.banner.dao.IBannerDao;
import pers.lbf.yeju.provider.advertisement.banner.status.BannerStatusEnum;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.service.interfaces.advertise.banner.interfaces.IBannerService;
import pers.lbf.yeju.service.interfaces.advertise.banner.pojo.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/15 19:54
 */
@DubboService(interfaceClass = IBannerService.class, timeout = 10000, retries = 0)
@Slf4j
public class BannerServiceImpl implements IBannerService {

    @Autowired
    private IBannerDao bannerDao;

    /**
     * 获取当前最新的轮播图
     *
     * @param size 个数
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<List<BannerInfoBean>> getLatest(Integer size) throws ServiceException {
        return null;
    }

    /**
     * 轮播图条件查询
     *
     * @param queryArgs 查询参数
     * @return pers.lbf.yeju.common.core.result.PageResult<java.beans.SimpleBeanInfo>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/4/15 19:55
     */
    @Override
    @Cacheable(cacheNames = "banner:page", keyGenerator = "yejuKeyGenerator")
    public PageResult<SimpleBannerInfoBean> queryPage(BannerQueryArgsBean queryArgs) throws ServiceException {
        Page<Banner> page = PageUtil.getPage(Banner.class, queryArgs.getCurrentPage(), queryArgs.getSize());
        QueryWrapper<Banner> queryWrapper = buildQueryWrapper(queryArgs);
        Page<Banner> bannerPage = bannerDao.selectPage(page, queryWrapper);
        List<SimpleBannerInfoBean> result = new LinkedList<>();
        for (Banner banner : bannerPage.getRecords()) {
            SimpleBannerInfoBean bean = bannerToSimpleInfoBean(banner);
            result.add(bean);
        }

        return PageResult.ok(bannerPage.getTotal(), bannerPage.getCurrent(), bannerPage.getSize(), result);

    }

    private SimpleBannerInfoBean bannerToSimpleInfoBean(Banner banner) {
        SimpleBannerInfoBean simpleBannerInfoBean = new SimpleBannerInfoBean();
        simpleBannerInfoBean.setSort(banner.getSort());
        simpleBannerInfoBean.setId(banner.getId());
        simpleBannerInfoBean.setImageUrl(banner.getImageUrl());
        simpleBannerInfoBean.setTitle(banner.getTitle());
        simpleBannerInfoBean.setBannerStatus(banner.getBannerStatus());
        simpleBannerInfoBean.setTargetUrl(banner.getTargetUrl());
        simpleBannerInfoBean.setCreateTime(banner.getCreateTime());
        simpleBannerInfoBean.setCreateBy(banner.getCreateBy());
        return simpleBannerInfoBean;

    }

    private QueryWrapper<Banner> buildQueryWrapper(BannerQueryArgsBean queryArgs) {
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();

        if (queryArgs.getBetween() != null) {
            Date[] between = queryArgs.getBetween();
            queryWrapper.between("create_time", between[0], between[1]);
        }

        if (queryArgs.getBannerStatus() != null) {
            queryWrapper.eq("banner_status", queryArgs.getBannerStatus());
        }

        if (queryArgs.getTitle() != null && !"".equals(queryArgs.getTitle())) {
            queryWrapper.eq("title", queryArgs.getTitle());
        }

        return queryWrapper;

    }


    /**
     * 保存轮播图方法
     *
     * @param bannerInfoBean 轮播图信息类
     * @return flag
     * @throws ServiceException e
     */
    @Override
    public IResult<Boolean> save(BannerInfoBean bannerInfoBean) throws ServiceException {
        return null;
    }

    /**
     * 创建方法
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @CacheEvict(cacheNames = "banner:page", allEntries = true)
    public IResult<Boolean> create(BannerCreateArgs args) throws ServiceException {
        Banner banner = createArgsToBanner(args);
        int count = bannerDao.insert(banner);
        if (count == 1) {
            return Result.success();
        }

        throw ServiceException.getInstance(BannerStatusEnum.CREATE_FAIL);

    }

    private Banner createArgsToBanner(BannerCreateArgs args) {
        Banner banner = new Banner();
        banner.setSort(args.getSort());
        banner.setImageUrl(args.getImageUrl());
        banner.setTitle(args.getTitle());
        banner.setBannerStatus(args.getBannerStatus());
        banner.setTargetUrl(args.getTargetUrl());
        banner.setCreateTime(args.getCreateTime());
        banner.setCreateBy(args.getCreateBy());

        banner.setRemark(args.getRemark());

        return banner;

    }

    @Override
    @CacheEvict(cacheNames = "banner:page", allEntries = true)
    public IResult<Boolean> updateById(BannerUpdateArgs args) throws ServiceException {
        Banner banner = updateArgsToBanner(args);
        int i = bannerDao.updateById(banner);
        if (i == 1) {
            return Result.success();
        }

        throw ServiceException.getInstance(BannerStatusEnum.UPDATE_FAIL);
    }

    private Banner updateArgsToBanner(BannerUpdateArgs args) {
        Banner banner = new Banner();
        banner.setSort(args.getSort());
        banner.setId(args.getId());
        banner.setImageUrl(args.getImageUrl());
        banner.setTitle(args.getTitle());
        banner.setBannerStatus(args.getBannerStatus());
        banner.setTargetUrl(args.getTargetUrl());
        banner.setCreateTime(args.getCreateTime());
        banner.setCreateBy(args.getCreateBy());
        banner.setUpdateTime(args.getUpdateTime());
        banner.setChangedBy(args.getChangedBy());
        banner.setRemark(args.getRemark());
        return banner;
    }


    /**
     * 批量删除
     *
     * @param bannerIdList 主键列表
     * @return flag
     * @throws ServiceException e
     */
    @Override
    @CacheEvict(cacheNames = "banner:page", allEntries = true)
    public IResult<Boolean> removeBatch(Set<Long> bannerIdList) throws ServiceException {
        bannerDao.deleteBatchIds(bannerIdList);
        return Result.success();
    }


    /**
     * 批量创建
     *
     * @param bannerCreateArgsList 批量创建参数
     * @return flag
     * @throws ServiceException e
     */
    @Override
    @CacheEvict(cacheNames = "banner:page", allEntries = true)
    public IResult<Boolean> createBatch(List<BannerCreateArgs> bannerCreateArgsList) throws ServiceException {
        for (BannerCreateArgs bannerCreateArgs : bannerCreateArgsList) {
            Banner banner = this.createArgsToBanner(bannerCreateArgs);
            bannerDao.insert(banner);
        }
        return Result.success();
    }
}