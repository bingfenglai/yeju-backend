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

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.sms.interfaces.ISmsTemplateService;
import pers.lbf.yeju.service.interfaces.sms.pojo.SimpleSmsTemplateInfoBean;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateCreateArgs;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateQueryArgs;
import pers.lbf.yeju.service.interfaces.sms.pojo.SmsTemplateUpdateArgs;

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

    //@Autowired
    //private ISmsTemplateDao SmsTemplateDao;

    @Override
    public IResult<Boolean> create(SmsTemplateCreateArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> updateById(SmsTemplateUpdateArgs args) throws ServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> removeBatch(Set<Long> ids) throws ServiceException {
        return null;
    }

    @Override
    public PageResult<SimpleSmsTemplateInfoBean> queryPage(SmsTemplateQueryArgs args) throws ServiceException {
        return null;
    }


}