package com.pencaucu.backend.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pencaucu.backend.model.DatabaseUser;
import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.responses.DefaultResponse;
import com.pencaucu.backend.model.responses.GetEquipoResponse;
import com.pencaucu.backend.model.responses.GetEquiposResponse;

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


    public GetEquipoResponse actualizarEtapa(int idEquipo, String newEtapa) throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "UPDATE EQUIPO SET etapaActual = ? WHERE idEquipo = " + idEquipo;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, newEtapa);
        preparedStmt.execute();
        DefaultResponse dr = new DefaultResponse("200", "Equipo obtenido correctamente");
        return new GetEquipoResponse(dr, getEquipoById(idEquipo).getEquipo());
    }

    public void habilitarEquipo(int idEquipo) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "UPDATE EQUIPO SET status = ? WHERE idEquipo = " + idEquipo;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, "HABILITADO");
        preparedStmt.execute();
    }

    public void deshabilitarEquipo(int idEquipo) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "UPDATE EQUIPO SET status = ? WHERE idEquipo = " + idEquipo;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, "DESCALIFICADO");
        preparedStmt.execute();
    }

    public GetEquiposResponse getEquipos() throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT * FROM EQUIPO";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
        List<Equipo> equipos = new ArrayList<Equipo>();
        while (rs.next()) {
            equipos.add(new Equipo(rs));
        }

        DefaultResponse dr = new DefaultResponse("200", "Equipo obtenido correctamente");

        return new GetEquiposResponse(dr, equipos.toArray(new Equipo[equipos.size()]));
    }

}
