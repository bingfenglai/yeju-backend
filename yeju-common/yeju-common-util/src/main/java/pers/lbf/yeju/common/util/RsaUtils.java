/*
  Copyright 2020 赖柄沣 bingfengdev@aliyun.com
  <p>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */


package pers.lbf.yeju.common.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * jwt 专用加密工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/30 12:01
 */
public class RsaUtils {
    //密钥默认长度
    private static final int DEFAULT_KEY_SIZE = 2048;
    private static final String CHARSET_NAME = "UTF-8";

    //算法
    private static final String RSA = "RSA";

    //加密填充方式
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    // 当前秘钥支持加密的最大字节数
    public static final int DEFAULT_BUFFERSIZE = (DEFAULT_KEY_SIZE / 8) - 11;

    // 当要加密的内容超过bufferSize，则采用partSplit进行分块加密
    public static final byte[] DEFAULT_SPLIT = "#PART#".getBytes();


    /**
     * 签名算法
     */
    private static final String ALGORITHM_RSA_SIGN = "SHA256WithRSA";
    private static final String RSA_ALGORITHM = "RSA";


    /**
     * 从文件中读取公钥
     *
     * @param filename 公钥保存路径，相对于classpath
     * @return java.security.PublicKey 公钥对象
     * @throws Exception e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:10:15
     * @version 1.0
     */
    public static PublicKey getPublicKey(String filename) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {

        byte[] bytes = readFile(filename);
        return getPublicKey(bytes);
    }


    /**
     * 从文件中读取密钥
     *
     * @param filename 私钥保存路径，相对于classpath
     * @return java.security.PrivateKey 私钥对象
     * @throws Exception e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:12:01
     * @version 1.0
     */
    public static PrivateKey getPrivateKey(String filename) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {

        byte[] bytes = readFile(filename);
        return getPrivateKey(bytes);

    }

    /**
     * @param bytes 公钥的字节形式
     * @return java.security.PublicKey 公钥对象
     * @throws Exception e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:12:59
     * @version 1.0
     */
    private static PublicKey getPublicKey(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        bytes = Base64.getDecoder().decode(bytes);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance(RSA);
        return factory.generatePublic(spec);

    }


    /**
     * 获取密钥
     *
     * @param bytes 私钥的字节形式
     * @return java.security.PrivateKey
     * @throws InvalidKeySpecException  e
     * @throws NoSuchAlgorithmException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:14:02
     * @version 1.0
     */
    private static PrivateKey getPrivateKey(byte[] bytes) throws InvalidKeySpecException, NoSuchAlgorithmException {
        bytes = Base64.getDecoder().decode(bytes);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance(RSA);
        return factory.generatePrivate(spec);

    }

    /**
     * 根据密文，生存rsa公钥和私钥,并写入指定文件
     *
     * @param publicKeyFilename  公钥文件路径
     * @param privateKeyFilename 私钥文件路径
     * @param secret             生成密钥的密文
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:14:02
     */
    public static void generateKey(String publicKeyFilename, String privateKeyFilename, String secret, int keySize) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());

        keyPairGenerator.initialize(Math.max(keySize, DEFAULT_KEY_SIZE), secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取公钥并写出
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        publicKeyBytes = Base64.getEncoder().encode(publicKeyBytes);
        writeFile(publicKeyFilename, publicKeyBytes);
        // 获取私钥并写出
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        privateKeyBytes = Base64.getEncoder().encode(privateKeyBytes);
        writeFile(privateKeyFilename, privateKeyBytes);
    }

    /**
     * 读文件
     *
     * @param fileName 文件名
     * @return byte[]
     * @throws Exception e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:15:37
     * @version 1.0
     */
    public static byte[] readFile(String fileName) throws IOException {
        return Files.readAllBytes(new File(fileName).toPath());

    }

    /**
     * 写文件
     *
     * @param destPath 路径
     * @param bytes    字节数据
     * @return void
     * @throws IOException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:16:01
     * @version 1.0
     */
    private static void writeFile(String destPath, byte[] bytes) throws IOException {
        File dest = new File(destPath);
        if (!dest.exists()) {
            boolean flag = dest.createNewFile();
            if (!flag) {
                throw new IOException("路径不存在，且创建文件失败！");
            }
        }

        Files.write(dest.toPath(), bytes);

    }


    public static String encryptByPublicKey(String data, String key) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        //初始化公钥,根据给定的编码密钥创建一个新的 X509EncodedKeySpec。
        byte[] byteKey = Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)));
        return Base64Util.encodeToString(bytes);
    }

    /**
     * 构造器私有化
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:16:29
     * @version 1.0
     */
    private RsaUtils() {

    }

}
