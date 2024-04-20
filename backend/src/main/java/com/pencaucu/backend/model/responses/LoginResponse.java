package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Alumno;

public class LoginResponse {

    DefaultResponse Response;
    String JWT;
    Alumno alumno;

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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

}