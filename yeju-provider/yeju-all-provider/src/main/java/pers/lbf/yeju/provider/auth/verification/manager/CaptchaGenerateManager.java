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
package pers.lbf.yeju.provider.auth.verification.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.provider.auth.verification.config.EasyCaptchaConfig;
import pers.lbf.yeju.provider.auth.verification.constant.VerificationConstant;
import pers.lbf.yeju.provider.auth.verification.strategy.ICaptchaGenerateStrategy;
import pers.lbf.yeju.provider.auth.verification.strategy.impl.ImageCaptchaCodeGenerateStrategy;
import pers.lbf.yeju.provider.auth.verification.strategy.impl.MapImageCapchaCodeGenerateStrategy;
import pers.lbf.yeju.provider.auth.verification.strategy.impl.MapNumberCaptchaCodeGenerateStrategy;
import pers.lbf.yeju.provider.auth.verification.strategy.impl.NumberCaptchaCodeGenerateStrategy;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;
import pers.lbf.yeju.service.interfaces.verificationcode.pojo.VerityDTO;
import pers.lbf.yeju.service.interfaces.verificationcode.status.VerificationCodeTypeEnum;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/25 12:36
 */
@Component
@EnableAsync
@Slf4j
public class CaptchaGenerateManager {

    @Autowired
    private EasyCaptchaConfig captchaConfig;

    @Autowired
    private IRedisService redisService;

    private static final ConcurrentHashMap<VerificationCodeTypeEnum, ICaptchaGenerateStrategy<Map<String, String>>> strategyMap = new ConcurrentHashMap<>();

    public CaptchaGenerateManager() {
        strategyMap.put(VerificationCodeTypeEnum.PICTURE_VERIFICATION_CODE, MapImageCapchaCodeGenerateStrategy.INSTANCE);
        strategyMap.put(VerificationCodeTypeEnum.MOBILE_VERIFICATION_CODE, MapNumberCaptchaCodeGenerateStrategy.INSTANCE);
        strategyMap.put(VerificationCodeTypeEnum.emailCode, MapNumberCaptchaCodeGenerateStrategy.INSTANCE);
    }

    @Deprecated
    public VerityDTO<String> getVerityCode(VerificationCodeTypeEnum type) {
        VerityDTO<String> verity = new VerityDTO<>();
        String code = "";
        String key = captchaConfig.getPrefix();
        if (VerificationCodeTypeEnum.MOBILE_VERIFICATION_CODE.equals(type)) {
            code = NumberCaptchaCodeGenerateStrategy.
                    INSTANCE.
                    generate(captchaConfig.getWidth(), captchaConfig.getHeight(), captchaConfig.getLength());

        }

        if (VerificationCodeTypeEnum.PICTURE_VERIFICATION_CODE.equals(type)) {
            code = ImageCaptchaCodeGenerateStrategy
                    .INSTANCE
                    .generate(captchaConfig.getWidth(), captchaConfig.getHeight(), captchaConfig.getLength());

        }

        verity.setCode(code);
        String s = UUID.randomUUID().toString();
        key = key + s;
        verity.setToken(key);

        return verity;
    }


    public VerityDTO<String> generateVerityCode(VerificationCodeTypeEnum type) throws Exception {
        Map<String, String> map = strategyMap.get(type).generate(captchaConfig.getWidth(), captchaConfig.getHeight(), captchaConfig.getLength());


        String key = captchaConfig.getPrefix() + UUID.randomUUID().toString();

        log.info("将验证码存入缓存");
        redisService.addCacheObject(key, map.get(VerificationConstant.CODE_VALUE), captchaConfig.getTimeout(), TimeUnit.MINUTES);
        log.info("构造dto");
        VerityDTO<String> verity = new VerityDTO<>();

        verity.setToken(key);
        verity.setCode(map.get(VerificationConstant.CODE));
        return verity;
    }

}
