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

package pers.lbf.yeju.encryption.service.interfaces;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetAesDisponsableKeyResponse;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetRsaPublicDisposableKeyResponse;

/**
 * 加密服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/29 11:41
 */
public interface IEncryptionService {


    /**
     * 获取临时的RSA公钥
     *
     * @return
     * @throws ServiceException
     */
    IResult<GetRsaPublicDisposableKeyResponse> getRsaPublicDisposableKey(String account) throws ServiceException;

    /**
     * 解密参数并反序列化为class返回
     *
     * @param ciphertext ，密文
     * @param clazz      反序列化类
     * @param key        密钥在缓存中的key
     * @return 反序列化后的参数封装类
     * @throws ServiceException
     */
    IResult<?> paramesRsaDecrypt(String ciphertext, Class<?> clazz, String key) throws ServiceException;


    /**
     * 获取AES加密密钥
     *
     * @param clientPublicKey 客户端的公钥
     * @return 经客户端公钥加密过的AES密钥
     * @throws ServiceException
     */
    IResult<GetAesDisponsableKeyResponse> getAesDisponsableKey(String clientPublicKey, String account) throws ServiceException;

    /**
     * AES解密参数
     *
     * @param ciphertext 密文
     * @param cacheKey   密钥在Redis中的Key值
     * @return 解密后的餐宿封装类
     * @throws ServiceException
     */
    IResult<String> paramesAesDecrypt(String ciphertext, String cacheKey) throws ServiceException;
}
