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
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** md5 信息摘要算法加解密工具类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/1 10:41
 */
public class Md5Utils {



    private Md5Utils() {

    }

    /** 16进制的字符数组 */
    private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    private final static String DEFAULT_ENCODING = "UTF-8";

    /**
     * 指定算法为MD5的MessageDigest
     */
    private static MessageDigest messageDigest = null;

    /* 初始化messageDigest的加密算法为MD5
     *
     */
    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static String encode(String source){
        return encode(source,DEFAULT_ENCODING,true);
    }

    /** dm5 加密方法
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/1 14:08
     * @param source 原字符串
     * @param encoding 编码 默认为UTF-8
     * @param uppercase 是否转大写 默认转
     * @return java.lang.String
     */
    public static String encode(String source, String encoding, boolean uppercase) {


        if (YejuStringUtils.isNull(encoding)){
            encoding = DEFAULT_ENCODING;
        }

        String result = source;
            // 使用指定的字节数组更新摘要信息
        try {
            messageDigest.update(result.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // messageDigest.digest()获得16位长度
        result = byteArrayToHexString(messageDigest.digest());


        return uppercase ? result.toUpperCase() : result;
    }

    /** 获取文件的md5值
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/1 14:23
     * @param source 待加密文件
     * @return java.lang.String
     */
    public static String encode(File source) {

        if (!source.isFile())
        {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte[] buffer = new byte[1024];
        int len;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(source);
            while ((len = in.read(buffer, 0, 1024)) != -1)
            {
                digest.update(buffer, 0, len);
            }
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /** 获取文件的md5加密 密文
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/1 14:26
     * @param filename 目标文件的完成文件名
     * @return String
     */
    public static String getFileMD5ByFilename(String filename) {

        return encode(new File(filename));
    }

    /** 校验文件是否一致
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/1 14:29
     * @param source
     * @param md5String
     * @return boolean
     */
    public static boolean check(String source,String md5String){
        if (YejuStringUtils.isNull(source)||YejuStringUtils.isNull(md5String)){
            return false;
        }
        String s = encode(source);
        return s.equalsIgnoreCase(md5String);
    }

    public static boolean check(File source,String md5String){
        if (YejuStringUtils.isNull(source)||YejuStringUtils.isNull(md5String)){
            return false;
        }
        return encode(source).equalsIgnoreCase(md5String);
    }

    public static boolean checkByFileName(String filename,String md5String){
        if (YejuStringUtils.isNull(filename)||YejuStringUtils.isNull(md5String)){
            return false;
        }
        return getFileMD5ByFilename(filename).equalsIgnoreCase(md5String);
    }




    /** 转换字节数组为16进制字符串
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/1 14:06
     * @param bytes 待转换的字节数组
     * @return java.lang.String
     */
    private static String byteArrayToHexString(byte[] bytes) {

        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }

    /** 将字节类型数据转换成字符串
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/1 14:04
     * @param b 要转换的数据
     * @return String 转换结果
     */
    private static String byteToHexString(byte b) {

        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }


}
