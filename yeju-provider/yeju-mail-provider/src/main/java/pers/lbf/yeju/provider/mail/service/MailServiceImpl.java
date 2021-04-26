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
package pers.lbf.yeju.provider.mail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.provider.mail.config.MailConfigInfoBean;
import pers.lbf.yeju.service.interfaces.mail.IMailService;
import pers.lbf.yeju.service.interfaces.mail.pojo.MailArgs;
import pers.lbf.yeju.service.interfaces.mail.pojo.SimpleMailArgs;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/26 9:04
 */
//@DubboService(interfaceClass = IMailService.class, timeout = 10000, retries = 0)
@Slf4j
@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private MailConfigInfoBean mailConfig;


    /**
     * 发送邮件
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @Async
    public void send(SimpleMailArgs args) throws ServiceException {

        MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        try {
            messageHelper.setFrom("1904454128@qq.com");
            messageHelper.setTo(args.getSendTo());
            messageHelper.setSubject(args.getSubject());
            messageHelper.setText(args.getContent());
            if (args.getSendDate() != null && args.getSendDate().after(new Date())) {
                messageHelper.setSentDate(args.getSendDate());
            }
            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (MessagingException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }

    }

    /**
     * 发送包含附件的邮件
     *
     * @param args
     * @return
     * @throws ServiceException
     */
    @Override
    @Async
    public void send(MailArgs args) throws ServiceException {

    }
}