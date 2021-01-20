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
package pers.lbf.yeju.service.interfaces.employee;

import pers.lbf.yeju.common.core.PageInfoBean;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.employee.pojo.SimpleEmployeeInfoBean;

import java.util.List;

/** 员工服务接口
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/18 20:29
 */
public interface IEmployeeService {

    /**TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:34
     * @param account  系统账户
     * @return IResult
     * @throws ServiceException e
     */
    IResult<SimpleEmployeeInfoBean> findInfoByAccount(String account)throws ServiceException;

    /**TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:36
     * @param account 系统账户
     * @return void
     * @throws ServiceException e
     */
    void RemoveEmployeeByAccount(String account) throws ServiceException;

    /**TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:37
     * @param employeeInfo 员工信息
     * @return void
     * @throws ServiceException e
     */
    void addOne(SimpleEmployeeInfoBean employeeInfo) throws ServiceException;

    /**TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:39
     * @param employeeInfos 员工信息列表
     * @return void
     * @throws ServiceException e
     */
    void  addList(List<SimpleEmployeeInfoBean> employeeInfos) throws ServiceException;


    /**TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/18 20:42
     * @param pageInfoBean 当前页信息
     * @return list
     * @throws ServiceException e
     */
    IResult<SimpleEmployeeInfoBean> findList(PageInfoBean pageInfoBean) throws ServiceException;
}
