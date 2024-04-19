package com.pencaucu.backend.model.responses;

public class RegisterResponse {

    DefaultResponse Response;
    String JWT;
    String message;

    public RegisterResponse() {
    }

    public DefaultResponse getResponse() {
        return Response;
    }

    public void setResponse(DefaultResponse response) {
        Response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String jWT) {
        JWT = jWT;
    }

}