package pers.lbf.yeju.common.core.exception.service.rpc;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

/**rpc远程调用异常
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 21:58
 */
public class RpcServiceException extends ServiceException {

    public RpcServiceException(IStatus statusEnum) {
        super(statusEnum);

    }

    public RpcServiceException() {
        super();

    }

    public RpcServiceException(String message, String exceptionCode, Object[] params, String module) {
       super(message, exceptionCode, params, module);
    }

    public RpcServiceException(String message, String exceptionCode){
       super(message, exceptionCode);
    }
}
