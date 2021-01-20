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
package pers.lbf.yeju.service.interfaces.log;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.log.pojo.AddLoginLogRequestBean;
import pers.lbf.yeju.service.interfaces.log.pojo.LoginLogInfoBean;

/**异步登录日志服务接口类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/29 10:56
 */
public interface ILoginLogService {

    /**TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/19 14:59
     * @param loginLogDTO 登录日志信息封装类
     * @return void
     * @throws ServiceException e
     */
    void addLog(AddLoginLogRequestBean loginLogDTO) throws ServiceException;

    /**TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/19 14:59
     * @param currentPage 当前页
     * @param size 每页大小
     * @return pa个ge
     * @throws ServiceException e
     */
    PageResult<LoginLogInfoBean> findList(Long currentPage, Long size) throws ServiceException;

}
