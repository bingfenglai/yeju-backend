package pers.lbf.yeju.common.core.exception;

import pers.lbf.yeju.common.core.enumes.ServiceStatus;

/**rpc远程调用异常
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 21:58
 */
public class RpcServiceException extends RuntimeException {

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
    private Object[] parmas;

    /**
     * 异常所属模块
     */
    private String module;

    public RpcServiceException() {
        this.message = ServiceStatus.UNKNOWN_ERROR.getMessage();
        this.exceptionCode = ServiceStatus.UNKNOWN_ERROR.getCode();
    }

    public RpcServiceException(String message, String exceptionCode, Object[] parmas, String module) {
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.parmas = parmas;
        this.module = module;
    }

    public RpcServiceException(String message, String message1, String exceptionCode, Object[] parmas, String module) {
        super(message);
        this.message = message1;
        this.exceptionCode = exceptionCode;
        this.parmas = parmas;
        this.module = module;
    }

    public RpcServiceException(String message, Throwable cause, String exceptionCode, Object[] parmas, String module) {
        super(message, cause);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.parmas = parmas;
        this.module = module;
    }

    public RpcServiceException(Throwable cause, String message, String exceptionCode, Object[] parmas, String module) {
        super(cause);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.parmas = parmas;
        this.module = module;
    }

    public RpcServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionCode, Object[] parmas, String module) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.parmas = parmas;
        this.module = module;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public Object[] getParmas() {
        return parmas;
    }

    public void setParmas(Object[] parmas) {
        this.parmas = parmas;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
