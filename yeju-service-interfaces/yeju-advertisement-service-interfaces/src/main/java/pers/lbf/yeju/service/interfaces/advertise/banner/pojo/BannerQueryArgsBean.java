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

package pers.lbf.yeju.service.interfaces.advertise.banner.pojo;

import pers.lbf.yeju.common.core.args.IFindPageArgs;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 轮播图条件查询参数封装类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/15 19:47
 */
public class BannerQueryArgsBean implements Serializable, IFindPageArgs {

    @NotNull(message = "当前页不能为空")
    @Min(1)
    private Long currentPage;

    @NotNull(message = "每页显示大小不能为空")
    @Min(1)
    @Max(50)
    private Long size;

    private String title;
    /**
     * 状态
     */
    private String bannerStatus;

    private Date[] between;

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBannerStatus() {
        return bannerStatus;
    }

    public void setBannerStatus(String bannerStatus) {
        this.bannerStatus = bannerStatus;
    }

    public Date[] getBetween() {
        return between;
    }

    public void setBetween(Date[] between) {
        this.between = between;
    }

    @Override
    public Long getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public Long getSize() {
        return this.size;
    }


    @Override
    public String toString() {
        return "BannerQueryArgsBean{" +
                "currentPage=" + currentPage +
                ", size=" + size +
                ", title='" + title + '\'' +
                ", bannerStatus='" + bannerStatus + '\'' +
                ", between=" + Arrays.toString(between) +
                '}';
    }
}
