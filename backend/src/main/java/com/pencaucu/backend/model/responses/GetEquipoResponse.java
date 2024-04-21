package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Equipo;

public class GetEquipoResponse {
    
    private DefaultResponse defaultResponse;
    private Equipo equipo;

    public GetEquipoResponse(DefaultResponse defaultResponse, Equipo equipo) {
        this.defaultResponse = defaultResponse;
        this.equipo = equipo;
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
