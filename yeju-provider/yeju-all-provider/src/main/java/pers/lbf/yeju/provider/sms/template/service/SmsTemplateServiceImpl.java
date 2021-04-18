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
package pers.lbf.yeju.provider.sms.template.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.domain.entity.business.SmsTemplate;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.sms.template.dao.ISmsTemplateDao;
import pers.lbf.yeju.service.interfaces.sms.interfaces.ISmsTemplateService;
import pers.lbf.yeju.service.interfaces.sms.pojo.SimpleSmsTemplateInfoBean;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateCreateArgs;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateQueryArgs;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateUpdateArgs;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 短信模板服务实现
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/15 22:05
 */
@DubboService(interfaceClass = ISmsTemplateService.class, timeout = 10000, retries = 0)
@Slf4j
public class SmsTemplateServiceImpl implements ISmsTemplateService {

    @Autowired
    private ISmsTemplateDao smsTemplateDao;

    @Override
    public IResult<Boolean> create(SmsTemplateCreateArgs args) throws ServiceException {

        SmsTemplate smsTemplate = createArgsToSmsTemplate(args);
        int insert = smsTemplateDao.insert(smsTemplate);

        return Result.ok(insert == 1);

    }


    @Override
    public IResult<Boolean> updateById(SmsTemplateUpdateArgs args) throws ServiceException {
        SmsTemplate smsTemplate = updateArgsToSmsTemplate(args);
        int i = smsTemplateDao.updateById(smsTemplate);
        return Result.ok(i == 1);
    }

    private SmsTemplate updateArgsToSmsTemplate(SmsTemplateUpdateArgs args) {
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setId(args.getId());
        smsTemplate.setTemplate(args.getTemplate());
        smsTemplate.setType(args.getType());
        smsTemplate.setStatus(args.getStatus());
        smsTemplate.setCreateTime(args.getCreateTime());
        smsTemplate.setCreateBy(args.getCreateBy());
        smsTemplate.setUpdateTime(args.getUpdateTime());
        smsTemplate.setChangedBy(args.getChangedBy());
        smsTemplate.setRemark(args.getRemark());

        return smsTemplate;
    }

    @Override
    public IResult<Boolean> removeBatch(Set<Long> ids) throws ServiceException {
        smsTemplateDao.deleteBatchIds(ids);
        return Result.success();
    }

    @Override
    public PageResult<SimpleSmsTemplateInfoBean> queryPage(SmsTemplateQueryArgs args) throws ServiceException {
        Page<SmsTemplate> page = PageUtil.getPage(SmsTemplate.class, args.getCurrentPage(), args.getSize());
        QueryWrapper<SmsTemplate> queryWrapper = queryWrapperBuild(args);
        Page<SmsTemplate> smsTemplatePage = smsTemplateDao.selectPage(page, queryWrapper);
        List<SimpleSmsTemplateInfoBean> result = new LinkedList<>();
        for (SmsTemplate smsTemplate : smsTemplatePage.getRecords()) {
            SimpleSmsTemplateInfoBean bean = smsTemplateToSimpleBean(smsTemplate);
            result.add(bean);
        }
        return PageResult.ok(smsTemplatePage.getTotal(), smsTemplatePage.getCurrent(), smsTemplatePage.getSize(), result);
    }

    @Override
    public IResult<Boolean> deleteById(Long id) throws ServiceException {
        smsTemplateDao.deleteById(id);
        return Result.success();
    }

    private SimpleSmsTemplateInfoBean smsTemplateToSimpleBean(SmsTemplate smsTemplate) {
        SimpleSmsTemplateInfoBean simpleSmsTemplateInfoBean = new SimpleSmsTemplateInfoBean();
        simpleSmsTemplateInfoBean.setId(smsTemplate.getId());
        simpleSmsTemplateInfoBean.setTemplate(smsTemplate.getTemplate());
        simpleSmsTemplateInfoBean.setType(smsTemplate.getType());
        simpleSmsTemplateInfoBean.setStatus(smsTemplate.getStatus());
        simpleSmsTemplateInfoBean.setCreateTime(smsTemplate.getCreateTime());
        simpleSmsTemplateInfoBean.setCreateBy(smsTemplate.getCreateBy());
        simpleSmsTemplateInfoBean.setRemark(smsTemplate.getRemark());
        return simpleSmsTemplateInfoBean;
    }

    private QueryWrapper<SmsTemplate> queryWrapperBuild(SmsTemplateQueryArgs args) {
        QueryWrapper<SmsTemplate> queryWrapper = new QueryWrapper<>();
        if (!YejuStringUtils.isEmpty(args.getBetween())) {
            queryWrapper.between("create_time", args.getBetween()[0], args.getBetween()[1]);
        }

        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getStatus())) {
            queryWrapper.eq("status", args.getStatus());
        }

        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getType())) {
            queryWrapper.eq("type", args.getType());
        }

        return queryWrapper;
    }

    private SmsTemplate createArgsToSmsTemplate(SmsTemplateCreateArgs args) {
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setRemark(args.getRemark());
        smsTemplate.setTemplate(args.getTemplate());
        smsTemplate.setType(args.getType());
        smsTemplate.setStatus(args.getStatus());
        smsTemplate.setCreateTime(args.getCreateTime());
        smsTemplate.setCreateBy(args.getCreateBy());

        return smsTemplate;
    }


}