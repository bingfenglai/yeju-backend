package pers.lbf.yeju.consumer.authrestapi.interfaces.interfaces;

import pers.lbf.yeju.common.core.result.IResult;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 14:07
 */
public interface IAuthService {

    /**
     * 登录方法
     * @param account 登录账号
     * @return r
     */
    IResult login(String account);


    /**
     * 登出方法
     * @param account 账号
     * @return r
     */
    IResult logout(String account);


    /**
     * 获取验证码
     * @return r
     */
    IResult getVerificationCode();


}
