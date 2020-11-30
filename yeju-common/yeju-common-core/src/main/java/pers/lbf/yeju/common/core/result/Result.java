package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.enumes.ServiceStatus;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 22:28
 */
public class Result<T> implements Serializable, IResult<T> {

    private String message;
    private String code;
    private T data;


    public Result<T> ok(T data) {
        return new Result<>(ServiceStatus.OK,data);
    }


    private Result(ServiceStatus status,T data) {
        this.data = data;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    private Result(){ }

    @Override
    public String toString() {
        return "RpcResult{" +
                "messsage='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
