package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

import java.io.Serializable;

/**
 * 操作响应消息封装类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 19:40
 */
public class SimpleResult extends BaseResult<Object> implements Serializable, IResult<Object> {

    public static IResult<Object> ok() {
        return new SimpleResult(ServiceStatusEnum.OK);
    }

    public static IResult<Object> ok(String message) {
        return new SimpleResult(message, ServiceStatusEnum.OK.getCode());
    }

    public static IResult<Object> error() {

        return new SimpleResult(ServiceStatusEnum.UNKNOWN_ERROR);
    }

    public static IResult<Object> failed(String code, String message) {
        return new SimpleResult(message, code);
    }


    public static IResult<Object> failed(IStatus status) {
        return new SimpleResult(status.getMessage(), status.getCode());
    }

    public SimpleResult(String message, String code) {
        this.message = message;
        this.code = code;
    }

    private SimpleResult(IStatus status) {
        if (status == null) {
            System.err.println("出错了/////");
        }
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    @Override
    public String toString() {
        return "SimpleResult{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
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
}
