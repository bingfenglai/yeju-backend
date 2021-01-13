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
package pers.lbf.yeju.gateway.security.pojo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**token详情信息
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/12 17:51
 */
public class AuthorityInfoBean implements Serializable {

    /**
     * 账号
     */
    private String principal;

    /**
     * 有效时间
     */
    private Integer expiration;

    /**
     * 时间单位。s秒 m分钟
     */
    private TimeUnit timeUnit;

    /**
     * 权限信息
     */
    private List<String> authorityList;

    private List<SimpleGrantedAuthority> simpleGrantedAuthorityList;


    public List<SimpleGrantedAuthority> getSimpleGrantedAuthorityList() {
        return simpleGrantedAuthorityList;
    }

    private void init(List<String> authorityList) {
        for (String s : authorityList) {
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(s));
        }
    }

    public void setSimpleGrantedAuthorityList(List<SimpleGrantedAuthority> simpleGrantedAuthorityList) {
        this.simpleGrantedAuthorityList = simpleGrantedAuthorityList;
    }

    @Override
    public String toString() {
        return "AuthorityInfo{" +
                "Principal='" + principal + '\'' +
                ", authority=" + authorityList +
                '}';
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public List<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<String> authorityList) {
        this.authorityList = authorityList;

    }


    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

}
