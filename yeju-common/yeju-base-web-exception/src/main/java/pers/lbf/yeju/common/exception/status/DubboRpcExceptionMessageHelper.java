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

package pers.lbf.yeju.common.exception.status;

import org.apache.dubbo.rpc.RpcException;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/12 0:33
 */
public class DubboRpcExceptionMessageHelper {

    private DubboRpcExceptionMessageHelper() {

    }

    public static String getMessage(RpcException e) {
        switch (e.getCode()) {
            case RpcException.NETWORK_EXCEPTION:
                return "网络错误";
            case RpcException.TIMEOUT_EXCEPTION:
                return "服务调用超时";
            case RpcException.BIZ_EXCEPTION:
                return "业务逻辑处理异常";
            case RpcException.FORBIDDEN_EXCEPTION:
                return "服务暂不可用，请耐心等待";
            case RpcException.SERIALIZATION_EXCEPTION:
                return "序列化发生错误";
            case RpcException.NO_INVOKER_AVAILABLE_AFTER_FILTER:
                return "远程调用不存在";
            case RpcException.LIMIT_EXCEEDED_EXCEPTION:
                return "服务正忙，请稍后重试";
            default:
                return "未知异常";

        }
    }
}
