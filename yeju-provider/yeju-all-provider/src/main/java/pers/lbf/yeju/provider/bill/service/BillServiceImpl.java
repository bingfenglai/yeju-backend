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
package pers.lbf.yeju.provider.bill.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.provider.bill.dao.IBillDao;
import pers.lbf.yeju.service.interfaces.bill.IBillService;
import pers.lbf.yeju.service.interfaces.bill.pojo.BillQueryArgs;
import pers.lbf.yeju.service.interfaces.bill.pojo.CreateBillArgs;
import pers.lbf.yeju.service.interfaces.bill.pojo.SimpleBillInfoBean;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/28 22:16
 */
@DubboService(interfaceClass = IBillService.class, timeout = 10000, retries = 0)
@Slf4j
public class BillServiceImpl implements IBillService {

    @Autowired
    private IBillDao BillDao;


    /**
     * 订单创建 ---成功返回订单号，不成功抛出异常
     *
     * @param createBillArgs
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<String> createBill(CreateBillArgs createBillArgs) throws ServiceException {
        return null;
    }


    /**
     * 分页查询接口
     *
     * @param billQueryArgs
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<SimpleBillInfoBean> queryPage(BillQueryArgs billQueryArgs) throws ServiceException {
        return null;
    }
}