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
package pers.lbf.yeju.provider.assets.integration.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.domain.entity.Integration;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.assets.integration.dao.IIntegrationDao;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.payment.IIntegrationService;
import pers.lbf.yeju.service.interfaces.payment.pojo.IntegrationFindPageArgs;
import pers.lbf.yeju.service.interfaces.payment.pojo.SimpleIntegrationInfoBean;
import pers.lbf.yeju.service.interfaces.payment.type.IntegrationType;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/25 9:21
 */
@DubboService(interfaceClass = IIntegrationService.class, timeout = 10000, retries = 0)
@Slf4j
public class IntegrationServiceImpl implements IIntegrationService {

    @Autowired
    private IIntegrationDao integrationDao;

    @DubboReference
    private IAccountService accountService;

    /**
     * 积分分页条件查询接口
     *
     * @param args 积分查询参数
     * @return 积分 list
     * @throws ServiceException e
     */
    @Override
    @Cacheable(cacheNames = "integration:page", key = "#args")
    public PageResult<SimpleIntegrationInfoBean> findPage(IntegrationFindPageArgs args) throws ServiceException {
        Long accountId = null;
        if (YejuStringUtils.isNotNUllAndNotEmpty(args.getAccount())) {
            accountId = accountService.findAccountIdByPrincipal(args.getAccount()).getData();
        }

        List<SimpleIntegrationInfoBean> results = new LinkedList<>();
        Page<Integration> page = PageUtil.getPage(Integration.class, args.getCurrentPage(), args.getSize());

        QueryWrapper<Integration> queryWrapper = new QueryWrapper<>();

        if (args.getIntegrationType() != null) {
            queryWrapper.eq("integration_type", args.getIntegrationType());
        }

        if (accountId != null) {
            queryWrapper.eq("account_id", accountId);
        }

        Page<Integration> integrationPage = integrationDao.selectPage(page, queryWrapper);
        for (Integration integration : integrationPage.getRecords()) {
            SimpleIntegrationInfoBean bean = integrationToSimpleInfoBean(integration);
            results.add(bean);
        }
        return PageResult.ok(integrationPage.getTotal(), integrationPage.getCurrent(), integrationPage.getSize(), results);
    }

    private SimpleIntegrationInfoBean integrationToSimpleInfoBean(Integration integration) {
        SimpleIntegrationInfoBean simpleIntegrationInfoBean = new SimpleIntegrationInfoBean();
        simpleIntegrationInfoBean.setId(integration.getId());
        simpleIntegrationInfoBean.setAccountId(integration.getAccountId());
        simpleIntegrationInfoBean.setIntegration(integration.getIntegration());
        simpleIntegrationInfoBean.setIntegrationType(integration.getIntegrationType());
        simpleIntegrationInfoBean.setCreateTime(integration.getCreateTime());
        simpleIntegrationInfoBean.setCreateBy(integration.getCreateBy());
        return simpleIntegrationInfoBean;

    }

    /**
     * 给指定账号添加积分
     *
     * @param integrationType 积分类型
     * @param accountId       账号id
     * @return flag
     * @throws ServiceException
     */
    @Override
    public IResult<Boolean> add(IntegrationType integrationType, Long accountId) throws ServiceException {
        return null;
    }

    /**
     * 给指定账号添加积分
     *
     * @param integrationType 积分类型
     * @param accountNumber   账号id
     * @return flag
     * @throws ServiceException
     */
    @Override
    public IResult<Boolean> add(IntegrationType integrationType, String accountNumber) throws ServiceException {
        return null;
    }
}