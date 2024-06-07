package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Partido;

public class ObtenerPartidosPorEquipoResponse {

    public DefaultResponse defaultResponse;
    public Partido[] Partidos;

    public ObtenerPartidosPorEquipoResponse() {
    }

    public ObtenerPartidosPorEquipoResponse(DefaultResponse defaultResponse, Partido[] partidos) {
        this.defaultResponse = defaultResponse;
        Partidos = partidos;
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Partido[] getPartidos() {
        return Partidos;
    }

    public void setPartidos(Partido[] partidos) {
        Partidos = partidos;
    }

}
