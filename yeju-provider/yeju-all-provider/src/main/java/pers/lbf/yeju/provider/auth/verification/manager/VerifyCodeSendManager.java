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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.provider.auth.verification.config.EasyCaptchaConfig;
import pers.lbf.yeju.provider.auth.verification.sender.VerifyCodeSender;
import pers.lbf.yeju.service.interfaces.mail.pojo.SimpleMailArgs;
import pers.lbf.yeju.service.interfaces.verificationcode.status.VerificationCodeTypeEnum;

/**
 * 验证码发送管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/26 15:18
 */
@Component
@EnableAsync
public class VerifyCodeSendManager {

    @Autowired
    @Qualifier("verifyCodeSenderEmailImpl")
    private VerifyCodeSender senderEmail;

    @Autowired
    private EasyCaptchaConfig captchaConfig;


    public void send(VerificationCodeTypeEnum type, String target, String code) {
        if (VerificationCodeTypeEnum.MOBILE_VERIFICATION_CODE.equals(type)) {
            // TODO
        }

        if (VerificationCodeTypeEnum.emailCode.equals(type)) {
            SimpleMailArgs args = new SimpleMailArgs();
            args.setSendTo(target);
            args.setSubject("【椰居】验证码 Please verify your device");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append("<div>")
                    .append("【椰居】您的验证码为")
                    .append("<b>")
                    .append(code)
                    .append("</b>")
                    .append("<br/>")
                    .append("<b style=\"color: #DD6161;\">")
                    .append(captchaConfig.getTimeout())
                    .append("</b>")
                    .append(" 分钟内有效。如果不是您本人操作，请忽略这封邮件")
                    .append("</div>")
                    .append("<br/><br/>")
                    .append("<div>")
                    .append("[Yeju] your verification code is ")
                    .append("<b>")
                    .append(code)
                    .append("</b>")
                    .append("<br/>")
                    .append("It's effective in ")
                    .append("<b style=\"color: #DD6161;\">")
                    .append(captchaConfig.getTimeout())
                    .append("</b>")
                    .append(" minutes. if It not your own operation, please ignore this email.")
                    .append("</div>");
            args.setContent(stringBuilder.toString());
            args.setType("1");
            senderEmail.send(args);
        }
    }

}
