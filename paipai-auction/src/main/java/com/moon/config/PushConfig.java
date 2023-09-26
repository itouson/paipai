package com.moon.config;

import com.moon.ws.PushHandShakeInterceptor;
import com.moon.ws.PushHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class PushConfig implements WebSocketConfigurer {
    @Autowired
    private PushHandler pushHandler;
    @Autowired
    private PushHandShakeInterceptor pushHandShakeInterceptor;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(pushHandler, "/wspush").addInterceptors(pushHandShakeInterceptor).setAllowedOrigins("*");
    }

    //
}
