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
package pers.lbf.yeju.service.interfaces.platfrom.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/14 19:47
 */
public class SimpleDepartmentInfoBean implements Serializable {

    private Long departmentId;

    private Long parentId;

    //部门名称
    private String name;
    //负责人id
    private Long leaderId;
    //手机号
    private String phoneNumber;
    //手机区号，比如中国是+86，详见属性表

    private Long phoneNumberPrefix;
    //部门（团队）邮箱
    private String email;
    //部门状态0未启用1启用
    private String departmentStatus;

    private String departmentStatusStr;
    //创建时间
    private Date createTime;

    private SimpleEmployeeInfoBean leader;

    private List<SimpleDepartmentInfoBean> children;

    private List<SimplePostInfoBean> postList;



    public void addPost(SimplePostInfoBean post){
        if (postList == null) {
            postList = new LinkedList<>();
        }
        postList.add(post);
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void addChildren(SimpleDepartmentInfoBean child){
        if (children == null) {
            children = new LinkedList<>();
        }

        children.add(child);
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getPhoneNumberPrefix() {
        return phoneNumberPrefix;
    }

    public void setPhoneNumberPrefix(Long phoneNumberPrefix) {
        this.phoneNumberPrefix = phoneNumberPrefix;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(String departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    public String getDepartmentStatusStr() {
        return departmentStatusStr;
    }

    public void setDepartmentStatusStr(String departmentStatusStr) {
        this.departmentStatusStr = departmentStatusStr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SimpleEmployeeInfoBean getLeader() {
        return leader;
    }

    public void setLeader(SimpleEmployeeInfoBean leader) {
        this.leader = leader;
    }

    public List<SimplePostInfoBean> getPostList() {
        return postList;
    }

    public void setPostList(List<SimplePostInfoBean> postList) {
        this.postList = postList;
    }
    public List<SimpleDepartmentInfoBean> getChildren() {
        return children;
    }

    public void setChildren(List<SimpleDepartmentInfoBean> children) {
        this.children = children;
    }
}
