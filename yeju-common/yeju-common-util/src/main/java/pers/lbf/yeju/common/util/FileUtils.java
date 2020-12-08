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
package pers.lbf.yeju.common.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/8 17:39
 */
public class FileUtils {

    private FileUtils(){

    }

    public static void writeFile(String path,String data){
        File dest = new File(path);
        //判断文件是否存在，不存在则创建

        if (!dest.exists()) {
            boolean flag = false;
            try {
                flag = dest.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!flag) {
                try {
                    throw new IOException("路径不存在，且创建文件失败！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        byte[] bytes;
        bytes = data.getBytes(StandardCharsets.UTF_8);
        try {
            Files.write(dest.toPath(),bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static byte[] readFile(String fileName){
        try {
            return Files.readAllBytes(new File(fileName).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
