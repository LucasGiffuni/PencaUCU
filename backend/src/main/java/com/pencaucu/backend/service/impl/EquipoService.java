package com.pencaucu.backend.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.responses.DefaultResponse;
import com.pencaucu.backend.model.responses.GetEquipoResponse;

@Service
public class EquipoService extends AbstractService {

    public GetEquipoResponse getEquipoById(int idEquipo) throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT * FROM EQUIPO WHERE idEquipo = " + idEquipo;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
        rs.absolute(1);
        Equipo e = new Equipo(rs);
        DefaultResponse dr = new DefaultResponse("200", "Equipo obtenido correctamente");
        return new GetEquipoResponse(dr, e);
    }

    public List<Equipo> getEquipos() throws SQLException, ClassNotFoundException {
        createConection();
        ArrayList<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM EQUIPO";
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            equipos.add(new Equipo(rs));
        }
        return equipos;
    }

    public GetEquipoResponse actualizarEtapa(int idEquipo, String newEtapa) throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "UPDATE EQUIPO SET etapaActual = ? WHERE idEquipo = " + idEquipo;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, newEtapa);
        preparedStmt.execute();
        DefaultResponse dr = new DefaultResponse("200", "Equipo obtenido correctamente");
        return new GetEquipoResponse(dr, getEquipoById(idEquipo).getEquipo());
    }

}
