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
package pers.lbf.yeju.consumer.auth.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.pojo.Payload;
import pers.lbf.yeju.common.util.JwtUtils;
import pers.lbf.yeju.common.util.RsaUtils;
import pers.lbf.yeju.consumer.auth.builder.AuthorityInfoTokenBuilder;
import pers.lbf.yeju.consumer.auth.config.RsaPublicKeyConfig;
import pers.lbf.yeju.consumer.auth.pojo.AuthorityInfoBean;


/** 认证令牌管理器
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

    @Autowired
    private AuthorityInfoTokenBuilder builder;

    @Deprecated
    public String getAuthorityInfoToken(AuthorityInfoBean authorityInfoBean) throws Exception{
        return builder.build(authorityInfoBean);
    }

    public AuthorityInfoBean getAuthorityInfo(String authenticationToken) throws Exception {

        //判断token是否合法
        boolean flag = authenticationToken.startsWith(TokenConstant.getPrefixToken());

        if (!flag) {
            throw new ServiceException(AuthStatusEnum.NO_TOKEN);
        }
        log.info("公钥路径： {}",rsaPublicKeyConfig.getPath());
        //获取真正的token
        String token = authenticationToken.substring(TokenConstant.getPrefixToken().length());

        Payload<AuthorityInfoBean> payload = JwtUtils.getInfoFromToken(token, RsaUtils.getPublicKey(rsaPublicKeyConfig.getPath()), AuthorityInfoBean.class);
        return payload.getUserInfo();
    }

    public AuthorityInfoTokenBuilder getBuilder(AuthorityInfoBean authorityInfoBean, Long expireAt) {
        builder.setAuthorityInfo(authorityInfoBean);
        builder.setExpireAt(expireAt);
        return builder;
    }
}
