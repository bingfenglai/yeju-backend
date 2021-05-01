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

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

/**
 * AES加密工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/5 15:04
 */
public class AesUtils {

    private static final String IV_STRING = "abcdefhABCDEoFGH";


    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
     */
    private static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";

    //    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS7Padding";


    /**
     * 默认key
     */
    private static String KEY = "fFK/B5sFXeITk6cXV5rTNA==";

    private static final int KEY_SIZE256 = 256;

    private static final int KEY_SIZE128 = 128;

    static {
        try {
            KEY = generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成密钥
     *
     * @param
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/5 15:52
     */
    public static String generateKey() throws Exception {

        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        // 初始化密钥生成器:AES要求密钥长度为128,192,256位
        kg.init(KEY_SIZE128, new SecureRandom());

        // 生成密钥
        SecretKey secretKey = kg.generateKey();

        // 获取二进制密钥编码形式
        return Base64Util.encodeToString(secretKey.getEncoded());
    }

    /**
     * 生成密钥
     *
     * @param password 密码 安全随机数
     * @return base64编码的密钥
     * @throws Exception
     */
    public static String generateKey(String password) throws Exception {
        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        // 初始化密钥生成器:AES要求密钥长度为128,192,256位
        kg.init(KEY_SIZE128, new SecureRandom(password.getBytes(StandardCharsets.UTF_8)));
        //kg.init(KEY_SIZE128);
        Security.addProvider(new BouncyCastleProvider());
        // 生成密钥
        SecretKey secretKey = kg.generateKey();

        // 获取二进制密钥编码形式
        return Base64Util.encodeToString(secretKey.getEncoded());
    }

    /**
     * 转换密钥
     */
    private static Key toKey(byte[] key) throws Exception {

        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 加密数据
     *
     * @param data 明文
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/5 15:50
     */
    public static String encrypt(String data) throws Exception {

        return encrypt(data, KEY);
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/5 15:51
     */
    public static String encrypt(String data, String key) throws Exception {

        return encrypt(data, key, null);
    }

    /**
     * 加密数据方法
     *
     * @param data     待加密数据
     * @param key      密钥
     * @param ivString CBC加密方式所需参数 当其不为null且为16个字节时时采取CBC加密方式
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key, String ivString) throws Exception {
        // 还原密钥
        Key k;
        if (key != null) {
            k = toKey(Base64Util.decode(key));
        } else {
            k = toKey(Base64Util.decode(KEY));
        }
        // 使用PKCS7Padding填充方式,这里就得这么写了(即调用BouncyCastle组件实现)

        // 实例化Cipher对象，它用于完成实际的加密操作
        Cipher cipher;
        Security.addProvider(new BouncyCastleProvider());
        if (ivString != null && ivString.length() == 16) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            byte[] initParam = ivString.getBytes(StandardCharsets.UTF_8);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            // 初始化Cipher对象，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, k, ivParameterSpec);
        } else {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            cipher.init(Cipher.ENCRYPT_MODE, k);
        }
        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
        return Base64Util.encodeToString(cipher.doFinal(data.getBytes()));
    }


    /**
     * 解密数据
     *
     * @param data 密文
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/5 15:49
     */
    public static String decrypt(String data) throws Exception {

        return decrypt(data, KEY);
    }


    /**
     * 解密数据
     *
     * @param data 密文
     * @param key  密钥
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/5 15:50
     */
    public static String decrypt(String data, String key) throws Exception {

        return decrypt(data, key, null);
    }


    public static String decrypt(String data, String key, String ivString) throws Exception {
        Key k;
        if (key != null) {
            k = toKey(Base64Util.decode(key));
        } else {
            k = toKey(Base64Util.decode(KEY));
        }

        Cipher cipher;
        Security.addProvider(new BouncyCastleProvider());
        if (ivString != null && ivString.length() == 16) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);

            // 初始化Cipher对象，设置为解密模式
            byte[] initParam = ivString.getBytes(StandardCharsets.UTF_8);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            cipher.init(Cipher.DECRYPT_MODE, k, ivParameterSpec);
        } else {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            cipher.init(Cipher.DECRYPT_MODE, k);
        }


        // 执行解密操作
        return new String(cipher.doFinal(Base64Util.decode(data)));

    }

    /**
     * 获取16位随机字符串 用来作为ivSting
     *
     * @return
     */
    public static String getUUID16() {
        StringBuilder uid = new StringBuilder();
        //产生16位的强随机数
        Random rd = new SecureRandom();
        for (int i = 0; i < 16; i++) {
            //产生0-2的3位随机数
            int type = rd.nextInt(2);
            switch (type) {
                case 0:
                    //ASCII在65-90之间为大写,获取大写随机
                    uid.append((char) (rd.nextInt(25) + 65));
                    break;
                case 1:
                    //ASCII在97-122之间为小写，获取小写随机
                    uid.append((char) (rd.nextInt(25) + 97));
                    break;
                default:
                    break;
            }
        }
        return uid.toString();
    }

    public static void main(String[] args) {

        try {
            String s = "yeju/com";
            System.out.println(IV_STRING.length());
            String uuid16 = getUUID16();
            String key = generateKey(uuid16);
            System.out.println("key 字节长度：" + key.getBytes(StandardCharsets.UTF_8).length);
            System.out.println(uuid16.length() + uuid16);
            String s1 = encrypt(s, key, uuid16);
            System.out.println("明文：" + s);
            System.out.println("密文：" + s1);
            String decrypt = decrypt(s1, key, uuid16);
            System.out.println(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
