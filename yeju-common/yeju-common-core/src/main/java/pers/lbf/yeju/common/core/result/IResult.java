package pers.lbf.yeju.common.core.result;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/30 0:18
 */
public interface IResult<T> {

    /**
     * 获取响应代码
     * @return code
     */
    String getCode();

    /**
     * 获取响应消息
     * @return message
     */
    String getMessage();

    /**
     * 获取响应数据
     * @return t
     */
    T getData();

    /**
     * 请求是否成功
     * @return success flag
     */
    Boolean isSuccess();
}
