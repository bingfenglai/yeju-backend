package pers.lbf.yeju.common.core.status.enums;

import pers.lbf.yeju.common.core.constant.ServiceStatusConstant;
import pers.lbf.yeju.common.core.status.insterfaces.Status;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 22:14
 */
public enum  ServiceStatus implements Status, Serializable {
    /**
     * 一切ok
     */
    OK(ServiceStatusConstant.SUCCESSFUL_OPERATION,ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR("未知异常","e9999");




    /**
     * 消息
     */
    private String message;

    /**
     * 代码
     */
    private String code;


    ServiceStatus(String message, String code) {
        this.message = message;
        this.code = code;
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
    public String toString() {
        return "ServiceStatus{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
