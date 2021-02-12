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
package pers.lbf.yeju.consumer.platform.resource.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/** 路由Vo对象
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/12 22:48
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL) //null 值不进行序列化
public class RouterVO implements Serializable {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long parentId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;

    private String component;
    /**
     *  true  当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private boolean alwaysShow;

    /**
     * name:'router-name' 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
     */
    private String name;

    private String path;
    private boolean hidden;

    private MetaVO meta;

    private List<RouterVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RouterVO> getChildren() {
        return children;
    }

    public void setChildren(List<RouterVO> children) {
        this.children = children;
    }

    public void addChildren(RouterVO routerVO){
        if (this.children!=null){
            children.add(routerVO);
        }else{
            children = new LinkedList<>();
            children.add(routerVO);
        }

    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public boolean isAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public MetaVO getMeta() {
        return meta;
    }

    public void setMeta(MetaVO meta) {
        this.meta = meta;
    }
}
