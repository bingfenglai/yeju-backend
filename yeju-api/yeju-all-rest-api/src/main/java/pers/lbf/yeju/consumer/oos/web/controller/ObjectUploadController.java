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
package pers.lbf.yeju.consumer.oos.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.consumer.oos.service.ObjectUploadService;
import pers.lbf.yeju.service.interfaces.oos.IFileUploadService;
import reactor.core.publisher.Mono;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/1 18:02
 */
@RestController
@RequestMapping("/oos")
@Slf4j
@Api(tags = "对象存储服务")
public class ObjectUploadController {

    @DubboReference
    private IFileUploadService fileUploadService;

    @Autowired
    private ObjectUploadService localObjectUploadService;


    @ApiOperation(value = "上传文件", notes = "上传文件说明", httpMethod = "POST")
    @PostMapping("/upload")
    public Mono<IResult<String>> upload(
            @ApiParam("待上传文件") @RequestPart FilePart filePart) throws ServiceException {

        return localObjectUploadService.upload(filePart);

    }

    @ApiOperation(value = "上传文件", notes = "快传接口", httpMethod = "POST")
    @PostMapping("/upload/{fileMd5}")
    public Mono<IResult<String>> upload(
            @ApiParam("文件md5值") @PathVariable String fileMd5,
            @ApiParam("待上传文件") @RequestPart FilePart filePart) throws ServiceException {

        return localObjectUploadService.upload(fileMd5, filePart);

    }

    @ApiOperation(value = "根据文件MD5值判断文件是否存在",
            notes = "说明:根据文件MD5值判断文件是否存在 存在则返回文件url,不存在返回false", httpMethod = "GET")
    @GetMapping("/{md5}")
    public Mono<IResult<String>> check(@PathVariable String md5) throws ServiceException {

        return Mono.just(fileUploadService.isExited(md5));

    }


}
