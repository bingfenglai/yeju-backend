package pers.lbf.yeju.common.core.exception.service.rpc;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ServiceStatus;
import pers.lbf.yeju.common.core.status.insterfaces.Status;

/**rpc远程调用异常
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 21:58
 */
public class RpcServiceException extends ServiceException {

    /**
     * 错误消息提示
     */
    private String message;

    /**
     * 异常编码
     */
    private String exceptionCode;

    /**
     * 服务调用参数
     */
    private Object[] params;

    /**
     * 异常所属模块
     */
    private String module;



    public static RpcServiceException getInstance(String message, String exceptionCode) {
        return new RpcServiceException(message, exceptionCode);
    }

    public static RpcServiceException getInstance(Status status){
        return new RpcServiceException(status.getMessage(), status.getCode());
    }

    public static RpcServiceException getInstance(Status status,Object[] params, String module){
        return new RpcServiceException(status.getMessage(), status.getCode());
    }



    public static RpcServiceException getInstance(String message, String exceptionCode, Object[] params, String module) {
        return new RpcServiceException(message, exceptionCode, params, module);
    }

    public static RpcServiceException getInstance() {
        return new RpcServiceException();
    }

    public RpcServiceException() {
        this.message = ServiceStatus.UNKNOWN_ERROR.getMessage();
        this.exceptionCode = ServiceStatus.UNKNOWN_ERROR.getCode();
    }

    public RpcServiceException(String message, String exceptionCode, Object[] params, String module) {
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = params;
        this.module = module;
    }

    public RpcServiceException(String message, String exceptionCode){
        this.message = message;
        this.exceptionCode = exceptionCode;
    }

   

   

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getExceptionCode() {
        return exceptionCode;
    }

    @Override
    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    @Override
    public Object[] getParams() {
        return params;
    }

    @Override
    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String getModule() {
        return module;
    }

    @Override
    public void setModule(String module) {
        this.module = module;
    }
}
