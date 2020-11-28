package pers.lbf.yeju.gateway.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/27 0:11
 */
@RestController
@RequestMapping("/te")
public class TestController {

    @GetMapping("/test1")
    public Mono<HashMap<Object, Object>> getDate(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("time",new Date());
        return Mono.just(map);
    }

    @GetMapping("/test2")
    public Mono<Date> getMsg() {
        return Mono.just(new Date());
    }
}
