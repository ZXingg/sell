package com.zxing.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by ZXing at 2018/2/23
 * QQ:1490570560
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("【websocket】有新的连接，总数为：{}", webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        log.info("【websocket】连接断开，总数为：{}", webSockets.size());
    }

//    @OnError
//    public void onError() {
//        log.warn("【websocket】发生错误！");
//    }

    @OnMessage
    public void onMessage(String msg) {
        log.info("【websocket】收到消息 <== {}", msg);
    }

    public void sendMsg(String msg) {
        //发送广播
        for (WebSocket webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                log.error("【websocket】发送消息出现异常，e = {}", e);
            }
        }
    }


}
