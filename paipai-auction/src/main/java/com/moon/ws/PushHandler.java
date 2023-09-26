package com.moon.ws;

import com.moon.model.MyMsg;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PushHandler extends TextWebSocketHandler {
    private Map<String, WebSocketSession> clients = new ConcurrentHashMap<String, WebSocketSession>();

    public Map<String, WebSocketSession> getClients() {
        return clients;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        clients.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        clients.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //获取握手拦截阶段带入的信息
        Map<String, Object> map = session.getAttributes();
        String account = map.get("account").toString();
        String roomId = map.get("roomId").toString();
        //创建消息对象
        MyMsg myMsg = new MyMsg();
        myMsg.setCreatetime(new Date());
        myMsg.setSender(account);
        myMsg.setContent(message.getPayload());
        myMsg.setReceiver(roomId);
        myMsg.setType("all");
        TextMessage textMessage = new TextMessage(myMsg.toJsonString());
        //推送
        for (Map.Entry<String, WebSocketSession> et : clients.entrySet()) {
            if (et.getValue().getAttributes().get("roomId").toString().equals(myMsg.getReceiver())) {
                et.getValue().sendMessage(textMessage);
            }
        }
    }

    //
}
