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
package pers.lbf.yeju.consumer.base.security.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.pojo.Payload;
import pers.lbf.yeju.common.util.JwtUtils;
import pers.lbf.yeju.common.util.RsaUtils;
import pers.lbf.yeju.consumer.base.security.config.RsaPublicKeyConfig;
import pers.lbf.yeju.consumer.base.security.pojo.AuthorityInfo;


/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/12 23:45
 */
@Component
@Slf4j
public class AuthorizationTokenManager {

    @Autowired
    private RsaPublicKeyConfig rsaPublicKeyConfig;


    public AuthorityInfo getAuthorityInfo(String authenticationToken) throws Exception {

        //判断token是否合法
        boolean flag = authenticationToken.startsWith(TokenConstant.getPrefixToken());

        if (!flag) {
            throw new ServiceException(AuthStatusEnum.NO_TOKEN);
        }
        log.info("公钥路径： {}", rsaPublicKeyConfig.getPath());
        //获取真正的token
        String token = authenticationToken.substring(TokenConstant.getPrefixToken().length());

        Payload<AuthorityInfo> payload = JwtUtils.getInfoFromToken(token, RsaUtils.getPublicKey(rsaPublicKeyConfig.getPath()), AuthorityInfo.class);
        return payload.getUserInfo();
    }


}
