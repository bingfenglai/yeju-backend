package pers.lbf.yeju.authrestapi.web.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.authrestapi.interfaces.interfaces.IAuthService;
import pers.lbf.yeju.authrestapi.pojo.LoginVO;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.SimpleResult;

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

    @GetMapping("/getInfo")
    public IResult getAuthService() {
        return SimpleResult.ok();
    }
}
