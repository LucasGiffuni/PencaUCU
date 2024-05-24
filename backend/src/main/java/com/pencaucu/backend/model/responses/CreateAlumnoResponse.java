package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Usuario;

public class CreateAlumnoResponse {

    public DefaultResponse defaultResponse;
    public Usuario alumno;

    public CreateAlumnoResponse(DefaultResponse defaultResponse, Usuario alumno) {
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

    public Usuario getAlumno() {
        return alumno;
    }

    public void setAlumno(Usuario alumno) {
        this.alumno = alumno;
    }

}
