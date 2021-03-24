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
package pers.lbf.yeju.consumer.oos.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.constant.ServiceStatusConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.util.Md5Utils;
import pers.lbf.yeju.consumer.oos.config.MinioConfig;
import pers.lbf.yeju.consumer.oos.minio.MinioHelper;
import pers.lbf.yeju.service.interfaces.oos.IFileUploadService;
import pers.lbf.yeju.service.interfaces.oos.pojo.SaveMd5Args;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/2 0:06
 */
@Service
@Slf4j
public class ObjectUploadServiceImpl implements ObjectUploadService {

    @Resource
    private MinioHelper minioHelper;

    @Autowired
    private MinioConfig minioConfig;

    @DubboReference
    private IFileUploadService fileUploadService;

    @Override
    public Mono<IResult<String>> upload(FilePart filePart) throws ServiceException {
        // 1. 将文件从客户端传至主机临时目录
        String tempPathname = minioConfig.getTempDir() + "/" + filePart.filename();
        log.info("===============================");
        log.info("=   将文件保存至主机临时目录       =");

        filePart.transferTo(new File(tempPathname));
        log.info("=   文件保存至临时目录成功         =");
        String objectName = UUID.randomUUID().toString() + filePart.filename();
        // 最终url
        String finalUrl = minioConfig.getEndpoint() + minioConfig.getBucketName() + "/" + objectName;
        //md5计算
        String md5 = Md5Utils.getFileMD5ByFilename(tempPathname);
        return Mono.just(Result.ok(finalUrl)).
                doOnSuccess(stringResult -> {
                    //使用响应式编程，先给用户返回url,再传输到 oos
                    try {
                        minioHelper.upload(tempPathname, objectName);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                })
                .
                        doFinally(signalType -> {
                            log.info("上传文件的md5: {}", md5);
                            SaveMd5Args args = new SaveMd5Args();
                            args.setMd5(md5);
                            args.setResourceUrl(finalUrl);
                            fileUploadService.saveMd5(args);
                        });
    }

    @Override
    public Mono<IResult<String>> upload(String fileMd5, FilePart filePart) throws ServiceException {
        //1. 验证文件是否已存在
        if (fileMd5 != null && fileMd5.length() > 0) {

            IResult<Object> result = fileUploadService.isExited(fileMd5);

            // 1.1 文件已经存在，直接返回url
            if (Objects.equals(result.getCode(), ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE)) {
                return Mono.just(Result.ok((String) result.getData()));
            }

        }


        // 2. 文件不存在，上传并将md5值写入数据库
        return upload(filePart);

    }


}
