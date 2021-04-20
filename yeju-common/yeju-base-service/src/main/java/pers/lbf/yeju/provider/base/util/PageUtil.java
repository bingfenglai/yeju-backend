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
package pers.lbf.yeju.provider.base.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.lbf.yeju.common.core.args.IFindPageArgs;

/**
 * 分页查询工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/16 15:18
 */
public class PageUtil {

    /**
     * 获取mybatis plus 分页查询参数 page
     *
     * @param clazz       类型
     * @param currentPage 当前页
     * @param size        每页显示的记录数
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/2/16 15:23
     */
    public static <T> Page<T> getPage(Class<T> clazz, Long currentPage, Long size) {

        Page<T> page = new Page<>();
        page.setPages(currentPage);
        page.setSize(size);

        return page;
    }

    public static <T> Page<T> getPage(Class<T> clazz, IFindPageArgs args) {
        return getPage(clazz, args.getCurrentPage(), args.getSize());
    }


    private PageUtil() {

    }
}
