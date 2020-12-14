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
package pers.lbf.yeju.gateway.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.gateway.security.builder.AuthorityInfoTokenBuilder;
import pers.lbf.yeju.gateway.security.pojo.AuthorityInfo;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/12 23:45
 */
@Component
public class AuthorizationTokenManager {

    @Autowired
    private AuthorityInfoTokenBuilder builder;


    public String getAuthorityInfoToken(AuthorityInfo authorityInfo) throws Exception{
        return builder.build(authorityInfo);
    }
}
