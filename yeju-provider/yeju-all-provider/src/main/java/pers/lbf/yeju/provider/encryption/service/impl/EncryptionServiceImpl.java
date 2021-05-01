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
package pers.lbf.yeju.provider.encryption.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.util.AesUtils;
import pers.lbf.yeju.common.util.Rsa2Utils;
import pers.lbf.yeju.common.util.RsaUtils;
import pers.lbf.yeju.encryption.service.interfaces.IEncryptionService;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetAesDisponsableKeyResponse;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetRsaPublicDisposableKeyResponse;
import pers.lbf.yeju.encryption.service.interfaces.status.EncryptionServiceStatus;
import pers.lbf.yeju.provider.encryption.config.EncryptionAesConfig;
import pers.lbf.yeju.provider.encryption.config.EncryptionRsaConfig;
import pers.lbf.yeju.provider.encryption.manage.EncryptionCacheKeyManager;
import pers.lbf.yeju.provider.encryption.pojo.AesCacheBody;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/29 15:18
 */
@DubboService(interfaceClass = IEncryptionService.class, timeout = 10000, retries = 0)
@Slf4j
public class EncryptionServiceImpl implements IEncryptionService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private EncryptionRsaConfig encryptionRsaConfig;

    @Autowired
    private EncryptionAesConfig aesConfig;

    @Autowired
    private EncryptionCacheKeyManager cachedKeyManager;

    /**
     * 获取临时的RSA公钥
     *
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<GetRsaPublicDisposableKeyResponse> getRsaPublicDisposableKey(String account) throws ServiceException {
        GetRsaPublicDisposableKeyResponse response = new GetRsaPublicDisposableKeyResponse();

        Map<String, String> map = Rsa2Utils.initRSAKey(encryptionRsaConfig.getSecretKeySize());
        String publicKey = map.get(Rsa2Utils.PUBLIC_KEY);
        String privateKey = map.get(Rsa2Utils.PRIVATE_KEY);

        String rsaCacheKey = cachedKeyManager.getRsaCacheKey(account);
        redisTemplate.opsForValue().set(rsaCacheKey, publicKey, Long.valueOf(encryptionRsaConfig.getKeyTimeout()), TimeUnit.MINUTES);
        response.setPublicKey(publicKey);
        response.setKey(rsaCacheKey);

        return Result.ok(response);
    }

    /**
     * 解密参数并反序列化为class返回
     *
     * @param ciphertext ，json密文
     * @param clazz      反序列化类
     * @param key        密钥在缓存中的key
     * @return 反序列化后的参数封装类
     * @throws ServiceException
     */
    @Override
    public IResult<?> paramesRsaDecrypt(String ciphertext, Class<?> clazz, String key) throws ServiceException {
        Boolean hasKeyFlag = redisTemplate.hasKey(key);
        if (hasKeyFlag == null || !hasKeyFlag) {
            throw ServiceException.getInstance(EncryptionServiceStatus.keyHasExpired);
        }

        String privateKey = (String) redisTemplate.opsForValue().get(key);
        String jsonString = Rsa2Utils.buildRSADecryptByPrivateKey(ciphertext, privateKey);
        Object obj = JSONObject.parseObject(jsonString, clazz);
        return Result.ok(obj);
    }

    /**
     * 获取AES加密密钥
     *
     * @param clientPublicKey 客户端的公钥
     * @return 经客户端公钥加密过的AES密钥
     * @throws ServiceException
     */
    @Override
    public IResult<GetAesDisponsableKeyResponse> getAesDisponsableKey(String clientPublicKey, String account) throws ServiceException {
        GetAesDisponsableKeyResponse response = new GetAesDisponsableKeyResponse();
        String uuid16 = AesUtils.getUUID16();
        try {
            String key = AesUtils.generateKey(uuid16);
            log.debug("aes key {}", key);
            String rsaKey = RsaUtils.encryptByPublicKey(key, clientPublicKey);

            AesCacheBody aesCacheBody = new AesCacheBody();
            aesCacheBody.setIvString(uuid16);
            aesCacheBody.setKey(key);
            String aesCacheKey = cachedKeyManager.getAesCacheKey(account);

            redisTemplate.opsForValue().set(aesCacheKey, aesCacheBody, Long.valueOf(aesConfig.getKeyTimeout()), TimeUnit.MINUTES);
            log.info("AES 密钥：{}", key);
            log.info("AES iv: {}", uuid16);
            response.setAesKey(rsaKey);
            response.setIvSting(uuid16);
            response.setCacheKey(aesCacheKey);
            return Result.ok(response);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw ServiceException.getInstance(e.getMessage(), ServiceStatusEnum.UNKNOWN_ERROR.getCode());
        }

    }

    /**
     * AES解密参数
     *
     * @param ciphertext 密文
     * @param cacheKey   密钥在Redis中的Key值
     * @return 解密后的餐宿封装类
     * @throws ServiceException
     */
    @Override
    public IResult<String> paramesAesDecrypt(String ciphertext, String cacheKey) throws ServiceException {
        Boolean hasKeyFlag = redisTemplate.hasKey(cacheKey);
        if (hasKeyFlag == null || !hasKeyFlag) {
            throw ServiceException.getInstance(EncryptionServiceStatus.keyHasExpired);
        }
        AesCacheBody body = (AesCacheBody) redisTemplate.opsForValue().get(cacheKey);

        try {
            assert body != null;
            log.debug("cache body {} {}", body.toString(), body.getIvString().length());
            String data = AesUtils.decrypt(ciphertext, body.getKey(), body.getIvString());
            log.debug("data {}", data);


            return Result.ok(data);
        } catch (Exception e) {

            e.printStackTrace();
            throw ServiceException.getInstance(EncryptionServiceStatus.failedToDecryptEncryptedData);
        }


    }


}