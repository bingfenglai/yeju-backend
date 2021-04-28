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

package pers.lbf.yeju.service.interfaces.auth.pojo;

import io.swagger.annotations.ApiModel;
import pers.lbf.yeju.common.core.args.ICreateArgs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 同步授权信息到账户-资源表
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/11 22:59
 */
@ApiModel("同步授权信息到账户-资源表")
public class SynchronousAuthorizedCreateArgs implements ICreateArgs, Serializable {

    List<String> authorityIdList;

    String roleId;

    private Date createTime;
    /**
     * 创建者
     */
    private Long createBy;

    @Override
    public String toString() {
        return "SynchronousAuthorizedCreateArgs{" +
                "authorityIdList=" + authorityIdList +
                ", roleId='" + roleId + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                '}';
    }

    public List<String> getAuthorityIdList() {
        return authorityIdList;
    }

    public void setAuthorityIdList(List<String> authorityIdList) {
        this.authorityIdList = authorityIdList;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateBy(String account) {
        createBy = Long.valueOf(account);
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
}
