package com.pencaucu.backend.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.EquipoConPuntaje;
import com.pencaucu.backend.model.Grupo;
import com.pencaucu.backend.model.responses.DefaultResponse;
import com.pencaucu.backend.model.responses.GetEquipoResponse;
import com.pencaucu.backend.model.responses.GetGrupoResponse;

@Service
public class GrupoService extends AbstractService {

    public GetGrupoResponse getGrupoById(String id) throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT g.idEquipo, e.nombre, e.urlBandera, e.status, g.puntos FROM equipogrupo g JOIN equipo e ON g.idEquipo = e.idequipo WHERE g.grupo = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, id);
        ResultSet rs = preparedStmt.executeQuery();
        Grupo g = new Grupo();
        g.setId(id);
        ArrayList<EquipoConPuntaje> equipos = new ArrayList<>();
        while (rs.next()) {
            equipos.add(new EquipoConPuntaje(rs));
        }
        g.setEquipos(equipos);
        DefaultResponse defaultResponse = new DefaultResponse("200", "Grupo encontrado correctamente");
        return new GetGrupoResponse(defaultResponse, g);
    }
}
