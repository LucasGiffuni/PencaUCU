package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Grupo;
import com.pencaucu.backend.model.Partido;

public class GetPartidosResponse {

    public DefaultResponse defaultResponse;
    public Partido[] partidos;

    public GetPartidosResponse() {
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Partido[] getPartidos() {
        return partidos;
    }

    public void setPartidos(Partido[] partidos) {
        this.partidos = partidos;
    }

}
