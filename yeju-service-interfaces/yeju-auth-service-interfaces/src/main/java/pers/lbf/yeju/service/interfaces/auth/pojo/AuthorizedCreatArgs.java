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
import io.swagger.annotations.ApiParam;
import pers.lbf.yeju.common.core.args.ICreateArgs;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/11 13:21
 */
@ApiModel("授权接口参数")
public class AuthorizedCreatArgs implements ICreateArgs, Serializable {


    @ApiParam(value = "主体标识")
    @NotNull(message = "授权主体不能为空")
    private String roleId;

    @ApiParam(value = "权限标识列表")
    @NotNull(message = "授权主体不能为空")
    private List<String> authorityIdList;

    @ApiParam(value = "有效期")
    private Date validPeriod;

    @NotNull(message = "创建者不能为空")
    private String createBy;


    private Date createTime;

    /**
     * 授权类型 1 临时权限 2 跟随角色状态的权限
     */
    private String authorizedType;

    private String remark;

    @Override
    public String toString() {
        return "AuthorizedCreatArgs{" +
                "roleId='" + roleId + '\'' +
                ", authorityId=" + authorityIdList +
                ", validPeriod=" + validPeriod +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", authorizedType='" + authorizedType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getAuthorityIdList() {
        return authorityIdList;
    }

    public void setAuthorityIdList(List<String> authorityIdList) {
        this.authorityIdList = authorityIdList;
    }

    public Date getValidPeriod() {
        return validPeriod;
    }

    public void setValidPeriod(Date validPeriod) {
        this.validPeriod = validPeriod;
    }

    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthorizedType() {
        return authorizedType;
    }

    public void setAuthorizedType(String authorizedType) {
        this.authorizedType = authorizedType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
