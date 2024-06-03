package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Prediccion;

public class ConsultarPrediccionesPorUsuarioResponse {

    private DefaultResponse defaultResponse;
    private DetallePrediccionUsuario detallePrediccionUsuario;

     public ConsultarPrediccionesPorUsuarioResponse(DefaultResponse defaultResponse, DetallePrediccionUsuario detallePrediccionUsuario) {
        this.defaultResponse = defaultResponse;
        this.detallePrediccionUsuario = detallePrediccionUsuario;
    }
}
