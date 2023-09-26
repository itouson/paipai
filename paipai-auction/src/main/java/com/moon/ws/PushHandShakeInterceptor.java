package com.moon.ws;

import com.moon.entity.SysUser;
import com.moon.utils.CurrUserUtils;
import com.moon.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class PushHandShakeInterceptor implements HandshakeInterceptor {
    @Autowired
    private CurrUserUtils currUserUtils;

    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;
        HttpServletRequest req = servletServerHttpRequest.getServletRequest();
        String roomId = req.getParameter("roomId");
        String account = currUserUtils.selectCurrUser("token", SysUser.class).getAccount();
        map.put("roomId", roomId);
        map.put("account", account);
        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }

    //
}
