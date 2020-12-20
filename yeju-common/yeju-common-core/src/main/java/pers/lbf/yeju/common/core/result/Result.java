package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.status.enums.ServiceStatus;

import java.io.Serializable;

/**详情查询结果封装类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 22:28
 */
public class Result<T> implements Serializable, IResult<T> {

    private String message;
    private String code;
    private T data;


    public static <T> Result<T> ok(T data) {
        return new Result<>(ServiceStatus.OK,data);
    }

    public static IResult<Object> error(String code,String message){
        return SimpleResult.faild(code, message);
    }

    public static <T> IResult<T> failed(String code, String message,T data){
        return new Result<>(code,message,data);
    }

    private Result(String code, String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
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
