package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Prediccion;

public class CrearPrediccionResponse {

    private DefaultResponse defaultResponse;
    private Prediccion prediccion;

    public CrearPrediccionResponse(DefaultResponse defaultResponse, Prediccion prediccion) {
        this.defaultResponse = defaultResponse;
        this.prediccion = prediccion;
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Prediccion getPrediccion() {
        return prediccion;
    }

    public void setPrediccion(Prediccion prediccion) {
        this.prediccion = prediccion;
    }
}
