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
import pers.lbf.yeju.common.core.constant.StatusConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.domain.entity.Notice;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.message.notice.dao.INoticeDao;
import pers.lbf.yeju.provider.message.notice.sender.NoticeSender;
import pers.lbf.yeju.service.interfaces.message.INoticeService;
import pers.lbf.yeju.service.interfaces.message.pojo.*;
import pers.lbf.yeju.service.interfaces.platfrom.employee.IEmployeeService;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 20:15
 */
@DubboService(interfaceClass = INoticeService.class)
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
    public Result<List<SimpleNoticeInfoBean>> findEffectiveNoticeList() throws ServiceException {

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

    @Cacheable(cacheNames = "noticeService:effectiveNoticeList", key = "#principal")
    @Override
    public Result<List<SimpleNoticeInfoBean>> findEffectiveNoticeList(String principal) throws ServiceException {

        return null;
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
    @CacheEvict(cacheNames = {
            "noticeService:effectiveNoticeList",
            "noticeService:findPage"},
            allEntries = true)
    @Override
    public IResult<Object> create(NoticeCreateArgs args) throws Exception {

        Notice notice = noticeCreateArgsToNotice(args);
        if (new Date().before(notice.getEndTime())) {
            notice.setStatus(StatusConstant.ABLE);
        } else {
            notice.setStatus(StatusConstant.DISABLE);
        }
        log.debug("开始将通知存入数据库");
        noticeDao.insert(notice);
        log.debug("准备将消息发送至消息队列");
        push(notice);
        return SimpleResult.ok();
    }

    @Async
    void push(Notice notice) {
        Date now = new Date();
        if (notice.getStartTime().before(now) && notice.getEndTime().after(now)) {
            NoticeMessageVO messageVO = noticeToMsgVO(notice);
            redisService.addCacheObject(NoticeConstant.REDIS_KEY_PREFIX + messageVO.getSendTo(), messageVO);
            noticeSender.send(messageVO, null);
            log.debug("通知消息推送成功");
        } else {
            log.debug("通知开始时间是一个未来的时间，取消推送");
        }
    }

    private NoticeMessageVO noticeToMsgVO(Notice notice) {
        NoticeMessageVO noticeMessageVO = new NoticeMessageVO();
        noticeMessageVO.setDate(notice.getStartTime());
        noticeMessageVO.setSendTo(notice.getSendTo());
        noticeMessageVO.setType(notice.getReceiverType());
        noticeMessageVO.setTitle(notice.getTitle());
        noticeMessageVO.setMessage(notice.getContent());
        return noticeMessageVO;
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

    @Override
    public IResult<Object> update(NoticeUpdateArgs args) throws ServiceException {
        Notice notice = noticeUpdateArgsToNotice(args);
        noticeDao.updateById(notice);
        return SimpleResult.ok();
    }


    /**
     * 推送消息
     *
     * @param noticeMessageVO
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Object>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/6 0:53
     */
    @Override
    public IResult<Object> send(NoticeMessageVO noticeMessageVO) throws ServiceException {

        return null;
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