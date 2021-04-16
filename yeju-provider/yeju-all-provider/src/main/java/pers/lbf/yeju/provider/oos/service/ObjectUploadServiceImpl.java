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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ParameStatusEnum;
import pers.lbf.yeju.common.domain.entity.ResourceMd5;
import pers.lbf.yeju.provider.oos.dao.ResourceMd5Dao;
import pers.lbf.yeju.service.interfaces.oos.IFileUploadService;
import pers.lbf.yeju.service.interfaces.oos.pojo.SaveMd5Args;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 对象传输服务
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
     * @param fileByte MultipartFile to fileByte
     * @param fileMd5  fileMd5
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/29 16:28
     */
    @Override
    @Deprecated
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
    @Cacheable(value = "objectUploadService:fileMd5", key = "#fileMd5")
    public IResult<String> isExited(String fileMd5) throws ServiceException {

        if (fileMd5 == null || fileMd5.length() == 0) {
            throw ServiceException.getInstance("文件md5值不能为空！", ParameStatusEnum.Parameter_cannot_be_empty.getCode());
        }

        String url = resourceMd5Dao.selectResourceUrlByMd5(fileMd5);
        if (url == null || url.length() == 0) {
            return Result.ok("");
        }
        return Result.ok(url);
    }


    @CacheEvict(value = "objectUploadService:fileMd5", key = "#args.md5")
    @Override
    public void saveMd5(SaveMd5Args args) throws ServiceException {
        ResourceMd5 resourceMd5 = new ResourceMd5();
        resourceMd5.setResource(args.getResourceUrl());
        resourceMd5.setResourceType(1L);
        resourceMd5.setMd5(args.getMd5());
        resourceMd5Dao.insert(resourceMd5);
    }
}
