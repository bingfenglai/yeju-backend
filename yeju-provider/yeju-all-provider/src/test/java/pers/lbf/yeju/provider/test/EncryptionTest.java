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

package pers.lbf.yeju.provider.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.yeju.common.util.AesUtils;
import pers.lbf.yeju.common.util.Rsa2Utils;
import pers.lbf.yeju.encryption.service.interfaces.IEncryptionService;
import pers.lbf.yeju.encryption.service.interfaces.respones.GetAesDisponsableKeyResponse;
import pers.lbf.yeju.provider.start.YejuProviderApplication;
import pers.lbf.yeju.provider.test.pojo.TestRequestParamesBind;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/30 13:10
 */
@SpringBootTest(classes = YejuProviderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class EncryptionTest {

    @DubboReference
    private IEncryptionService encryptionService;


    @Test
    public void test() {
        TestRequestParamesBind parames = new TestRequestParamesBind();
        parames.setUsername("王五");
        parames.setPassword("123456");
        Map<String, String> map = Rsa2Utils.initRSAKey(2048);
        String puk = map.get(Rsa2Utils.PUBLIC_KEY);
        String prk = map.get(Rsa2Utils.PRIVATE_KEY);
        GetAesDisponsableKeyResponse data = encryptionService.getAesDisponsableKey(puk, "123456").getData();
        String key = Rsa2Utils.buildRSADecryptByPrivateKey(data.getAesKey(), prk);
        String jsonString = JSONObject.toJSONString(parames);
        log.info("待加密参数：{}", jsonString);
        log.info("AES key {}", key);
        try {
            String encrypt = AesUtils.encrypt(jsonString, key, data.getIvSting());
            log.info("加密后 {}", encrypt);
            if (data.getCacheKey() == null) {
                log.error("cache key is null");
            }

            if (encryptionService == null) {
                log.error("encryptionService is null");
            }

//            IResult<?> result = encryptionService.paramesAesDecrypt(encrypt, TestRequestParamesBind.class, data.getCacheKey());
//            TestRequestParamesBind bind = (TestRequestParamesBind) result.getData();
//
            //  log.info("解密后 {}", bind.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
