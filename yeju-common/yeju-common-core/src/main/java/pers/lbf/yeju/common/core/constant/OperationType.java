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
package pers.lbf.yeju.common.core.constant;

/**业务操作类型
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/19 16:13
 */
public enum OperationType {

    /**
     * 其他
     */
    OTHER(0),
    /**
     * 新增
     */
    INSTER(1),

    /**
     * 修改
     */
    UPDATE(2),

    /**
     * 逻辑删除
     */
    DELETE(3),

    /**
     * 查询
     */
    SELECT(4),

    /**
     * 授权
     */
    AUTHZ(5);



    private Integer value;

    OperationType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {

        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
