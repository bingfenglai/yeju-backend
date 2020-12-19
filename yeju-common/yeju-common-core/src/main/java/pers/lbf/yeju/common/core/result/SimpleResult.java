package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.status.enums.AuthStatus;
import pers.lbf.yeju.common.core.status.enums.ServiceStatus;

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


    public static SimpleResult ok(){
        return new SimpleResult(ServiceStatus.OK);
    }

    public static SimpleResult ok(String message){
        return new SimpleResult(ServiceStatus.OK.getCode(),message);
    }

    public static SimpleResult error(){

        return new SimpleResult(ServiceStatus.UNKNOWN_ERROR);
    }

    public static SimpleResult faild(String code,String message){
        return new SimpleResult(message, code);
    }

    public static SimpleResult faild(ServiceStatus status){
        return new SimpleResult(status);
    }

    public static SimpleResult faild(AuthStatus status){
        return new SimpleResult(status);
    }

    private SimpleResult(String message, String code) {
        this.message = message;
        this.code = code;
    }

    private SimpleResult(ServiceStatus status) {
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    private SimpleResult(AuthStatus status){
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
