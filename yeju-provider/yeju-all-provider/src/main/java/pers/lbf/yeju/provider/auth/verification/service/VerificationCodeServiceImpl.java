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
package pers.lbf.yeju.provider.auth.verification.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.provider.auth.verification.config.EasyCaptchaConfig;
import pers.lbf.yeju.provider.auth.verification.enums.VerificationCodeStatusEnum;
import pers.lbf.yeju.provider.auth.verification.manager.CaptchaGenerateManager;
import pers.lbf.yeju.provider.auth.verification.manager.VerifyCodeSendManager;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;
import pers.lbf.yeju.service.interfaces.verificationcode.IVerificationCodeService;
import pers.lbf.yeju.service.interfaces.verificationcode.pojo.VerityDTO;
import pers.lbf.yeju.service.interfaces.verificationcode.status.VerificationCodeTypeEnum;

import java.util.Arrays;

/**
 * 验证码服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 22:42
 */
@DubboService(interfaceClass = IVerificationCodeService.class, timeout = 10000, retries = 0)
public class VerificationCodeServiceImpl implements IVerificationCodeService {
    private final Logger logger = LoggerFactory.getLogger(VerificationCodeServiceImpl.class);

    @Autowired
    private IRedisService redisService;

    @Autowired
    private CaptchaGenerateManager captchaGenerateManager;

    @Autowired
    private EasyCaptchaConfig captchaConfig;

    @Autowired
    private VerifyCodeSendManager verifyCodeSendManager;

    @Override
    public IResult<VerityDTO<String>> getVerificationCode(VerificationCodeTypeEnum type) throws ServiceException {

        VerityDTO<String> verityDTO;
        try {
            verityDTO = captchaGenerateManager.generateVerityCode(type);
        } catch (Exception e) {
            throw ServiceException.getInstance(VerificationCodeStatusEnum.GEN_FAILED);
        }

        if (verityDTO.getCode().length() == captchaConfig.getLength()) {
            verityDTO.setCode("");
        }
        return Result.ok(verityDTO);
    }

    @Override
    public IResult<VerityDTO<String>> getVerificationCode(VerificationCodeTypeEnum type, String target) {

        try {
            VerityDTO<String> verityDTO = captchaGenerateManager.generateVerityCode(type);
            verifyCodeSendManager.send(type, target, verityDTO.getCode());

            if (verityDTO.getCode().length() == captchaConfig.getLength()) {
                verityDTO.setCode("");
            }
            return Result.ok(verityDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.ok(null);
    }

    @Override
    public IResult<Boolean> verify(String key, String code) throws ServiceException {
        String c;
        try {
            c = (String) redisService.getCacheObject(key);

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            return Result.ok(false);
        }

        boolean flag = code.equalsIgnoreCase(c);

        return Result.ok(flag);
    }
}
