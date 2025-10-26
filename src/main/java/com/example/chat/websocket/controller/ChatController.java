package com.example.chat.websocket.controller;

import com.example.chat.websocket.dto.ChatMensajeDTO;
import com.example.chat.websocket.entity.ChatMensaje;
import com.example.chat.websocket.mapper.ChatMensajeMapper;
import com.example.chat.websocket.repository.ChatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;
@Component
public class ChatController extends TextWebSocketHandler {

    private final Set<WebSocketSession> sesiones = new HashSet<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMensajeMapper chatMensajeMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession sesion){
        sesiones.add(sesion);
        System.out.println("Nueva Conexion: " + sesion.getId());
    }

    @Override
    public  void handleTextMessage(WebSocketSession session, TextMessage mensaje) throws Exception{

        ChatMensajeDTO chatMensajeDTO = mapper.readValue(mensaje.getPayload(), ChatMensajeDTO.class);

        ChatMensaje chatMensaje = ChatMensajeMapper.chatMensajeDTOToChatMensaje(chatMensajeDTO);
        chatRepository.save(chatMensaje);

        for(WebSocketSession s: sesiones){
            if (s.isOpen()){
                ChatMensajeDTO mensajeDTO = ChatMensajeMapper.chatMensajeToChatMensajeDTO(chatMensaje);
                        s.sendMessage(new TextMessage(mapper.writeValueAsString(mensajeDTO)));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession sesion, CloseStatus estado){
        sesiones.remove(sesion);
        System.out.println("Sesion Desconectado: " + sesion.getId());
    }
}
