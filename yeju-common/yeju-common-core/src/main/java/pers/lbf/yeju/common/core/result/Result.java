package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;

import java.io.Serializable;

/**
 * 详情查询结果封装类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 22:28
 */
public class Result<T> extends BaseResult<T> implements Serializable, IResult<T> {

    private T data;


    public static <T> IResult<T> ok(T data) {
        return new Result<>(ServiceStatusEnum.OK, data);
    }

    public static Result<Boolean> failed() {
        return new Result<>(ServiceStatusEnum.OK, false);
    }

    public static Result<Boolean> success() {
        return new Result<>(ServiceStatusEnum.OK, true);
    }


    public static IResult<Object> error(String code, String message) {
        return SimpleResult.failed(code, message);
    }

    public static <T> IResult<T> failed(String code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(ServiceStatusEnum status, T data) {
        this.data = data;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
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
    public String getCode() {
        return code;
    }

    @Override
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
