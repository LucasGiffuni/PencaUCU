package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Partido;

public class CrearPartidoResponse {

    public DefaultResponse defaultResponse;
    public Partido partido;

    public CrearPartidoResponse() {
        
    }

    public CrearPartidoResponse(DefaultResponse defaultResponse, Partido partido) {
        this.defaultResponse = defaultResponse;
        this.partido = partido;
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

}
