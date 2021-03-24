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
package pers.lbf.yeju.provider.message.notice.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import pers.lbf.yeju.common.core.constant.StatusConstants;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.domain.entity.Notice;
import pers.lbf.yeju.common.util.DateUtils;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.message.notice.NoticeCacheKeyManager;
import pers.lbf.yeju.provider.message.notice.dao.INoticeDao;
import pers.lbf.yeju.provider.message.notice.sender.NoticeSender;
import pers.lbf.yeju.service.interfaces.message.IGroupMessageService;
import pers.lbf.yeju.service.interfaces.message.IMessageGroupService;
import pers.lbf.yeju.service.interfaces.message.notice.INoticeService;
import pers.lbf.yeju.service.interfaces.message.notice.pojo.NoticeDetailsBean;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeCreateArgs;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeMessage;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeUpdateArgs;
import pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean;
import pers.lbf.yeju.service.interfaces.platfrom.employee.IEmployeeService;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;

import java.util.*;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 20:15
 */
@DubboService(interfaceClass = INoticeService.class, timeout = 8000)
@Slf4j
@Transactional(rollbackFor = Exception.class)
@EnableAsync
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private INoticeDao noticeDao;

    @DubboReference
    private IEmployeeService employeeService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private NoticeSender noticeSender;

    @DubboReference
    private IMessageGroupService messageGroupService;

    @DubboReference
    private IGroupMessageService groupMessageService;

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
    @Cacheable(cacheNames = "noticeService:findPage", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleNoticeInfoBean> findPage(Long currentPage, Long size) throws ServiceException {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("notice_id", "title", "notice_type", "status", "create_time", "create_by");

        Page<Notice> page = PageUtil.getPage(Notice.class, currentPage, size);
        Page<Notice> noticePage = noticeDao.selectPage(page, queryWrapper);
        List<Notice> noticeList = noticePage.getRecords();
        List<SimpleNoticeInfoBean> result = new LinkedList<>();

        for (Notice notice : noticeList) {
            SimpleNoticeInfoBean bean = this.noticeToSimpleInfoBean(notice);
            result.add(bean);
        }
        return PageResult.ok(noticePage.getTotal(), currentPage, size, result);
    }


    /**
     * 查询所有有效的通知 （当前时间小于截止时间的通知）
     *
     * @return pers.lbf.yeju.common.core.result.Result<java.util.List < pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean>>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/3 22:44
     */
    @Cacheable(cacheNames = "noticeService:effectiveNoticeList", keyGenerator = "yejuKeyGenerator")
    @Override
    public IResult<List<SimpleNoticeInfoBean>> findEffectiveNoticeList() throws ServiceException {

        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        Date date = new Date();
        queryWrapper.eq("status", 1);
        queryWrapper.le("start_time", date);
        queryWrapper.gt("end_time", date);
        queryWrapper.select("title", "content", "notice_type");
        queryWrapper.orderByDesc("create_time");
        List<Notice> notices = noticeDao.selectList(queryWrapper);
        List<SimpleNoticeInfoBean> result = new LinkedList<>();
        for (Notice notice : notices) {
            SimpleNoticeInfoBean bean = new SimpleNoticeInfoBean();
            bean.setTitle(notice.getTitle());
            bean.setContent(notice.getContent());
            bean.setNoticeType(notice.getNoticeType());
            result.add(bean);
        }
        return Result.ok(result);
    }


    /**
     * 用户上线读取系统通知方法
     *
     * @param principal
     * @return pers.lbf.yeju.common.core.result.Result<java.util.List < java.lang.String>>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/7 15:22
     */
    @Override
    public IResult<List<String>> findEffectiveNoticeList(String principal) throws ServiceException {

        List<Long> groupIds = messageGroupService.findSystemMessageGroupByPrincipal(principal).getData();

        List<String> result = new LinkedList<>();

        for (Long groupId : groupIds) {

            String pattern = NoticeCacheKeyManager.generateNoticeKeyAllMessagePattern(groupId);
            log.info("表达式： {}", pattern);
            Collection<String> keys = redisService.keys(pattern);
            log.info("消息键---> {} {}", keys.size(), Arrays.toString(keys.toArray()));

            for (String key : keys) {
                String msg = (String) redisService.getCacheObject(key);
                JSONObject jsonObject = JSONObject.parseObject(msg);
                Long messageId = (Long) jsonObject.get("messageId");
                Long now = System.currentTimeMillis();
                Long sendDate = (Long) jsonObject.get("sendDate");
                Long endDate = (Long) jsonObject.get("endDate");
                log.info("消息id {}", messageId);
                if (endDate != null) {
                    if (sendDate <= now && now <= endDate) {
                        Boolean flag = groupMessageService.isDeliveredSuccessfully(principal, messageId).getData();
                        if (!flag) {
                            result.add(msg);
                        }
                    }
                } else {
                    if (sendDate <= now) {
                        Boolean flag = groupMessageService.isDeliveredSuccessfully(principal, messageId).getData();
                        if (!flag) {
                            result.add(msg);
                        }
                    }
                }

            }
        }

        return Result.ok(result);
    }


    /**
     * 创建一个新的通知
     * 创建后会清除该缓存的所有数据
     *
     * @param args 通知创建参数
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Object>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/4 23:35
     */
    @CacheEvict(cacheNames = "noticeService:findPage", allEntries = true)
    @Override
    public IResult<Object> create(NoticeCreateArgs args) throws Exception {

        Notice notice = noticeCreateArgsToNotice(args);
        if (new Date().before(notice.getEndTime())) {
            notice.setStatus(StatusConstants.ABLE);
        } else {
            notice.setStatus(StatusConstants.DISABLE);
        }

        log.debug("准备将消息发送至消息队列");
        noticeDao.insert(notice);
        push(notice);

        log.debug("开始将通知存入数据库");

        return SimpleResult.ok();
    }

    @Override
    @CacheEvict(cacheNames = "noticeService:findPage", allEntries = true)
    public IResult<Object> update(NoticeUpdateArgs args) throws ServiceException {
        Notice notice = noticeUpdateArgsToNotice(args);
        if (new Date().before(notice.getEndTime())) {
            notice.setStatus(StatusConstants.ABLE);
        } else {
            notice.setStatus(StatusConstants.DISABLE);
        }

        noticeDao.updateById(notice);
        return SimpleResult.ok();
    }


    /**
     * 推送消息
     *
     * @param noticeMessage
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Object>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/6 0:53
     */
    @Override
    public IResult<Object> send(NoticeMessage noticeMessage) throws ServiceException {

        return null;
    }

    /**
     * 批量删除通知
     *
     * @param idList ids
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/10 11:23
     */
    @CacheEvict(cacheNames = "noticeService:findPage", allEntries = true)
    @Override
    public IResult<Boolean> deleteBatch(String[] idList) throws ServiceException {
        List<Long> ids = new LinkedList<>();
        for (String s : idList) {
            String redisKey = NoticeCacheKeyManager.generateAllNoticeKeyPattern(s);
            log.info("清理redis缓存key {}", redisKey);
            Collection<String> keys = redisService.keys(redisKey);
            for (String key : keys) {
                redisService.deleteObject(key);
            }

            ids.add(Long.valueOf(s));
        }
        // 清理数据库
        noticeDao.deleteBatchIds(ids);
        return Result.ok(true);
    }

    /**
     * TODO
     *
     * @param id
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Boolean>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/10 14:59
     */
    @CacheEvict(cacheNames = "noticeService:findPage", allEntries = true)
    @Override
    public IResult<Boolean> deleteById(String id) throws ServiceException {

        int i = noticeDao.deleteById(Long.valueOf(id));
        redisService.deleteObject(NoticeCacheKeyManager.generateAllNoticeKeyPattern(id));
        log.info("删除行数 {}", i);
        return Result.ok(true);
    }

    @Cacheable(cacheNames = "noticeService:details", key = "#id")
    @Override
    public IResult<NoticeDetailsBean> findDetailsById(String id) throws ServiceException {
        Notice notice = noticeDao.selectById(Long.valueOf(id));
        NoticeDetailsBean bean = noticeToDetailsBean(notice);

        return Result.ok(bean);
    }

    private NoticeDetailsBean noticeToDetailsBean(Notice notice) {
        NoticeDetailsBean noticeDetailsBean = new NoticeDetailsBean();
        noticeDetailsBean.setNoticeId(notice.getNoticeId());
        noticeDetailsBean.setTitle(notice.getTitle());
        noticeDetailsBean.setContent(notice.getContent());
        noticeDetailsBean.setNoticeType(notice.getNoticeType());
        noticeDetailsBean.setStatus(notice.getStatus());
        noticeDetailsBean.setSendTo(notice.getSendTo());
        noticeDetailsBean.setReceiverType(notice.getReceiverType());
        String[] dateRange = new String[2];
        dateRange[0] = DateUtils.dateToString(notice.getStartTime());
        dateRange[1] = DateUtils.dateToString(notice.getEndTime());
        noticeDetailsBean.setDateRange(dateRange);
        return noticeDetailsBean;
    }

    @Async
    void push(Notice notice) {
        Date now = new Date();
        NoticeMessage message = noticeToMsgVO(notice);
        log.info("准备将消息写入缓存 {}", message.toString());
        String redisKey = NoticeCacheKeyManager.generateNoticeKey(message.getMessageId(), message.getSendTo());

        String msgJson = JSONObject.toJSONString(message);
        redisService.addCacheObject(redisKey, msgJson);

        if (notice.getStartTime().before(now) && notice.getEndTime().after(now)) {

            noticeSender.send(message, null);
            log.debug("通知消息推送成功");
        } else {
            log.debug("通知开始时间是一个未来的时间，取消推送");
        }

    }

    private NoticeMessage noticeToMsgVO(Notice notice) {
        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setDate(notice.getStartTime());
        noticeMessage.setSendTo(notice.getSendTo());
        noticeMessage.setType(notice.getNoticeType());
        noticeMessage.setTitle(notice.getTitle());
        noticeMessage.setMessage(notice.getContent());
        noticeMessage.setMessageId(notice.getNoticeId());
        noticeMessage.setMessageType(notice.getReceiverType());
        return noticeMessage;
    }


    private Notice noticeCreateArgsToNotice(NoticeCreateArgs args) {
        Notice notice = new Notice();

        notice.setTitle(args.getNoticeTitle());
        notice.setContent(args.getContent());
        notice.setNoticeType(args.getNoticeType());
        notice.setStatus(args.getStatus());
        notice.setStartTime(args.getStartTime());
        notice.setEndTime(args.getEndTime());
        notice.setCreateTime(args.getCreateTime());
        notice.setCreateBy(args.getCreateBy());

        notice.setRemark(args.getRemark());

        notice.setBeFrom(args.getBeFrom());
        notice.setSendTo(args.getSendTo());
        notice.setReceiverType(args.getReceiverType());
        return notice;
    }


    private Notice noticeUpdateArgsToNotice(NoticeUpdateArgs args) {
        Notice notice = new Notice();
        notice.setNoticeId(args.getNoticeId());
        notice.setTitle(args.getNoticeTitle());
        notice.setContent(args.getContent());
        notice.setNoticeType(args.getNoticeType());
        notice.setStatus(args.getStatus());
        notice.setStartTime(args.getStartTime());
        notice.setEndTime(args.getEndTime());
        notice.setCreateTime(args.getCreateTime());
        notice.setCreateBy(args.getCreateBy());
        notice.setUpdateTime(args.getUpdateTime());
        notice.setChangedBy(args.getChangedBy());
        notice.setRemark(args.getRemark());
        notice.setBeFrom(args.getBeFrom());
        notice.setSendTo(args.getSendTo());
        return notice;
    }

    private SimpleNoticeInfoBean noticeToSimpleInfoBean(Notice notice) {
        SimpleNoticeInfoBean simpleNoticeInfoBean = new SimpleNoticeInfoBean();
        simpleNoticeInfoBean.setNoticeId(notice.getNoticeId());
        simpleNoticeInfoBean.setTitle(notice.getTitle());
        simpleNoticeInfoBean.setContent(notice.getContent());
        simpleNoticeInfoBean.setNoticeType(notice.getNoticeType());
        simpleNoticeInfoBean.setStatus(notice.getStatus());
        simpleNoticeInfoBean.setStartTime(notice.getStartTime());
        simpleNoticeInfoBean.setEndTime(notice.getEndTime());
        simpleNoticeInfoBean.setCreateTime(notice.getCreateTime());
        simpleNoticeInfoBean.setCreateBy(notice.getCreateBy());
        if (notice.getCreateBy() != null) {
            String name = employeeService.findNameByAccount(notice.getCreateBy()).getData();
            simpleNoticeInfoBean.setCreateByStr(name);
            log.info("name {}", name);
        }
        return simpleNoticeInfoBean;

    }
}