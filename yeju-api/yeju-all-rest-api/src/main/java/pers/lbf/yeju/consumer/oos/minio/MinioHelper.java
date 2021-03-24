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
package pers.lbf.yeju.consumer.oos.minio;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/3 15:04
 */
@Slf4j
public class MinioHelper {

    private static String bucketName = "yeju";

    private static MinioClient minioClient;

    public static void setBucketName(String bucketName) {
        MinioHelper.bucketName = bucketName;
    }

    public static void setClient(MinioClient client) {
        MinioHelper.minioClient = client;
    }

    public void upload(Path path) throws Exception{
        upload(String.valueOf(path));
    }

    public void upload(String path) throws Exception{
        if (minioClient != null){
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .object(path)
                    .filename(path)
                    .bucket(bucketName)
                    .build();
            upload(uploadObjectArgs);
            removeTempFile(path);

        }
    }

    public void upload(String path,String object) throws Exception{
        if (minioClient != null){
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .object(object)
                    .filename(path)
                    .bucket(bucketName)
                    .build();
            upload(uploadObjectArgs);
            removeTempFile(path);
        }
    }

    private void upload(UploadObjectArgs args) throws Exception{
        log.info("======================================");
        log.info("===            开始上传到 oos        ===");
        log.info("======================================");
        ObjectWriteResponse response = minioClient.uploadObject(args);
        log.info("***************上传到{} oos 成功**********",response.object());

    }

    private void removeTempFile(String path) throws IOException {
        log.info("***************开始删除临时文件 {}**********",path);
        if (Files.exists(Paths.get(path))){
            if (Files.isWritable(Paths.get(path))){
                Files.delete(Paths.get(path));
            }
        }

        log.info("===============删除临时文件 {} 成功**********",path);
    }


}

