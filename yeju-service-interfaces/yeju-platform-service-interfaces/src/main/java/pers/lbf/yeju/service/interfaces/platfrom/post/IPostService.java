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
package pers.lbf.yeju.service.interfaces.platfrom.post;

import pers.lbf.yeju.common.core.args.IFindPageArgs;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimplePostInfoBean;

import java.util.List;

/** 职位接口类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/14 17:29
 */
public interface IPostService {

    IResult<SimplePostInfoBean> findPostById(Long id) throws ServiceException;

    PageResult<List<SimplePostInfoBean>> findPage(IFindPageArgs args) throws ServiceException;

    IResult<Object> addPost(SimplePostInfoBean bean) throws ServiceException;

    IResult<Object> updatePost(SimplePostInfoBean bean) throws ServiceException;

}