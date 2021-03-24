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
package pers.lbf.yeju.provider.log.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.domain.entity.log.HouseCheckLog;
import pers.lbf.yeju.provider.log.dao.IHouseCheckLogDao;
import pers.lbf.yeju.service.interfaces.log.IHouseCheckLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.HouseCheckLogCreateArgs;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/21 15:51
 */
@DubboService(interfaceClass = IHouseCheckLogService.class)
@Slf4j
@Service
public class HouseCheckLogServiceImpl implements IHouseCheckLogService {

    @Autowired
    private IHouseCheckLogDao houseCheckLogDao;

    @Async
    @Override
    public void add(HouseCheckLogCreateArgs args) throws ServiceException {
        HouseCheckLog log = createArgsToHouseCheckLog(args);
        houseCheckLogDao.insert(log);
    }

    private HouseCheckLog createArgsToHouseCheckLog(HouseCheckLogCreateArgs args) {
        return null;
    }
}