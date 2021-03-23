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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.domain.entity.log.LoginLog;
import pers.lbf.yeju.provider.log.dao.ILoginLogDao;
import pers.lbf.yeju.provider.log.pojo.SimpleLoginLogInfoBean;
import pers.lbf.yeju.service.interfaces.log.ILoginLogService;
import pers.lbf.yeju.service.interfaces.log.pojo.AddLoginLogRequestBean;
import pers.lbf.yeju.service.interfaces.log.pojo.LoginLogInfoBean;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/1 17:05
 */
@DubboService(interfaceClass = ILoginLogService.class)
@Service(value = "loginLogService")
public class RemoteLoginLogServiceImpl implements ILoginLogService {


    @Autowired
    private ILoginLogDao loginLogDao;

    @Override
    @Async
    @CacheEvict(cacheNames = "log:login", allEntries = true)
    public void addLog(AddLoginLogRequestBean loginLogDTO) throws ServiceException {
        LoginLog loginLog = new LoginLog();

        loginLog.setAccount(loginLogDTO.getAccount());
        loginLog.setSubjectName(loginLogDTO.getSubjectName());
        loginLog.setIp(loginLogDTO.getIp());
        loginLog.setLoginStatus(loginLogDTO.getLoginStatus());
        loginLog.setMessage(loginLogDTO.getMessage());
        loginLog.setAccentTime(loginLogDTO.getAccentTime());
        loginLog.setLastIpNumber(loginLogDTO.getLastIpNumber());

        loginLogDao.insert(loginLog);

    }

    @Cacheable(value = "log:login", keyGenerator = "yejuKeyGenerator")
    @Override
    public PageResult<LoginLogInfoBean> findList(Long currentPage, Long size) throws ServiceException {
        Page<LoginLog> page = new Page<>();
        if (currentPage != null && currentPage > 1) {
            page.setCurrent(currentPage);
        }
        if (size != null && size > 0) {
            page.setSize(size);
        }

        QueryWrapper<LoginLog> wrapper = new QueryWrapper<>();
        wrapper.select("login_log_id", "account", "subject_name", "ip", "login_status", "message", "accent_time");
        Page<LoginLog> logPage = loginLogDao.selectPage(page, wrapper);
        List<LoginLogInfoBean> simpleLoginLogList = new LinkedList<>();
        for (LoginLog loginLog : logPage.getRecords()) {
            LoginLogInfoBean simpleLoginLog = new SimpleLoginLogInfoBean();
            simpleLoginLog.setLoginLogId(loginLog.getLoginLogId());
            simpleLoginLog.setAccount(loginLog.getAccount());
            simpleLoginLog.setSubjectName(loginLog.getSubjectName());
            simpleLoginLog.setIp(loginLog.getIp());
            simpleLoginLog.setLoginStatus(loginLog.getLoginStatus());
            simpleLoginLog.setMessage(loginLog.getMessage());
            simpleLoginLog.setAccentTime(loginLog.getAccentTime());
            simpleLoginLogList.add(simpleLoginLog);

        }

        return PageResult.ok(logPage.getTotal(), logPage.getCurrent(), logPage.getSize(), simpleLoginLogList);

    }
}
