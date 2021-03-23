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
package pers.lbf.yeju.base.security.authorization.manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.base.security.authorization.config.RsaPublicKeyConfig;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.pojo.Payload;
import pers.lbf.yeju.common.util.JwtUtils;
import pers.lbf.yeju.common.util.RsaUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/12 23:45
 */
@Component
public class AuthorizationTokenManager {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationTokenManager.class);

    @Autowired
    private RsaPublicKeyConfig rsaPublicKeyConfig;


    public AuthorityInfoBean getAuthorityInfo(String authenticationToken) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {

        //判断token是否合法
        boolean flag = authenticationToken.startsWith(TokenConstant.getPrefixToken());

        if (!flag) {
            throw new ServiceException(AuthStatusEnum.NO_TOKEN);
        }
        log.info("公钥路径： {}", rsaPublicKeyConfig.getPath());
        //获取真正的token
        String token = authenticationToken.substring(TokenConstant.getPrefixToken().length());

        Payload<AuthorityInfoBean> payload = JwtUtils.getInfoFromToken(token, RsaUtils.getPublicKey(rsaPublicKeyConfig.getPath()), AuthorityInfoBean.class);
        return payload.getUserInfo();
    }


}
