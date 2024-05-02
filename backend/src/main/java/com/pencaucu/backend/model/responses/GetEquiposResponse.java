package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Equipo;

public class GetEquiposResponse {
    

    private DefaultResponse defaultResponse;
    private Equipo[] equipos;

    public GetEquiposResponse(DefaultResponse defaultResponse, Equipo[] equipos) {
        this.defaultResponse = defaultResponse;
        this.equipos = equipos;
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Equipo[] getEquipo() {
        return equipos;
    }

    public void setEquipo(Equipo[] equipos) {
        this.equipos = equipos;
    }
}
