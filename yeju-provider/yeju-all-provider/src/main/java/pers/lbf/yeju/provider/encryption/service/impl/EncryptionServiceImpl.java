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

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.encryption.service.interfaces.IEncryptionService;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetAesDisponsableKeyResponse;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetRsaPublicDisposableKeyResponse;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;

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
    private IRedisService redisService;

    /**
     * 获取临时的RSA公钥
     *
     * @return
     * @throws ServiceException
     */
    @Override
    public IResult<GetRsaPublicDisposableKeyResponse> getRsaPublicDisposableKey() throws ServiceException {
        return null;
    }

    /**
     * 解密参数并反序列化为class返回
     *
     * @param ciphertext ，密文
     * @param clazz      反序列化类
     * @param key        密钥在缓存中的key
     * @return 反序列化后的参数封装类
     * @throws ServiceException
     */
    @Override
    public IResult<?> paramesRsaDecrypt(String ciphertext, Class<?> clazz, String key) throws ServiceException {
        return null;
    }

    /**
     * 获取AES加密密钥
     *
     * @param clientPublicKey 客户端的公钥
     * @return 经客户端公钥加密过的AES密钥
     * @throws ServiceException
     */
    @Override
    public IResult<GetAesDisponsableKeyResponse> getAesDisponsableKey(String clientPublicKey) throws ServiceException {
        return null;
    }

    /**
     * AES解密参数
     *
     * @param ciphertext 密文
     * @param clazz      参数封装类
     * @param key        密钥在Redis中的Key值
     * @return 解密后的餐宿封装类
     * @throws ServiceException
     */
    @Override
    public IResult<?> paramesAesDecrypt(String ciphertext, Class<?> clazz, String key) throws ServiceException {
        return null;
    }
}