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
package pers.lbf.yeju.service.interfaces.log.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/29 11:19
 */
public class AddOperationLogRequestBean implements Serializable {

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型0其他1新增2修改3删除
     */
    private Integer businessType;

    /**
     * 方法名称
     */
    private String method;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 操作类型0其他1平台业务员2客户3移动端客户
     */
    private String operatorType;
    /**
     * 操作者名字
     */
    private String operatorName;

    /**
     * 请求路径
     */
    private String url;
    /**
     * 请求ip
     */
    private String ip;
    /**
     * 地点
     */
    private String location;
    /**
     * 请求参数
     */
    private String param;
    /**
     * 返回的结果
     */
    private String result;
    /**
     * 操作状态0正常1异常
     */
    private Integer operationStatus;
    /**
     * 错误消息
     */
    private String errorMessage;
    /**
     * 操作时间
     */
    private Date operationTime;
    /**
     * 访问ip最后一位数字，用作分区标识
     */
    private Integer lastIpNumber;

    /**
     * 操作者Account
     */
    private String operatorAccount;

    private Long executeTime;

    public Long getExecuteTime() {
        return executeTime;
    }


    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    @Override
    public String toString() {
        return "AddOperationLogRequestBean{" +
                "title='" + title + '\'' +
                ", businessType=" + businessType +
                ", method='" + method + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", operatorType='" + operatorType + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", param='" + param + '\'' +
                ", result='" + result + '\'' +
                ", operationStatus=" + operationStatus +
                ", errorMessage='" + errorMessage + '\'' +
                ", operationTime=" + operationTime +
                ", operatorId='" + operatorAccount + '\'' +
                ", executeTime=" + executeTime +
                '}';
    }

    public String getOperatorAccount() {
        return operatorAccount;
    }

    public void setOperatorAccount(String operatorAccount) {
        this.operatorAccount = operatorAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(Integer operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getLastIpNumber() {
        return lastIpNumber;
    }

    public void setLastIpNumber(Integer lastIpNumber) {
        this.lastIpNumber = lastIpNumber;
    }
}
