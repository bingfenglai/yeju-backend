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

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

/**
 * AES加密工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/5 15:04
 */
public class AesUtils {


    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 默认key
     */
    private static String KEY = "fFK/B5sFXeITk6cXV5rTNA==";

    private static final int KEY_SIZE256 = 256;

    private static final int keySize192 = 192;

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
        kg.init(KEY_SIZE256, new SecureRandom());

        // 生成密钥
        SecretKey secretKey = kg.generateKey();

        // 获取二进制密钥编码形式
        return Base64Util.encodeToString(secretKey.getEncoded());
    }

    public static String generateKey(String password) throws Exception {
        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        // 初始化密钥生成器:AES要求密钥长度为128,192,256位
        kg.init(KEY_SIZE256, new SecureRandom(password.getBytes(StandardCharsets.UTF_8)));

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
     * @param data 待加密数据
     * @param key  密钥
     * @return java.lang.String
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/5 15:51
     */
    public static String encrypt(String data, String key) throws Exception {

        // 还原密钥
        Key k = toKey(Base64Util.decode(key));

        // 使用PKCS7Padding填充方式,这里就得这么写了(即调用BouncyCastle组件实现)

        // 实例化Cipher对象，它用于完成实际的加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 初始化Cipher对象，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);

        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
        return Base64Util.encodeToString(cipher.doFinal(data.getBytes()));
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

        Key k = toKey(Base64Util.decode(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // 初始化Cipher对象，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);

        // 执行解密操作
        return new String(cipher.doFinal(Base64Util.decode(data)));
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

}
