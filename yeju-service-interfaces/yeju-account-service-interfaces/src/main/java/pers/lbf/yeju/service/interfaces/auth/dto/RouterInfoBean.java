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

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/9 20:37
 */
@Deprecated
public class RouterInfoBean implements Serializable {



    private String redirect = "noRedirect";
    protected String component;
    private boolean alwaysShow = true;
    private String name;
    private String path;
    private boolean hidden = false;

    private Meta meta;


    private List<RouterInfoBeanChild> children = new ArrayList<>();

    public void addChildren(RouterInfoBeanChild routerInfoBean){
        this.children.add(routerInfoBean);
    }
    @Override
    public String toString() {
        return "RouterInfoBean{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", hidden=" + hidden +
                ", redirect='" + redirect + '\'' +
                ", component='" + component + '\'' +
                ", alwaysShow=" + alwaysShow +
                ", meta=" + meta +
                ", children=" + children +
                '}';
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

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<RouterInfoBeanChild> getChildren() {
        return children;
    }

    public void setChildren(List<RouterInfoBeanChild> children) {
        this.children = children;
    }
}
