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
package pers.lbf.yeju.service.interfaces.platfrom.department;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.DepartmentIdAndName;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimpleDepartmentInfoBean;

import java.util.List;

/** 平台部门服务接口类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/14 17:28
 */
public interface IDepartmentService {

    IResult<SimpleDepartmentInfoBean> findDeptById(Long id) throws ServiceException;

    PageResult<SimpleDepartmentInfoBean> findPage(Long currentPage, Long size) throws ServiceException;

    IResult<Object> addDepartment(SimpleDepartmentInfoBean bean) throws ServiceException;

    IResult<Object> updateDepartment(SimpleDepartmentInfoBean bean) throws ServiceException;


    IResult<List<DepartmentIdAndName>> findAll() throws ServiceException;
}
