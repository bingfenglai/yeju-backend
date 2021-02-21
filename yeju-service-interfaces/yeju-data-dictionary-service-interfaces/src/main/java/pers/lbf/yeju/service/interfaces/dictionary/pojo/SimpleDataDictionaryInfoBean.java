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
package pers.lbf.yeju.service.interfaces.dictionary.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/13 15:26
 */
public class SimpleDataDictionaryInfoBean implements Serializable {

    private Long id;

    private String dictionaryLabel;
    //字典键值，内部代码例如gender
    private String dictionaryValue;
    //样式属性
    private String cssClass;
    //表格回显样式
    private String listClass;
    //是否为默认属性0否1是
    private boolean defaultFlags;
    //状态0未启用1启用
    private Integer status;

    //创建时间
    private Date createTime;

    //排序（优先级）
    private Integer sort;
    //备注
    private String remark;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictionaryLabel() {
        return dictionaryLabel;
    }

    public void setDictionaryLabel(String dictionaryLabel) {
        this.dictionaryLabel = dictionaryLabel;
    }

    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getListClass() {
        return listClass;
    }

    public void setListClass(String listClass) {
        this.listClass = listClass;
    }

    public boolean isDefaultFlags() {
        return defaultFlags;
    }

    public void setDefaultFlags(boolean defaultFlags) {
        this.defaultFlags = defaultFlags;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
