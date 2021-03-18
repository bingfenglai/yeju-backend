package pers.lbf.yeju.common.core.status.enums;

import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/26 22:14
 */
public enum ServiceStatusEnum implements IStatus, Serializable {
    /**
     * 一切ok
     */
    OK("操作成功", "00000"),

    /**
     * 业务操作 结果未不满足条件的情况， 而非系统引起的异常
     */
    failed("操作失败", "00001"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR("未知异常", "e9999"),

    no_data_has_been_found("没有查询到相关数据", "svc01");


    /**
     * 消息
     */
    private String message;

    /**
     * 代码
     */
    private String code;


    ServiceStatusEnum(String message, String code) {
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
