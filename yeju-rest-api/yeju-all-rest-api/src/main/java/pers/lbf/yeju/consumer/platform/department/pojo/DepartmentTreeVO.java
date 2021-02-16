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
package pers.lbf.yeju.consumer.platform.department.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/** 部门树结构 vo
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/15 20:37
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DepartmentTreeVO implements Serializable {

    private Long id;
    //部门名称
    private String label;

    @JsonIgnore
    private Long parentId;

    private List<DepartmentTreeVO> children;

    public void addChildren(DepartmentTreeVO child){
        if (children == null) {
            children = new LinkedList<>();
        }
        children.add(child);
    }


    @Override
    public String toString() {
        return "DepartmentTreeVO{" +
                "id=" + id +
                ", name='" + label + '\'' +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<DepartmentTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentTreeVO> children) {
        this.children = children;
    }
}
