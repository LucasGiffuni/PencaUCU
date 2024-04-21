package com.pencaucu.backend.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.responses.DefaultResponse;
import com.pencaucu.backend.model.responses.GetEquipoResponse;

@Service
public class EquipoService extends AbstractService {

    public GetEquipoResponse getEquipoById(String idEquipo) throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT * FROM equipo WHERE idEquipo = " + idEquipo;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
        rs.absolute(1);
        Equipo e = new Equipo(rs);
        DefaultResponse dr = new DefaultResponse("200", "Equipo obtenido correctamente");
        return new GetEquipoResponse(dr, e);
    }

}
