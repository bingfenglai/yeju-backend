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
package pers.lbf.yeju.consumer.auth.builder;

import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.http.server.reactive.ServerHttpRequest;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.consumer.auth.util.HttpUtils;
import pers.lbf.yeju.service.interfaces.session.pojo.OnlineInfoBean;

import java.util.Date;
import java.util.Objects;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/20 22:02
 */
public class OnlineInfoBeanBuilder {

    private String principal;

    private ServerHttpRequest request;

    public OnlineInfoBean build() {
        if (request != null && principal != null && !"".equals(principal)) {
            return this.build(principal, request);
        }
        throw ServiceException.getInstance("subject is not be null", ServiceStatusEnum.UNKNOWN_ERROR.getCode());
    }

    private OnlineInfoBean build(String principal, ServerHttpRequest request) {

        String userAgentStr = Objects.requireNonNull(request.getHeaders().get("User-Agent")).get(0);
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);


        OnlineInfoBean onlineInfoBean = new OnlineInfoBean();
        onlineInfoBean.setPrincipal(principal);
        onlineInfoBean.setIp(HttpUtils.getIpAddress(request));
        onlineInfoBean.setAddress("未知");
        onlineInfoBean.setDate(new Date());
        onlineInfoBean.setClient(userAgent.getBrowser().getName());
        onlineInfoBean.setOs(userAgent.getOperatingSystem().getName());

        return onlineInfoBean;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public void setRequest(ServerHttpRequest request) {
        this.request = request;
    }
}
