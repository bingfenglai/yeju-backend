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
package pers.lbf.yeju.gateway.security.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.util.JwtUtils;
import pers.lbf.yeju.common.util.RsaUtils;
import pers.lbf.yeju.gateway.security.pojo.AuthorityInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**token建造者
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/12 23:36
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "rsa")
public class AuthorityInfoTokenBuilder {


    private String privateKeyFilePath;

    private final Logger logger = LoggerFactory.getLogger(AuthorityInfoTokenBuilder.class);

    /**
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/12 23:42
     * @param authorityInfo 权限信息载体
     * @return pers.lbf.yeju.gateway.security.pojo.AuthorityInfoToken
     * @throws Exception e
     */
    public  String build( @Valid @NotNull(message = "权限主体不能为null")
                                  AuthorityInfo authorityInfo) throws Exception{
        logger.info("私钥路径 {}",privateKeyFilePath);

        String token;
        if (authorityInfo.getTimeUnit()==null||authorityInfo.getExpiration()==null){
            token = JwtUtils.generateTokenExpireInMinutes(
                    authorityInfo, RsaUtils.getPrivateKey(privateKeyFilePath),
                    60 * 12 * 7);
            logger.info("账户{}生成token成功：{}",authorityInfo.getPrincipal(),token);
            return token;
        }

        if (TimeUnit.SECONDS.equals(authorityInfo.getTimeUnit())){
            token = JwtUtils.generateTokenExpireInSeconds(
                    authorityInfo,RsaUtils.getPrivateKey(privateKeyFilePath),
                    authorityInfo.getExpiration());
            logger.info("账户{}生成token成功：{}",authorityInfo.getPrincipal(),token);
            return token;
        }

        token =  JwtUtils.generateTokenExpireInMinutes(
            authorityInfo, RsaUtils.getPrivateKey(privateKeyFilePath),
                authorityInfo.getExpiration());
        logger.info("账户{}生成token成功：{}",authorityInfo.getPrincipal(),token);
        return token;
    }

    public String getPrivateKeyFilePath() {
        return privateKeyFilePath;
    }

    public void setPrivateKeyFilePath(String privateKeyFilePath) {
        this.privateKeyFilePath = privateKeyFilePath;
    }
}
