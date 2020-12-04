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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Description TODO
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/11/30 12:01
 */
public class RsaUtils {
    //密钥默认长度
    private static final int DEFAULT_KEY_SIZE = 2048;
    private static final String CHARSET_NAME= "UTF-8";

    //算法
    private static final String RSA = "RSA";

    //加密填充方式
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    // 当前秘钥支持加密的最大字节数
    public static final int DEFAULT_BUFFERSIZE = (DEFAULT_KEY_SIZE / 8) - 11;

    private static final String PUBLIC_KEY = "";

    // 当要加密的内容超过bufferSize，则采用partSplit进行分块加密
    public static final byte[] DEFAULT_SPLIT = "#PART#".getBytes();




    /**从文件中读取公钥
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:10:15
     * @param filename 公钥保存路径，相对于classpath
     * @return java.security.PublicKey 公钥对象
     * @throws Exception e
     * @version 1.0
     */
    public static PublicKey getPublicKey(String filename) throws Exception {

        byte[] bytes = readFile(filename);
        return getPublicKey(bytes);
    }


    /**从文件中读取密钥
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:12:01
     * @param filename 私钥保存路径，相对于classpath
     * @return java.security.PrivateKey 私钥对象
     * @throws Exception e
     * @version 1.0
     */
    public static PrivateKey getPrivateKey(String filename) throws Exception {
        byte[] bytes = readFile(filename);
        return getPrivateKey(bytes);

    }

    /**
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:12:59
     * @param bytes 公钥的字节形式
     * @return java.security.PublicKey 公钥对象
     * @throws Exception e
     * @version 1.0
     */
    private static PublicKey getPublicKey(byte[] bytes) throws Exception {
        bytes = Base64.getDecoder().decode(bytes);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance(RSA);
        return factory.generatePublic(spec);

    }


    /**获取密钥
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:14:02
     * @param bytes 私钥的字节形式
     * @return java.security.PrivateKey
     * @throws InvalidKeySpecException e
     * @throws NoSuchAlgorithmException e
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
     *@author 赖柄沣 bingfengdev@aliyun.com
     *@date 2020-09-04 13:14:02
     * @param publicKeyFilename  公钥文件路径
     * @param privateKeyFilename 私钥文件路径
     * @param secret             生成密钥的密文
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

    /**读文件
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:15:37
     * @param fileName 文件名
     * @return byte[]
     * @throws Exception e
     * @version 1.0
     */
    public static byte[] readFile(String fileName) throws Exception {
        return Files.readAllBytes(new File(fileName).toPath());

    }

    /**写文件
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:16:01
     * @param destPath 路径
     * @param bytes 字节数据
     * @return  void
     * @throws IOException e
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

    /**构造器私有化
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-04 13:16:29
     * @version 1.0
     */
    private RsaUtils() {

    }

}
