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
import pers.lbf.yeju.provider.auth.verification.strategy.ICaptchaGenerateStrategy;

/**图片验证码生成策略
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/25 12:44
 */
@Deprecated
public enum ImageCaptchaCodeGenerateStrategy implements ICaptchaGenerateStrategy<String> {

    /**
     * 枚举实现单例模式
     */
    INSTANCE;

    @Override
    public String generate() {
        GifCaptcha captcha = new GifCaptcha(130, 48,6);

        return captcha.toBase64();
    }

    @Override
    public String generate(Integer width, Integer height, Integer length) {
        GifCaptcha captcha = new GifCaptcha(width, height,length);
        return captcha.toBase64();
    }





}
