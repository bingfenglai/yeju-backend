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

package pers.lbf.yeju.service.interfaces.payment;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.payment.pojo.IntegrationFindPageArgs;
import pers.lbf.yeju.service.interfaces.payment.pojo.SimpleIntegrationInfoBean;
import pers.lbf.yeju.service.interfaces.payment.type.IntegrationType;

/**
 * 积分服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/24 15:48
 */
public interface IIntegrationService {

    /**
     * 积分分页条件查询接口
     *
     * @param args 积分查询参数
     * @return 积分 list
     * @throws ServiceException e
     */
    PageResult<SimpleIntegrationInfoBean> findPage(IntegrationFindPageArgs args) throws ServiceException;

    /**
     * 给指定账号添加积分
     *
     * @param integrationType 积分类型
     * @param accountId       账号id
     * @return flag
     * @throws ServiceException
     */
    IResult<Boolean> add(IntegrationType integrationType, Long accountId) throws ServiceException;

    /**
     * 给指定账号添加积分
     *
     * @param integrationType 积分类型
     * @param accountNumber   账号id
     * @return flag
     * @throws ServiceException
     */
    IResult<Boolean> add(IntegrationType integrationType, String accountNumber) throws ServiceException;


}
