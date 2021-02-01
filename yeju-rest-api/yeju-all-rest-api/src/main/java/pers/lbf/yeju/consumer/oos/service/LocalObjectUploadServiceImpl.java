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
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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

    @Override
    public Mono<IResult<String>> upload(FilePart filePart) throws ServiceException {
        log.info("原文件名：{}",filePart.filename());
        Path tempFile = null;

        try {
            tempFile = Files.createTempFile("yeju_", filePart.filename());
        } catch (IOException e) {
            e.printStackTrace();
        }



        assert tempFile != null;
        log.info("新文件名：{}",tempFile.getFileName());


        AsynchronousFileChannel channel =
                null;
        try {
            channel = AsynchronousFileChannel.open(tempFile, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert channel != null;
        Path finalTempFile = tempFile;
        DataBufferUtils.write(filePart.content(), channel, 0)
                .doOnComplete(() -> {
                    log.info("文件{}上传成功！", finalTempFile.getFileName());
                });

        return Mono.just(Result.ok(tempFile.getFileName().toString()));
    }
}
