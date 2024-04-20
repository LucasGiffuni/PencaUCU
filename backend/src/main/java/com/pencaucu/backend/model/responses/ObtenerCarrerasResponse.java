package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Carrera;

public class ObtenerCarrerasResponse {

    public DefaultResponse defaultResponse;
    public Carrera[] carreras;

    public ObtenerCarrerasResponse(DefaultResponse defaultResponse, Carrera[] carreras) {
        this.defaultResponse = defaultResponse;
        this.carreras = carreras;
    }

    public ObtenerCarrerasResponse() {
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Carrera[] getCarreras() {
        return carreras;
    }

    public void setCarreras(Carrera[] carreras) {
        this.carreras = carreras;
    }

}
