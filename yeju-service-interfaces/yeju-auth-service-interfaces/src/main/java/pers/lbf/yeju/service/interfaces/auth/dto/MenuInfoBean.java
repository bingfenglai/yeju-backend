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
package pers.lbf.yeju.service.interfaces.auth.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 菜单信息封装类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/9 11:59
 */
public class MenuInfoBean implements Serializable {

    // 主键
    private Long resourceId;
    // 资源名
    private String resourceName;
    // 资源权限字符串
    private String resourceCode;

    // 父菜单id,仅当资源类型为0时生效
    private Long parentMenuId;
    // 显示顺序，当多个子菜单对应一个父菜单时，需要给定显示顺序
    private Integer orderNumber;
    // 路由地址，当资源为菜单操作时需要记录路由地址
    private String path;
    // 当为组件时，需要给定组件路径
    private String component;
    // 是否缓存0否1是
    private Boolean isCache;
    // 资源状态0未启用1启用
    private Integer resourceStatus;
    // 菜单是否显示0不显示1显示
    private Boolean visible;
    // 菜单图标地址，当资源类型为菜单时需要指定
    private String icon;

    /**
     * 菜单对应的次级子菜单
     */
    private List<MenuInfoBean> children = new ArrayList<>();


    public void addChildren(MenuInfoBean menuInfoBean){
        children.add(menuInfoBean);
    }


    @Override
    public String toString() {
        return "MenuInfoBean{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", resourceCode='" + resourceCode + '\'' +
                ", parentMenuId=" + parentMenuId +
                ", orderNumber=" + orderNumber +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", isCache=" + isCache +
                ", resourceStatus=" + resourceStatus +
                ", visible=" + visible +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Boolean getCache() {
        return isCache;
    }

    public void setCache(Boolean cache) {
        isCache = cache;
    }

    public Integer getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(Integer resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuInfoBean> getChildren() {
        return children;
    }

    public void setChildren(List<MenuInfoBean> children) {
        this.children = children;
    }
}
