package com.example.chat.websocket.entity;

public class ChatMensaje {

    private String usuario;
    private String contenido;

    public ChatMensaje() {
    }

    public ChatMensaje(String usuario, String contenido) {
        this.usuario = usuario;
        this.contenido = contenido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
