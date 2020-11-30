package pers.lbf.yeju.common.core.result;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/30 0:18
 */
public interface IResult<T> {

    String getCode();

    String getMessage();

    T getData();
}
