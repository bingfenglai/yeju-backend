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
package pers.lbf.yeju.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**为了方便后期获取token中的用户信息，
 * 将token中载荷部分单独封装成一个对象
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/30 14:50
 */
public class Payload<T> implements Serializable {
    /**
     * token id
     */
    private String id;

    /**
     * 用户信息（用户名、角色...）
     */
    private T userInfo;

    /**
     * 令牌过期时间
     */
    private Date expiration;


    @Override
    public String toString() {
        return "Payload{" +
                "id='" + id + '\'' +
                ", userInfo=" + userInfo +
                ", expiration=" + expiration +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public T getUserInfo() {
        return userInfo;
    }
}
