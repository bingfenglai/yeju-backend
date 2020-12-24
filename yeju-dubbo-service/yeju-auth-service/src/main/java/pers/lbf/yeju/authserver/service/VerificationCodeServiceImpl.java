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
package pers.lbf.yeju.authserver.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.lbf.yeju.authserver.interfaces.dto.VerityDTO;
import pers.lbf.yeju.authserver.interfaces.enums.VerificationCodeTypeEnum;
import pers.lbf.yeju.authserver.interfaces.interfaces.IVerificationCodeService;
import pers.lbf.yeju.common.core.exception.service.rpc.RpcServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.redisserver.service.interfaces.IRedisService;

import java.util.Arrays;

/**验证码服务
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 22:42
 */
@DubboService(interfaceClass = IVerificationCodeService.class)
public class VerificationCodeServiceImpl implements IVerificationCodeService {
    private final Logger logger = LoggerFactory.getLogger(VerificationCodeServiceImpl.class);

    @DubboReference
    private IRedisService redisService;

    @Override
    public <T> IResult<VerityDTO<T>> getVerificationCode(VerificationCodeTypeEnum type) throws RpcServiceException {
        return null;
    }

    @Override
    public IResult<Boolean> verify(String key, String code) throws RpcServiceException {
        String c;
        try {
             c  = (String) redisService.getCacheObject(key);

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            return Result.ok(false);
        }

        boolean flag = code.equals(c);

        return Result.ok(flag);
    }
}
