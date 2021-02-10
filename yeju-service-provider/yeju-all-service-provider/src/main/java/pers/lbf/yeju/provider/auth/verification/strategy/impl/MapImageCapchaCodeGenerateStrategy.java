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

import com.wf.captcha.GifCaptcha;
import pers.lbf.yeju.common.util.YejuStringUtils;
import pers.lbf.yeju.provider.auth.verification.constant.VerificationConstant;
import pers.lbf.yeju.provider.auth.verification.strategy.ICaptchaGenerateStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/28 16:00
 */
public enum MapImageCapchaCodeGenerateStrategy implements ICaptchaGenerateStrategy<Map<String, String>> {
    /**
     * 实例
     */
    INSTANCE;

    private static Integer defaultWidth = 130;
    private static Integer defaultHeight = 48;
    private static Integer defaultLength = 6;
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
        GifCaptcha captcha = new GifCaptcha(130, 48,6);
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put(VerificationConstant.CODE_VALUE,captcha.text());
        map.put(VerificationConstant.CODE,captcha.toBase64());
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

        boolean flag = YejuStringUtils.isOneNull(width, height, length);

        if (flag) {
            return this.generate();
        }

        GifCaptcha captcha = new GifCaptcha(width, height,length);
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put(VerificationConstant.CODE,captcha.toBase64());
        map.put(VerificationConstant.CODE_VALUE,captcha.text());
        return map;
    }
}
