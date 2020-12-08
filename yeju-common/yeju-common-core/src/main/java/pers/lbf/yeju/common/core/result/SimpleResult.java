package pers.lbf.yeju.common.core.result;

import pers.lbf.yeju.common.core.enumes.ServiceStatus;

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

    public static SimpleResult error(){

        return new SimpleResult(ServiceStatus.UNKNOWN_ERROR);
    }

    public static SimpleResult faild(String message,String code){
        return new SimpleResult(message, code);
    }

    private SimpleResult(String message, String code) {
        this.message = message;
        this.code = code;
    }

    private SimpleResult(ServiceStatus status) {
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

    public String getMessage() {
        return message;
    }

    @Override
    public Object getData() {
        throw new UnsupportedOperationException("不支持的操作类型");
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
