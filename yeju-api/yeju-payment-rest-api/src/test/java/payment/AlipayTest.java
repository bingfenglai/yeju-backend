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

package payment;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.consumer.payment.start.YejuConsumerPayment;
import pers.lbf.yeju.consumer.payment.thirdparty.config.AlipayConfigInfoBean;
import pers.lbf.yeju.consumer.payment.thirdparty.manager.AlipayManager;

import java.util.UUID;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/25 10:23
 */
@SpringBootTest(classes = YejuConsumerPayment.class)
@RunWith(SpringRunner.class)
@Slf4j
public class AlipayTest {
    @Autowired
    private AlipayManager payManager;

    @Autowired
    private AlipayConfigInfoBean bean;

    @Autowired
    @Qualifier("alipayClient")
    private AlipayClient alipayClient;

    @Test
    public void test() {

        log.info(bean.toString());
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数
//此次只是参数展示，未进行字符串转义，实际情况下请转义
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        try {
            AlipayOpenPublicTemplateMessageIndustryModifyResponse response = alipayClient.execute(request);
            log.info(response.toString());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        try {
            AlipayTradeAppPayResponse response = payManager.appPay(model, null);
            log.info(response.getMsg());
            log.info(response.getCode());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }
}
