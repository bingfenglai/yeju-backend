package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.enumes.ServiceStatus;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 22:28
 */
public class Result<T> implements Serializable {

    private String message;
    private String code;
    private T data;

    public Result(ServiceStatus serviceStatus){
        this.message = serviceStatus.getMessage();
        this.code = serviceStatus.getCode();
    }

    public Result(String messsage, String code) {
        this.message = messsage;
        this.code = code;
    }

    public Result(T data) {
        this.data = data;
    }

    private Result(){ }


    /**
     * 服务调用成功时调用
     * @return result
     */
    public static Result<Object> ok(){
        Result<Object> result = new Result<>();
        result.setMessage(ServiceStatus.OK.getMessage());
        result.setCode(ServiceStatus.OK.getCode());
        return result;
    }

    public static Result<Object> error(ServiceStatus status){
        return new Result<>(status);
    }



    public static Result<Object> error(String message, String code){
        Result<Object> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }




    @Override
    public String toString() {
        return "RpcResult{" +
                "messsage='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
