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
package pers.lbf.yeju.service.interfaces.oos;

import pers.lbf.yeju.common.core.result.IResult;

import java.io.File;

/** 文件上传服务接口
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date  16:23
 */
public interface IFileUploadService {

    /** 上传文件
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/29 16:28
     * @param file MultipartFile
     * @param fileMd5 fileMd5
     * @return void
     */
    void upload(File file, String fileMd5);


    /** 判断文件是否存在，如果存在返回url
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/1/29 16:31
     * @param fileMd5 fileMd5
     * @return file url
     */
    IResult<String> isExited(String fileMd5);


}
