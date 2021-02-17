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
package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.args.IFindPageArgs;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;

import java.io.Serializable;
import java.util.List;

/** 分页查询结果集
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/7 20:37
 */
public class PageResult<T> extends BaseResult<T> implements Serializable {

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 当前页码
     */
    private Long currentPage;

    /**
     * 每页显示的条数
     */
    private Long size;

    /**
     * 记录列表
     */
    private List<T> list;


    public static <T> PageResult<T> ok(Long total, Long currentPage, Long size,List<T> list){
        return new PageResult<T>
                (ServiceStatusEnum.OK.getCode(), ServiceStatusEnum.OK.getMessage(), total, currentPage, size, list);
    }

    public static <T> PageResult<T> ok(IFindPageArgs args,Long count, List<T> list){
        return new PageResult<T>
                (ServiceStatusEnum.OK.getCode(), ServiceStatusEnum.OK.getMessage(), count, args.getCurrentPage(), args.getSize(), list);
    }

    public static <T> PageResult<T> error(String code,String message){
        return new PageResult<T>(code, message);
    }

    private PageResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public PageResult(String code, String message, Long total, Long currentPage, Long size, List<T> list) {
        this.code = code;
        this.message = message;
        this.total = total;
        this.currentPage = currentPage;
        this.size = size;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    @Override
    public String getCode() {
        return this.code;
    }


    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", count=" + total +
                ", currentPage=" + currentPage +
                ", size=" + size +
                ", list=" + list +
                '}';
    }
}
