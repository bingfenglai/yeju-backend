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
package pers.lbf.yeju.provider.oos.service;


import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.FileStatus;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.provider.oos.dao.ResourceMd5Dao;
import pers.lbf.yeju.service.interfaces.oos.IFileUploadService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/30 0:04
 */
@DubboService(interfaceClass = IFileUploadService.class)
public class ObjectUploadServiceImpl implements IFileUploadService {

    @Autowired
    private ResourceMd5Dao resourceMd5Dao;

    /**
     * 上传文件
     *
     * @param fileByte    MultipartFile to fileByte
     * @param fileMd5 fileMd5
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/29 16:28
     */
    @Override
    public void upload(byte[] fileByte, String fileMd5) {
        InputStream in = new ByteArrayInputStream(fileByte);

    }

    /**
     * 判断文件是否存在，如果存在返回url
     *
     * @param fileMd5 fileMd5
     * @return file url
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/29 16:31
     */
    @Override
    public IResult<Object> isExited(String fileMd5) throws ServiceException {

        if (fileMd5 == null||fileMd5.length() == 0) {
            throw ServiceException.getInstance("文件md5值不能为空！",ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

        String url = resourceMd5Dao.selectResourceUrlByMd5(fileMd5);
        if (url == null || url.length() == 0){
            return SimpleResult.faild(FileStatus.NOT_Found);
        }
        return Result.ok(url);
    }
}
