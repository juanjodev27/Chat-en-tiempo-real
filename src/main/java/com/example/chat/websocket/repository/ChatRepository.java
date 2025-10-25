package com.example.chat.websocket.repository;

import com.example.chat.websocket.entity.ChatMensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatMensaje,Long> {
}
