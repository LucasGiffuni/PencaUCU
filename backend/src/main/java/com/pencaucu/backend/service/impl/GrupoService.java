package com.pencaucu.backend.service.impl;

import org.springframework.stereotype.Service;

import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.Grupo;
import com.pencaucu.backend.model.responses.DefaultResponse;
import com.pencaucu.backend.model.responses.GetGrupoResponse;

@Service
public class GrupoService {

    public GetGrupoResponse getGrupoById(String id) {

        DefaultResponse DR = new DefaultResponse("200", "OK");
        Equipo e1 = new Equipo("1", "Uruguay", 10);
        Equipo e2 = new Equipo("2", "Francia", 2);
        Equipo e3 = new Equipo("3", "Venezuela", 0);
        Equipo e4 = new Equipo("4", "Colombia", 4);

        Grupo g = new Grupo();

        Equipo[] e = new Equipo[4];
        e[0] = e1;
        e[1] = e2;
        e[2] = e3;
        e[3] = e4;
        g.setId("A");
        g.setEquipos(e);

        GetGrupoResponse response = new GetGrupoResponse();
        response.setDefaultResponse(DR);
        response.setGrupo(g);

        return response;
    }
}
