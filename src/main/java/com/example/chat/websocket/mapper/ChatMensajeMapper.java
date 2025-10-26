package com.example.chat.websocket.mapper;

import com.example.chat.websocket.dto.ChatMensajeDTO;
import com.example.chat.websocket.entity.ChatMensaje;
import org.springframework.stereotype.Component;

@Component
public class ChatMensajeMapper {

    public static ChatMensajeDTO chatMensajeToChatMensajeDTO(ChatMensaje chatMensaje){
        if(chatMensaje == null){
            return null;
        }

        ChatMensajeDTO dto = new ChatMensajeDTO();
        dto.setUsuario(chatMensaje.getUsuario());
        dto.setContenido(chatMensaje.getContenido());
        return dto;
    }

   public static ChatMensaje chatMensajeDTOToChatMensaje(ChatMensajeDTO chatMensajeDTO){
        if (chatMensajeDTO == null ){
            return null;
        }

        ChatMensaje entity = new ChatMensaje();
        entity.setUsuario(chatMensajeDTO.getUsuario());
        entity.setContenido(chatMensajeDTO.getContenido());
        return entity;
   }
}
