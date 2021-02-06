package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.status.enums.AuthStatusEnum;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

import java.io.Serializable;

/**操作响应消息封装类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 19:40
 */
public class SimpleResult implements Serializable, IResult<Object> {

    private String message;
    private String code;


    public static IResult<Object> ok(){
        return new SimpleResult(ServiceStatusEnum.OK);
    }

    public static IResult<Object> ok(String message){
        return new SimpleResult(message,ServiceStatusEnum.OK.getCode());
    }

    public static IResult<Object> error(){

        return new SimpleResult(ServiceStatusEnum.UNKNOWN_ERROR);
    }

    public static IResult<Object> faild(String code,String message){
        return new SimpleResult(message, code);
    }

    public static IResult<Object> faild(ServiceStatusEnum status){
        return new SimpleResult(status);
    }

    public static IResult<Object> faild(IStatus status){
        return new SimpleResult(status.getMessage(), status.getCode());
    }

    public static IResult<Object> faild(AuthStatusEnum status){
        return new SimpleResult(status);
    }

    public SimpleResult(String message, String code) {
        this.message = message;
        this.code = code;
    }

    private SimpleResult(ServiceStatusEnum status) {
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    private SimpleResult(AuthStatusEnum status){
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
    public Object getData() {
        return null;
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
}
