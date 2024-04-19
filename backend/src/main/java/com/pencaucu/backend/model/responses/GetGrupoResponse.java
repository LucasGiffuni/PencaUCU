package com.pencaucu.backend.model.responses;

import com.pencaucu.backend.model.Grupo;

public class GetGrupoResponse {

    public DefaultResponse defaultResponse;
    public Grupo grupo;

    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    public void setDefaultResponse(DefaultResponse defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
