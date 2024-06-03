package com.pencaucu.backend.model.responses;

import java.util.ArrayList;

import com.pencaucu.backend.model.Prediccion;

public class ConsultarPrediccionesPorUsuarioResponse {

    private DefaultResponse defaultResponse;
    private ArrayList<DetallePrediccionUsuario> detallePrediccionUsuario;

     public ConsultarPrediccionesPorUsuarioResponse(DefaultResponse defaultResponse, ArrayList<DetallePrediccionUsuario> detallePrediccionUsuario) {
        this.defaultResponse = defaultResponse;
        this.detallePrediccionUsuario = detallePrediccionUsuario;
    }

    public ConsultarPrediccionesPorUsuarioResponse() {
        
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public ArrayList<DetallePrediccionUsuario> getDetallePrediccionUsuario() {
        return detallePrediccionUsuario;
    }

    public void setDetallePrediccionUsuario(ArrayList<DetallePrediccionUsuario> detallePrediccionUsuario) {
        this.detallePrediccionUsuario = detallePrediccionUsuario;
    }
}

