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
 * 数据字典信息表(TableSystemDataDictionaryInfo)表实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-13 15:02:42
 */
@TableName("table_system_data_dictionary_info")
public class DataDictionaryInfo extends Model<DataDictionaryInfo> {
    //主键
    @TableId
    private Long dataDictionaryInfoId;
    //数据字典类型标识
    private Long typeId;
    //排序（优先级）
    private Integer sort;
    //字典标签，实际显示出来的值
    private String dictionaryLabel;
    //字典键值，内部代码例如gender
    private String dictionaryValue;
    //样式属性
    private String cssClass;
    //表格回显样式
    private String listClass;
    //是否为默认属性0否1是
    private Integer isDefault;
    //状态0未启用1启用
    private Integer status;
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
    //更改者
    private Long changedBy;
    //备注
    private String remark;
    //字段版本
    @Version
    private Integer versionNumber;
    //删除标识
    @TableLogic
    private Integer isDelete;


    public Long getDataDictionaryInfoId() {
        return dataDictionaryInfoId;
    }

    public void setDataDictionaryInfoId(Long dataDictionaryInfoId) {
        this.dataDictionaryInfoId = dataDictionaryInfoId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return this.dataDictionaryInfoId;
    }
}