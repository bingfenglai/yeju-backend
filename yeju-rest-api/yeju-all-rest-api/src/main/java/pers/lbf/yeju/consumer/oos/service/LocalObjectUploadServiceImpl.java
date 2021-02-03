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
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.consumer.oos.minio.MinioHelper;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/2 0:06
 */
@Service
@Slf4j
public class LocalObjectUploadServiceImpl implements ILocalObjectUploadService {

    @Resource
    private MinioHelper minioHelper;

    @Override
    public Mono<IResult<String>> upload(FilePart filePart) throws ServiceException {

        Mono<Void> mono = filePart.transferTo(new File(filePart.filename()));

        mono.doFinally(signalType -> {
           log.info("***************开始上传到 oos **********");
            try {
                minioHelper.upload(filePart.filename());

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    Files.delete(Paths.get(filePart.filename()));
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    boolean flag = ! Files.exists(Paths.get(filePart.filename()));
                    if (flag) {
                        log.info("删除主机文件成功");
                    } else {
                        log.warn("删除主机文件失败");
                    }
                }


            }
        }).subscribe();


        return Mono.just(Result.ok(""));
    }




}
