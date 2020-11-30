package pers.lbf.yeju.authserver.web.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.yeju.authserver.service.interfaces.IAuthService;
import pers.lbf.yeju.common.core.result.IResult;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 10:55
 */
@RestController
@RequestMapping("/authserver")
public class AuthController {

    @DubboReference
    private IAuthService authService;

    @PostMapping("/login")
    public IResult login(){
        return authService.login("123");
    }

}
