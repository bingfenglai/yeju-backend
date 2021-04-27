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

package pers.lbf.yeju.service.basedata.community.interfaces;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityCreateArgs;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityQueryArgs;
import pers.lbf.yeju.service.basedata.community.pojo.CommunityUpdateArgs;
import pers.lbf.yeju.service.basedata.community.pojo.SimpleCommunityInfoBean;

import java.util.List;
import java.util.Set;

/**
 * 社区服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/19 21:27
 */
public interface ICommunityService {

    /**
     * 综合分页查询接口
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    PageResult<SimpleCommunityInfoBean> query(CommunityQueryArgs args) throws ServiceException;

    /**
     * 添加一个社区
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> create(CommunityCreateArgs args) throws ServiceException;

    /**
     * 批量创建
     *
     * @param argsList
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> createBatch(List<CommunityCreateArgs> argsList) throws ServiceException;

    /**
     * 更新指定Id的社区信息
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> updateById(CommunityUpdateArgs args) throws ServiceException;

    /**
     * 移除指定id社区
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> removeById(Long id) throws ServiceException;


    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @throws ServiceException
     */
    IResult<Boolean> removeBatch(Set<Long> ids) throws ServiceException;

    /**
     * 根据小区id查找详细地址
     *
     * @param communityId
     * @throws ServiceException
     */
    IResult<String> findDetailsAddressById(Long communityId) throws ServiceException;
}
