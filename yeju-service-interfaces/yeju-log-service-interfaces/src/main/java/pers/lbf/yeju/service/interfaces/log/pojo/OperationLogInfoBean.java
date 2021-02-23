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
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/22 8:59
 */
public class OperationLogInfoBean implements Serializable {

    private Long operationLogId;
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
     * 操作者id
     */
    private String operatorId;
    
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
     * 操作耗时
     */
    private Long executeTime;
}
