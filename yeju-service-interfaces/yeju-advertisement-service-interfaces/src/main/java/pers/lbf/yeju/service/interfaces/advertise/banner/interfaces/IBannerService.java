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

package pers.lbf.yeju.service.interfaces.advertise.banner.interfaces;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.advertise.banner.pojo.*;

import java.util.List;
import java.util.Set;

/**
 * 轮播图服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/15 19:41
 */
public interface IBannerService {

    /**
     * 获取当前最新的轮播图
     *
     * @param size 个数
     * @return
     * @throws ServiceException
     */
    IResult<List<BannerInfoBean>> getLatest(Integer size) throws ServiceException;

    /**
     * 轮播图条件查询
     *
     * @param queryArgs 查询参数
     * @return pers.lbf.yeju.common.core.result.PageResult<java.beans.SimpleBeanInfo>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/4/15 19:55
     */
    PageResult<SimpleBannerInfoBean> queryPage(BannerQueryArgsBean queryArgs) throws ServiceException;

    /**
     * 保存轮播图方法
     *
     * @param bannerInfoBean 轮播图信息类
     * @return flag
     * @throws ServiceException e
     */
    IResult<Boolean> save(BannerInfoBean bannerInfoBean) throws ServiceException;

    /**
     * 创建方法
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> create(BannerCreateArgs args) throws ServiceException;

    IResult<Boolean> updateById(BannerUpdateArgs args) throws ServiceException;

    /**
     * 批量删除
     *
     * @param bannerIdList 主键列表
     * @return flag
     * @throws ServiceException e
     */
    IResult<Boolean> removeBatch(Set<Long> bannerIdList) throws ServiceException;

    /**
     * 批量创建
     *
     * @param bannerCreateArgsList 批量创建参数
     * @return flag
     * @throws ServiceException e
     */
    IResult<Boolean> createBatch(List<BannerCreateArgs> bannerCreateArgsList) throws ServiceException;
}
