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

package pers.lbf.yeju.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 需要进行接口防刷拦截的配置
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/20 21:35
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "yeju.security.api-access.limit")
public class ApiAccessLimitConfig {

    private List<String> path;

    private List<Integer> second;

    private List<Integer> count;

    private ConcurrentHashMap<String, Integer[]> pathMaps;

    public ConcurrentHashMap<String, Integer[]> getPathMaps() {
        return pathMaps;
    }

    public void setPathMaps(ConcurrentHashMap<String, Integer[]> pathMaps) {
        this.pathMaps = pathMaps;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<Integer> getSecond() {
        return second;
    }

    public void setSecond(List<Integer> second) {
        this.second = second;
    }

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }
}
