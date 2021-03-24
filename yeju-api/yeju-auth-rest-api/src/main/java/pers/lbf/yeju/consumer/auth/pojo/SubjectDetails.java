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
package pers.lbf.yeju.consumer.auth.pojo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pers.lbf.yeju.common.core.constant.AccountStatusConstant;

import java.util.List;
import java.util.Objects;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description 当前登录对象封装类
 * @date 2020/12/12 18:05
 */
public class SubjectDetails extends SimpleSubject implements UserDetails {

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.getCertificate();
    }

    @Override
    public String getUsername() {

        return this.getPrincipal();
    }

    @Override
    public boolean isAccountNonExpired() {
        return Objects.equals(this.getAccountStatus(), AccountStatusConstant.ENABLE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return Objects.equals(this.getAccountStatus(), AccountStatusConstant.ENABLE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.getAccountStatus(), AccountStatusConstant.ENABLE);
    }
}
