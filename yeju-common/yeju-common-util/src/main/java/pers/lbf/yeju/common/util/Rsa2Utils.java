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


import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务使用 加密工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/8 16:13
 */
public final class Rsa2Utils {
    public static final String PUBLIC_KEY = "publicKey";

    public static final String PRIVATE_KEY = "privateKey";
    public static final String CHARSET = "UTF-8";
    //密钥算法
    public static final String ALGORITHM_RSA = "RSA";
    //RSA 签名算法
    public static final String ALGORITHM_RSA_SIGN = "SHA256WithRSA";
    public static final int ALGORITHM_RSA_PRIVATE_KEY_LENGTH = 2048;

    private static final String publicKeyFilePath = "rsa2PublicKey.rsa";
    private static final String privateKeyFilePath = "rsa2PrivateKey.rsa";

    private Rsa2Utils() {
    }

    /**
     * 初始化RSA算法密钥对
     *
     * @param keysize RSA1024已经不安全了,建议2048
     * @return 经过Base64编码后的公私钥Map, 键名分别为publicKey和privateKey
     */
    public static Map<String, String> initRSAKey(int keysize) {
        if (keysize != ALGORITHM_RSA_PRIVATE_KEY_LENGTH) {
            throw new IllegalArgumentException("RSA1024已经不安全了,请使用" + ALGORITHM_RSA_PRIVATE_KEY_LENGTH + "初始化RSA密钥对");
        }
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + ALGORITHM_RSA + "]");
        }
        //初始化KeyPairGenerator对象,不要被initialize()源码表面上欺骗,其实这里声明的size是生效的
        kpg.initialize(ALGORITHM_RSA_PRIVATE_KEY_LENGTH);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);
        return keyPairMap;
    }

    /**
     * RSA算法公钥加密数据
     *
     * @param data 待加密的明文字符串
     * @param key  RSA公钥字符串
     * @return RSA公钥加密后的经过Base64编码的密文字符串
     */
    public static String buildRSAEncryptByPublicKey(String data, String key) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法公钥解密数据
     *
     * @param data 待解密的经过Base64编码的密文字符串
     * @param key  RSA公钥字符串
     * @return RSA公钥解密后的明文字符串
     */
    public static String buildRSADecryptByPublicKey(String data, String key) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data)), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法私钥加密数据
     *
     * @param data 待加密的明文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥加密后的经过Base64编码的密文字符串
     */
    public static String buildRSAEncryptByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法私钥解密数据
     *
     * @param data 待解密的经过Base64编码的密文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥解密后的明文字符串
     */
    public static String buildRSADecryptByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data)), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法使用私钥对数据生成数字签名
     *
     * @param data 待签名的明文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥签名后的经过Base64编码的字符串
     */
    public static String buildRSASignByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initSign(privateKey);
            signature.update(data.getBytes(CHARSET));
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException("签名字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法使用公钥校验数字签名
     *
     * @param data 参与签名的明文字符串
     * @param key  RSA公钥字符串
     * @param sign RSA签名得到的经过Base64编码的字符串
     * @return true--验签通过,false--验签未通过
     */
    public static boolean buildRSAverifyByPublicKey(String data, String key, String sign) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initVerify(publicKey);
            signature.update(data.getBytes(CHARSET));
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            throw new RuntimeException("验签字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * RSA算法分段加解密数据
     *
     * @param cipher 初始化了加解密工作模式后的javax.crypto.Cipher对象
     * @param opmode 加解密模式,值为javax.crypto.Cipher.ENCRYPT_MODE/DECRYPT_MODE
     * @return 加密或解密后得到的数据的字节数组
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = ALGORITHM_RSA_PRIVATE_KEY_LENGTH / 8;
        } else {
            maxBlock = ALGORITHM_RSA_PRIVATE_KEY_LENGTH / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }


    public static String getPublicKey() {
        byte[] bytes = FileUtils.readFile(publicKeyFilePath);
        assert bytes != null;
        return new String(bytes);
    }

    public static String getPrivateKey() {
        byte[] bytes = FileUtils.readFile(privateKeyFilePath);
        assert bytes != null;
        return new String(bytes);
    }

    public static void main(String[] args) {
        String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAodov2Lx6pDgu+T1mPGnRMZRmiKeLlFy16QPvw3Ij3+jV59kieQPDL+Vsj4TuBsq9LU5imwSvLbDl2RQ5OQW/F2Vbj1QSDish5i7wSVDDI7YWAgqyT8XUwOWTEMdbfSiPFILUSf8H3QHCZFpVCuAyraKq3DFPZkIpzdbvQU8X8NAngmEtMQNU8gPhTmOJ4M3cDqoVWrWFHlKute5lzwI5V3smglHjpHocC7g0PnptUmvOlraO73humH7u/KfACqawwZ+FA/HlowXRzXfzY/AbaltBwgezB0XRQYZoZRH/1exYsjSgyY40/TznaoDgzboFm1yB54Mh6t401/LG4Pd1KwIDAQAB";
        // String s = Base64.getEncoder().encodeToString(k2.getBytes(StandardCharsets.UTF_8));
        //buildRSAEncryptByPublicKey("ss", key);
        System.out.println(key.length());
        String k2 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAodov2Lx6pDgu+T1mPGnRMZRmiKeLlFy16QPvw3Ij3+jV59kieQPDL+Vsj4TuBsq9LU5imwSvLbDl2RQ5OQW/F2Vbj1QSDish5i7wSVDDI7YWAgqyT8XUwOWTEMdbfSiPFILUSf8H3QHCZFpVCuAyraKq3DFPZkIpzdbvQU8X8NAngmEtMQNU8gPhTmOJ4M3cDqoVWrWFHlKute5lzwI5V3smglHjpHocC7g0PnptUmvOlraO73humH7u/KfACqawwZ+FA/HlowXRzXfzY/AbaltBwgezB0XRQYZoZRH/1exYsjSgyY40/TznaoDgzboFm1yB54Mh6t401/LG4Pd1KwIDAQAB";
        buildRSAEncryptByPublicKey("ss", k2);
        System.out.println(k2.length());
    }


}