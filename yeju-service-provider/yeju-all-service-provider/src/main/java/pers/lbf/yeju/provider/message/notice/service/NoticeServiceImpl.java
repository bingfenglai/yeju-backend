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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import pers.lbf.yeju.common.core.constant.DataDictionaryTypeConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.domain.entity.Notice;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.message.notice.dao.INoticeDao;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.message.INoticeService;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeCreateArgs;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeUpdateArgs;
import pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean;
import pers.lbf.yeju.service.interfaces.platfrom.employee.IEmployeeService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private INoticeDao noticeDao;

    @DubboReference
    private IDataDictionaryInfoService dictionaryInfoService;

    @DubboReference
    private IEmployeeService employeeService;

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
    @Cacheable(cacheNames = "noticeService", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleNoticeInfoBean> findPage(Long currentPage, Long size) throws ServiceException {

        Page<Notice> page = PageUtil.getPage(Notice.class, currentPage, size);
        Page<Notice> noticePage = noticeDao.selectPage(page, null);
        List<Notice> noticeList = noticePage.getRecords();
        List<SimpleNoticeInfoBean> result = new LinkedList<>();
        Map<String, String> noticeTypeMap = dictionaryInfoService.getDictMap("notice_type").getData();
        Map<String, String> statusMap = dictionaryInfoService.getDictMap(DataDictionaryTypeConstant.NOTICE_STATUS).getData();

        for (Notice notice : noticeList) {
            SimpleNoticeInfoBean bean = this.noticeToSimpleInfoBean(notice);
            if (bean.getNoticeType() != null) {
                bean.setNoticeTypeStr(noticeTypeMap.get(bean.getNoticeType()));

            }
            if (bean.getStatus() != null) {
                bean.setStatusStr(statusMap.get(bean.getStatus().toString()));
            }
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


    @Override
    public IResult<Object> create(NoticeCreateArgs args) throws ServiceException {
        Notice notice = noticeCreateArgsToNotice(args);
        noticeDao.insert(notice);
        return SimpleResult.ok();
    }

    private Notice noticeCreateArgsToNotice(NoticeCreateArgs args) {
        Notice notice = new Notice();
        notice.setTitle(args.getNoticeTitle());
        notice.setContent(args.getContent());
        notice.setNoticeType(args.getNoticeType());
        notice.setStatus(args.getStatus());
        notice.setStartTime(args.getStartTime());
        notice.setEndTime(args.getEndTime());
        notice.setCreateTime(new Date());
        notice.setRemark(args.getRemark());
        notice.setBeFrom(args.getBeFrom());
        notice.setSendTo(args.getSendTo());
        return notice;
    }

    @Override
    public IResult<Object> update(NoticeUpdateArgs args) throws ServiceException {
        Notice notice = noticeUpdateArgsToNotice(args);
        noticeDao.updateById(notice);
        return SimpleResult.ok();
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