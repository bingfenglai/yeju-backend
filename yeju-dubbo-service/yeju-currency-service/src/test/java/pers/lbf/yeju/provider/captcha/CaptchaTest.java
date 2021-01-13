package pers.lbf.yeju.provider.captcha;/*
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

import com.wf.captcha.GifCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.yeju.consumer.authrestapi.interfaces.dto.VerityDTO;
import pers.lbf.yeju.consumer.authrestapi.interfaces.enums.VerificationCodeTypeEnum;
import pers.lbf.yeju.provider.currency.start.YejuCurrencyServiceProviderApplication;
import pers.lbf.yeju.provider.currency.verification.manager.CaptchaGenerateManager;
import pers.lbf.yeju.redisserver.service.interfaces.IRedisService;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/25 12:50
 */
@SpringBootTest(classes = YejuCurrencyServiceProviderApplication.class)
@Slf4j
public class CaptchaTest {

    @Autowired
    private CaptchaGenerateManager captchaGenerateManager;

    @DubboReference
    private IRedisService redisService;


    @Test
    public void test1(){
        GifCaptcha captcha = new GifCaptcha(130, 48);

        System.out.println(captcha.toBase64());
    }

    @Test
    public void test2(){
        log.info("验证码生成测试开始");

        VerityDTO<String> verityDTO = null;
        try {
            verityDTO = captchaGenerateManager.generateVerityCode(VerificationCodeTypeEnum.PICTURE_VERIFICATION_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert verityDTO != null;
        String code = verityDTO.getCode();

        log.info("图片验证码生成： {}",code==null?"失败":"成功");
        log.info("key: {}",verityDTO.getToken());
        try {
            log.info("value: {}",redisService.getCacheObject(verityDTO.getToken()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        log.info("数字验证码生成测试开始");

        VerityDTO<String> verityDTO = null;
        try {
            verityDTO = captchaGenerateManager.generateVerityCode(VerificationCodeTypeEnum.MOBILE_VERIFICATION_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert verityDTO != null;
        String code = verityDTO.getCode();

        log.info("数字验证码生成： {}",code==null?"失败":"成功");
        log.info("code: {}",code);
        String value = null;
        try {
            value = String.valueOf(redisService.getCacheObject(verityDTO.getToken()));
            log.info("value: {}",value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
