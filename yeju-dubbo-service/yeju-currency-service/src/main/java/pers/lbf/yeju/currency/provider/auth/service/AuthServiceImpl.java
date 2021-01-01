package pers.lbf.yeju.currency.provider.auth.service;

import org.apache.dubbo.config.annotation.DubboService;
import pers.lbf.yeju.authrestapi.interfaces.interfaces.IAuthService;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.domain.entity.Account;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 20:05
 */
@DubboService(interfaceClass = IAuthService.class)
public class AuthServiceImpl implements IAuthService {
    /**
     * 登录方法
     *
     * @param account 登录账号
     * @return r
     */
    @Override
    public Result<Account> login(String account) {
        return null;
    }

    /**
     * 登出方法
     *
     * @param account 账号
     * @return r
     */
    @Override
    public SimpleResult logout(String account) {
        return null;
    }

    /**
     * 获取验证码
     *
     * @return r
     */
    @Override
    public Result<String> getVerificationCode() {
        return null;
    }
}
