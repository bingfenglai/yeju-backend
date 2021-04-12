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
package pers.lbf.yeju.consumer.auth.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.base.security.authorization.pojo.AuthorityInfoBean;
import pers.lbf.yeju.common.core.constant.TokenConstant;
import pers.lbf.yeju.common.util.JwtUtils;
import pers.lbf.yeju.common.util.RsaUtils;
import pers.lbf.yeju.consumer.auth.config.RsaPrivateKeyConfig;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * token建造者
 *
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


    private Long defaultExpiresAt = 60L;

    private Long expireAt;


    private AuthorityInfoBean authorityInfoBean;


    /**
     * @param authorityInfoBean 权限信息载体
     * @return pers.lbf.yeju.gateway.security.pojo.AuthorityInfoToken
     * @throws Exception e
     * @Description //TODO
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/12 23:42
     */
    public String build(@Valid @NotNull(message = "权限主体不能为null")
                                AuthorityInfoBean authorityInfoBean) throws Exception {
        logger.info("私钥路径 {}", rsaPrivateKeyConfig.getPath());

        String token;
        if (this.expireAt == null) {
            this.expireAt = this.defaultExpiresAt;
        }

        if (authorityInfoBean.getTimeUnit() != null && authorityInfoBean.getExpiration() != null) {
            logger.info("自定义过期时间");
            token = JwtUtils.generateToken(authorityInfoBean, RsaUtils.getPrivateKey(rsaPrivateKeyConfig.getPath()), authorityInfoBean.getExpiration(), authorityInfoBean.getTimeUnit());
            return TokenConstant.getPrefixToken() + token;

        }

        if (authorityInfoBean.getTimeUnit() == null || authorityInfoBean.getExpiration() == null) {
            token = JwtUtils.generateTokenExpireInSeconds(
                    authorityInfoBean, RsaUtils.getPrivateKey(rsaPrivateKeyConfig.getPath()),
                    Math.toIntExact(this.expireAt));
            logger.info("账户 {} 生成token {}", authorityInfoBean.getPrincipal(), token != null ? "成功" : "失败");
            return TokenConstant.getPrefixToken() + token;
        }

        if (TimeUnit.SECONDS.equals(authorityInfoBean.getTimeUnit())) {
            token = JwtUtils.generateTokenExpireInSeconds(
                    authorityInfoBean, RsaUtils.getPrivateKey(rsaPrivateKeyConfig.getPath()),
                    authorityInfoBean.getExpiration());
            logger.info("账户{}生成token成功：{}", authorityInfoBean.getPrincipal(), token);
            return TokenConstant.getPrefixToken() + token;
        }

        token = JwtUtils.generateTokenExpireInMinutes(
                authorityInfoBean, RsaUtils.getPrivateKey(rsaPrivateKeyConfig.getPath()),
                authorityInfoBean.getExpiration());
        logger.info("账户{}生成token成功：{}", authorityInfoBean.getPrincipal(), token);

        return TokenConstant.getPrefixToken() + token;
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

    public AuthorityInfoBean getAuthorityInfo() {
        return authorityInfoBean;
    }

    public void setAuthorityInfo(AuthorityInfoBean authorityInfoBean) {
        this.authorityInfoBean = authorityInfoBean;
    }
}
