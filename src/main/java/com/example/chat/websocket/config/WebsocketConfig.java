package com.example.chat.websocket.config;

import com.example.chat.websocket.controller.ChatController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private final ChatController chatController;

    public WebsocketConfig(ChatController chatController){
        this.chatController = chatController;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(chatController,"/chat")
                .setAllowedOrigins("*");
    }

    @Bean
    public ChatController chatController(){
        return new ChatController();
    }
}
