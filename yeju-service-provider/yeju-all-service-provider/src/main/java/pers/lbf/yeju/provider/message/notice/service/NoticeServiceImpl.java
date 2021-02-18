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
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.Notice;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.message.notice.dao.INoticeDao;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.message.INoticeService;
import pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean;
import pers.lbf.yeju.service.interfaces.platfrom.employee.IEmployeeService;

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
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private INoticeDao noticeDao;

    @DubboReference
    private IDataDictionaryInfoService dictionaryInfoService;

    @DubboReference
    private IEmployeeService employeeService;

    @Cacheable(cacheNames = "noticeService",keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<SimpleNoticeInfoBean> findPage(Long currentPage, Long size) throws ServiceException {
        Page<Notice> page = PageUtil.getPage(Notice.class, currentPage, size);
        Page<Notice> noticePage = noticeDao.selectPage(page, null);
        List<Notice> noticeList = noticePage.getRecords();
        List<SimpleNoticeInfoBean> result = new LinkedList<>();
        Map<String, String> noticeTypeMap = dictionaryInfoService.getDictMap("notice_type").getData();
        Map<String, String> statusMap = dictionaryInfoService.getDictMap("status").getData();

        for (Notice notice : noticeList) {
            SimpleNoticeInfoBean bean = this.noticeToSimpleInfoBean(notice);
            if (bean.getNoticeType()!=null){
                bean.setNoticeTypeStr(noticeTypeMap.get(bean.getNoticeType()));

            }
            if (bean.getStatus()!=null){
                bean.setStatusStr(statusMap.get(bean.getStatus().toString()));
            }
            result.add(bean);
        }
        return PageResult.ok(noticePage.getTotal(),currentPage,size,result);
    }

    @Override
    public Result<List<SimpleNoticeInfoBean>> findEffectiveNoticeList() throws ServiceException {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",1);
        queryWrapper.select("title","content","notice_type");
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

    private SimpleNoticeInfoBean noticeToSimpleInfoBean(Notice notice){
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
        if (notice.getCreateBy()!=null){
            String name = employeeService.findNameByAccount(notice.getCreateBy()).getData();
            simpleNoticeInfoBean.setCreateByStr(name);
            log.info("name {}",name);
        }
        return simpleNoticeInfoBean;

    }
}