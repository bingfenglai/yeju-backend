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
import pers.lbf.yeju.provider.auth.verification.strategy.ICaptchaGenerateStrategy;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/25 16:11
 */
@Deprecated
public enum NumberCaptchaCodeGenerateStrategy implements ICaptchaGenerateStrategy<String> {

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
    public String generate() {
        SpecCaptcha specCaptcha = new SpecCaptcha();
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        specCaptcha.setLen(6);
        return specCaptcha.text();
    }

    /**
     * 自定义宽高、长度的验证码生成方法
     *
     * @param width 宽
     * @param height 高
     * @param length 验证码长度
     * @return t
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/25 16:12
     */
    @Override
    public String generate(Integer width, Integer height, Integer length) {
        SpecCaptcha specCaptcha = new SpecCaptcha();
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        specCaptcha.setLen(length);
        specCaptcha.setLen(6);
        return specCaptcha.text();
    }
}
