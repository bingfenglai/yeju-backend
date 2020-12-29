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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.util.JwtUtils;
import pers.lbf.yeju.common.util.RsaUtils;
import pers.lbf.yeju.gateway.config.RsaPrivateKeyConfig;
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
public class AuthorityInfoTokenBuilder {

    @Autowired
    private RsaPrivateKeyConfig rsaPrivateKeyConfig;

    private final Logger logger = LoggerFactory.getLogger(AuthorityInfoTokenBuilder.class);


    private Long defaultExpiresAt  = 60L;

    private Long expireAt;


    private AuthorityInfo authorityInfo;



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
        logger.info("私钥路径 {}", rsaPrivateKeyConfig.getPath());

        String token;
        if (this.expireAt==null){
            this.expireAt = this.defaultExpiresAt;
        }
        if (authorityInfo.getTimeUnit()==null||authorityInfo.getExpiration()==null){
            token = JwtUtils.generateTokenExpireInSeconds(
                    authorityInfo, RsaUtils.getPrivateKey(rsaPrivateKeyConfig.getPath()),
                    Math.toIntExact(this.expireAt));
            logger.info("账户 {} 生成token {}",authorityInfo.getPrincipal(),token!=null? "成功":"失败");
            return token;
        }

        if (TimeUnit.SECONDS.equals(authorityInfo.getTimeUnit())){
            token = JwtUtils.generateTokenExpireInSeconds(
                    authorityInfo,RsaUtils.getPrivateKey(rsaPrivateKeyConfig.getPath()),
                    authorityInfo.getExpiration());
            logger.info("账户{}生成token成功：{}",authorityInfo.getPrincipal(),token);
            return token;
        }

        token =  JwtUtils.generateTokenExpireInMinutes(
            authorityInfo, RsaUtils.getPrivateKey(rsaPrivateKeyConfig.getPath()),
                authorityInfo.getExpiration());
        logger.info("账户{}生成token成功：{}",authorityInfo.getPrincipal(),token);
        return token;
    }

    public String build() throws Exception {
        return this.build(this.getAuthorityInfo());
    }

    public Long getDefaultExpiresAt() {
        return defaultExpiresAt;
    }

    public void setDefaultExpiresAt(Long defaultExpiresAt) {
        this.defaultExpiresAt = defaultExpiresAt;
    }

    public Long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }

    public AuthorityInfo getAuthorityInfo() {
        return authorityInfo;
    }

    public void setAuthorityInfo(AuthorityInfo authorityInfo) {
        this.authorityInfo = authorityInfo;
    }
}
