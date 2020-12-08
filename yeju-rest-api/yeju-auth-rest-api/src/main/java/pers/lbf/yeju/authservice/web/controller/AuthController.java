package pers.lbf.yeju.authservice.web.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.yeju.authservice.pojo.LoginVO;
import pers.lbf.yeju.authservice.service.interfaces.IAuthService;
import pers.lbf.yeju.common.core.result.IResult;

import javax.validation.Valid;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/29 10:55
 */
@RestController
@RequestMapping("/authc")
public class AuthController {

    @DubboReference
    private IAuthService authService;

    @PostMapping("/login")
    public IResult login(@RequestBody @Valid LoginVO loginVO){

        return authService.login("123");
    }

}
