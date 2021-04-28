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
import java.util.Date;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/12 1:36
 */
public class AuthorityInfoBean implements Serializable {
    // 资源名
    private String AuthorityName;
    // 资源权限字符串
    private String AuthorityCode;
    // 资源类型值0菜单目录1抽象权限标识符2操作（按钮）、api
    private final String resourceType = "1";

    // uri
    private String path;

    // 资源状态0未启用1启用
    private Integer resourceStatus;

    // 创建时间
    private Date createTime;
    // 创建者
    private Long createBy;
    // 更新时间
    private Date updateTime;
    // 更改者
    private Long changedBy;
    // 备注
    private String remark;
}
