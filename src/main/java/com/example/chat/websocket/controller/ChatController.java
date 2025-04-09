package com.example.chat.websocket.controller;

import com.example.chat.websocket.entity.ChatMensaje;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

public class ChatController extends TextWebSocketHandler {

    private final Set<WebSocketSession> sesiones = new HashSet<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession sesion){
        sesiones.add(sesion);
        System.out.println("Nueva Conexion: " + sesion.getId());
    }

    @Override
    public  void handleTextMessage(WebSocketSession session, TextMessage mensaje) throws Exception{
        ChatMensaje chatMensaje = mapper.readValue(mensaje.getPayload(),ChatMensaje.class);

        for(WebSocketSession s: sesiones){
            if (s.isOpen()){
                s.sendMessage(new TextMessage(mapper.writeValueAsString(chatMensaje)));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession sesion, CloseStatus estado){
        sesiones.remove(sesion);
        System.out.println("Sesion Desconectado: " + sesion.getId());
    }
}
