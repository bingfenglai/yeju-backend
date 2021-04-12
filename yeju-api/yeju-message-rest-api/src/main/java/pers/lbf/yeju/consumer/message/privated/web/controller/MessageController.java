/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package pers.lbf.yeju.consumer.message.privated.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.consumer.message.vo.MessageVO;
import pers.lbf.yeju.service.interfaces.message.IMessageService;
import pers.lbf.yeju.service.interfaces.message.TextMessage;
import pers.lbf.yeju.service.interfaces.message.privated.IPrivateMessageService;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/4/6 14:56
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @DubboReference
    private IMessageService messageService;

    @DubboReference
    private IPrivateMessageService privateMessageService;

    @ApiOperation(value = "获取指定发送者的未读消息", notes = "指定发送者的未读消息说明", httpMethod = "GET")
    @GetMapping("/private/{id}")
    public Mono<IResult<List<MessageVO>>> getUnReadMessageBySenderId(@PathVariable String id) throws ServiceException {
        log.info("获取请求者信息");
        log.info("开始读取senderId为{}的消息", id);
        List<MessageVO> list = new LinkedList<>();
        MessageVO message1 = new MessageVO();
        message1.setId("1");
        message1.setContent("明天来我家吃饭");
        message1.setType("0");

        MessageVO message2 = new MessageVO();
        message2.setId("2");
        message2.setContent("好的");
        message2.setType("1");

        list.add(message1);
        list.add(message2);


        return Mono.just(Result.ok(list));
    }


    @ApiOperation(value = "发私信", notes = "说明", httpMethod = "POST")
    @PostMapping("/private")
    public Mono<IResult<Boolean>> sendMessage(@RequestBody TextMessage message) throws ServiceException {
        log.info(message.toString());
        return Mono.just(privateMessageService.addMessage(message));
    }


}
