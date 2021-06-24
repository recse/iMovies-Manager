package com.example.imoviesmanager;

public class usuario {

    String stNombreUsuario;
    String stUsuarioEmail;
    String stUserId;

    public usuario(String stNombreUsuario, String stUsuarioEmail, String stUserId) {
        this.stNombreUsuario = stNombreUsuario;
        this.stUsuarioEmail = stUsuarioEmail;
        this.stUserId = stUserId;
    }

    public String getstNombreUsuario() {
        return stNombreUsuario;
    }

    public String getstUsuarioEmail() {
        return stUsuarioEmail;
    }

    public String getStUserId() {
        return stUserId;
    }

}

