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
package pers.lbf.yeju.service.interfaces.message.notice;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.message.notice.pojo.NoticeDetailsBean;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeCreateArgs;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeMessage;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeUpdateArgs;
import pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean;

import java.util.List;

/**
 * 通知消息服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 19:54
 */
public interface INoticeService {

    /**
     * 通知 分页查询接口
     *
     * @param currentPage 当前页
     * @param size        每页大小
     * @return pers.lbf.yeju.common.core.result.PageResult<pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/3 22:43
     */
    PageResult<SimpleNoticeInfoBean> findPage(Long currentPage, Long size) throws ServiceException;

    /**
     * 查询所有有效的通知 （当前时间小于截止时间的通知）
     *
     * @return pers.lbf.yeju.common.core.result.Result<java.util.List < pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean>>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/3 22:44
     */
    @Deprecated
    IResult<List<SimpleNoticeInfoBean>> findEffectiveNoticeList() throws ServiceException;

    IResult<List<String>> findEffectiveNoticeList(String principal) throws ServiceException;


    IResult<Object> create(NoticeCreateArgs args) throws Exception;

    IResult<Object> update(NoticeUpdateArgs args) throws ServiceException;

    /**
     * 推送消息
     *
     * @param noticeMessage 通知消息参数
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Object>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/6 0:53
     */
    IResult<Object> send(NoticeMessage noticeMessage) throws ServiceException;


    /**
     * 批量删除通知
     *
     * @param idList ids
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/10 11:23
     */
    IResult<Boolean> deleteBatch(String[] idList) throws ServiceException;

    /**
     * TODO
     *
     * @param id
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @throws ServiceException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/10 14:59
     */
    IResult<Boolean> deleteById(String id) throws ServiceException;

    IResult<NoticeDetailsBean> findDetailsById(String id) throws ServiceException;
}
