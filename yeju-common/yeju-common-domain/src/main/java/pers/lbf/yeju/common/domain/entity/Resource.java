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
package pers.lbf.yeju.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * (受保护的资源)权限表，包括菜单和API(TableSystemResources)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-12-13 09:42:12
 */
@TableName("table_system_resources")
public class Resource extends Model<Resource> {
    // 主键
    @TableId
    private Long resourceId;
    // 资源名
    private String resourceName;
    // 资源权限字符串
    private String resourceCode;
    // 资源类型值0菜单1接口2操作（按钮）
    private String resourceType;
    // 父菜单id,仅当资源类型为0时生效
    private Long parentMenuId;
    // 显示顺序，当多个子菜单对应一个父菜单时，需要给定显示顺序
    private Integer orderNumber;
    // 路由地址，当资源为菜单操作时需要记录路由地址
    private String path;
    // 当为组件时，需要给定组件路径
    private String componetPath;
    // 是否缓存0否1是
    private Integer isCache;
    // 资源状态0未启用1启用
    private Integer resourceStatus;
    // 菜单是否显示0不显示1显示
    private Integer visible;
    // 菜单图标地址，当资源类型为菜单时需要指定
    private String icon;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    // 更改者
    private Long changedBy;
    // 备注
    private String remark;
    // 字段版本
    @Version
    private Integer versionNumber;
    // 删除标识
    @TableLogic
    private Integer isDelete;

    private Integer isFrame;

    public Integer getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(Integer isFrame) {
        this.isFrame = isFrame;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponetPath() {
        return componetPath;
    }

    public void setComponetPath(String componetPath) {
        this.componetPath = componetPath;
    }

    public Integer getIsCache() {
        return isCache;
    }

    public void setIsCache(Integer isCache) {
        this.isCache = isCache;
    }

    public Integer getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(Integer resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Long changedBy) {
        this.changedBy = changedBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.resourceId;
    }


    @Override
    public String toString() {
        return "Resources{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", resourceCode='" + resourceCode + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", parentMenuId=" + parentMenuId +
                ", orderNumber=" + orderNumber +
                ", path='" + path + '\'' +
                ", componetPath='" + componetPath + '\'' +
                ", isCache=" + isCache +
                ", resourceStatus=" + resourceStatus +
                ", visible=" + visible +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", changedBy=" + changedBy +
                ", remark='" + remark + '\'' +
                '}';
    }
}
