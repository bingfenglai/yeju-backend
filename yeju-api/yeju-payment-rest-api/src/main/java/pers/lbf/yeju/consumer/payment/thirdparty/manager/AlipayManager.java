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

package pers.lbf.yeju.consumer.payment.thirdparty.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.consumer.payment.thirdparty.pojo.BizContentForUniTransfer;
import pers.lbf.yeju.consumer.payment.thirdparty.pojo.Participant;
import pers.lbf.yeju.consumer.payment.thirdparty.pojo.TransferParams;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/25 9:33
 */
@Component
@Slf4j
public class AlipayManager {

    @Autowired
    @Qualifier("alipayClient")
    public AlipayClient client;

    /**
     * app 支付
     *
     * @param model
     * @param notifyUrl
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeAppPayResponse appPay(AlipayTradeAppPayModel model, String notifyUrl) throws AlipayApiException {
        AlipayTradeAppPayRequest aliPayRequest = new AlipayTradeAppPayRequest();
        model.setProductCode("QUICK_MSECURITY_PAY");
        aliPayRequest.setNotifyUrl(notifyUrl);
        aliPayRequest.setBizModel(model);
        // 这里和普通的接口调用不同，使用的是sdkExecute

        return client.sdkExecute(aliPayRequest);
    }

    /**
     * 转账接口
     *
     * @param transferParams
     * @return
     * @throws Exception
     */
    public AlipayFundTransUniTransferResponse doTransferNew(TransferParams transferParams) throws Exception {

        String title = (StringUtils.isNotBlank(transferParams.getRemark()) ? transferParams
                .getRemark() : "转账");
        //转账请求入参
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        //转账参数
        BizContentForUniTransfer bizContent = new BizContentForUniTransfer();
        bizContent.setOut_biz_no(transferParams.getOutBizNo());
        //bizContent.setTrans_amount(MathUtil.changeF2Y(Math.abs(Integer.parseInt(transferParams.getAmount()))));
        bizContent.setTrans_amount(BigDecimal.valueOf(Math.abs(Integer.parseInt(transferParams.getAmount()))));
        bizContent.setProduct_code("TRANS_ACCOUNT_NO_PWD");
        bizContent.setBiz_scene("DIRECT_TRANSFER");
        bizContent.setOrder_title(title);
        Participant participant = new Participant();
        participant.setIdentity(transferParams.getPayeeAccount());

        participant.setIdentity_type(transferParams.getPayeeType());
        participant.setName((StringUtils.isNotBlank(transferParams.getPayeeRealName()) ? transferParams
                .getPayeeRealName() : StringUtils.EMPTY));
        bizContent.setPayee_info(participant);
        bizContent.setRemark(title);

        request.setBizContent(JSON.toJSONString(bizContent));

        //转账请求返回
        AlipayFundTransUniTransferResponse response = null;
        try {
            response = client.certificateExecute(request);
        } catch (Exception e) {

            log.info("doTransfer exception，异常信息：{}", e.toString());

            log.info("doTransfer exception，支付宝返回信息：{}", JSONObject.toJSONString(response));

        }

        log.info("doTransfer,AlipayFundTransUniTransferResponse:{}", JSONObject.toJSONString(response));

        return response;
    }
}
