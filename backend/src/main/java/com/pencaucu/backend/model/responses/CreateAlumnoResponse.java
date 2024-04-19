package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Alumno;

public class CreateAlumnoResponse {

    public DefaultResponse defaultResponse;
    public Alumno alumno;

    public CreateAlumnoResponse(DefaultResponse defaultResponse, Alumno alumno) {
        this.defaultResponse = defaultResponse;
        this.alumno = alumno;
    }

    public CreateAlumnoResponse() {
    }

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

}
