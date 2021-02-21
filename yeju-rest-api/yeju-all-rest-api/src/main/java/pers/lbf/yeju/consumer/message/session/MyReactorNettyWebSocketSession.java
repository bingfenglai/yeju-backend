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
package pers.lbf.yeju.consumer.message.session;

import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.web.reactive.socket.HandshakeInfo;
import org.springframework.web.reactive.socket.adapter.ReactorNettyWebSocketSession;
import reactor.netty.http.websocket.WebsocketInbound;
import reactor.netty.http.websocket.WebsocketOutbound;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/19 22:57
 */
public class MyReactorNettyWebSocketSession extends ReactorNettyWebSocketSession implements Serializable {
    public MyReactorNettyWebSocketSession(WebsocketInbound inbound, WebsocketOutbound outbound, HandshakeInfo info, NettyDataBufferFactory bufferFactory) {
        super(inbound, outbound, info, bufferFactory);
    }

    public MyReactorNettyWebSocketSession(WebsocketInbound inbound, WebsocketOutbound outbound, HandshakeInfo info, NettyDataBufferFactory bufferFactory, int maxFramePayloadLength) {
        super(inbound, outbound, info, bufferFactory, maxFramePayloadLength);
    }
}
