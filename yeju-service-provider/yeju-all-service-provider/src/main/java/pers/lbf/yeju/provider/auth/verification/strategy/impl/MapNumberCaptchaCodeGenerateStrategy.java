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
package pers.lbf.yeju.provider.auth.verification.strategy.impl;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.auth.verification.constant.VerificationConstant;
import pers.lbf.yeju.provider.auth.verification.strategy.ICaptchaGenerateStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/28 16:25
 */
public enum MapNumberCaptchaCodeGenerateStrategy implements ICaptchaGenerateStrategy<Map<String, String>> {
    /**
     * 实例
     */
    INSTANCE;

    /**
     * 默认的验证码生成方法
     *
     * @return t
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/25 16:08
     */
    @Override
    public Map<String, String> generate() {
        SpecCaptcha specCaptcha = new SpecCaptcha();
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        specCaptcha.setLen(6);

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        map.put(VerificationConstant.CODE_VALUE,specCaptcha.text());
        map.put(VerificationConstant.CODE,specCaptcha.text());

        return map;
    }

    /**
     * 自定义宽高、长度的验证码生成方法
     *
     * @param width
     * @param height
     * @param length
     * @return t
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/25 16:12
     */
    @Override
    public Map<String, String> generate(Integer width, Integer height, Integer length) {

        if (YejuStringUtils.isOneNull(width,height,length)){
            return this.generate();
        }

        SpecCaptcha specCaptcha = new SpecCaptcha();
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        specCaptcha.setLen(length);
        specCaptcha.setLen(6);

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        map.put(VerificationConstant.CODE_VALUE,specCaptcha.text());
        map.put(VerificationConstant.CODE,specCaptcha.text());


        return map;
    }
}
