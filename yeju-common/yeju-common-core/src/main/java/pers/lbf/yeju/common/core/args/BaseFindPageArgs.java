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
package pers.lbf.yeju.common.core.args;

import java.io.Serializable;

/**
 * 基础分页查询参数类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/13 14:40
 */
public class BaseFindPageArgs implements IFindPageArgs, Serializable {
    protected Long currentPage = 1L;
    protected Long size = 10L;

    @Override
    public Long getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public Long getSize() {
        return this.size;
    }

    public void setCurrentPage(Long currentPage) {
        if (currentPage >= this.currentPage) {
            this.currentPage = currentPage;
        }
    }

    public void setSize(Long size) {

        long minSize = 1L;
        long maxSize = 50L;
        if (minSize <= size && maxSize >= size) {
            this.size = size;
        }
    }
}
