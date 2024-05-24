package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Usuario;

public class LoginResponse {

    DefaultResponse Response;
    String JWT;
    Usuario alumno;

    public LoginResponse() {

    }

    public DefaultResponse getResponse() {
        return Response;
    }

    public void setResponse(DefaultResponse response) {
        Response = response;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String jWT) {
        JWT = jWT;
    }

    public Usuario getAlumno() {
        return alumno;
    }

    public void setAlumno(Usuario alumno) {
        this.alumno = alumno;
    }

}