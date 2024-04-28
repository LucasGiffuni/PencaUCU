package com.pencaucu.backend.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencaucu.backend.model.responses.CrearPrediccionResponse;
import com.pencaucu.backend.model.responses.DefaultResponse;

@Service
public class PrediccionService extends AbstractService {

    // @Autowired
    // PartidoService partidoService;

    public CrearPrediccionResponse cargarPrediccion(int CI, int idPartido, int resultadoEquipo1, int resultadoEquipo2) throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "INSERT INTO prediccion VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, CI);
        preparedStmt.setInt(2, idPartido);
        // HAY QUE CAMBIARLO
        // int idGanador = partidoService.calcularGanador(idPartido, resultadoEquipo1, resultadoEquipo2);
        // preparedStmt.setInt(3, idGanador);
        preparedStmt.setInt(4, resultadoEquipo1);
        preparedStmt.setInt(5, resultadoEquipo2);
        preparedStmt.setInt(6, 0);
        preparedStmt.execute();

        DefaultResponse dr = new DefaultResponse("200", "Prediccion cargada correctamente");
        return new CrearPrediccionResponse(dr, null);
    }

    public void actualizarPuntajes(int idPartido, int resultadoEquipo1, int resultadoEquipo2) throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT * FROM prediccion WHERE idPartido = " + idPartido;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            int ci = rs.getInt(1);
            int prediccionEquipo1 = rs.getInt(3);
            int prediccionEquipo2 = rs.getInt(4);
            int puntosObtenidos = 0;
            // Acierta puntaje exacto
            if (prediccionEquipo1 == resultadoEquipo1 && prediccionEquipo2 == resultadoEquipo2) {
                puntosObtenidos = 4;
            } else if (prediccionEquipo1 == prediccionEquipo2 && resultadoEquipo1 == resultadoEquipo2) {
                puntosObtenidos = 2;
            } else if (prediccionEquipo1 > prediccionEquipo2 && resultadoEquipo1 > resultadoEquipo2) {
                puntosObtenidos = 2;
            } else if (prediccionEquipo1 < prediccionEquipo2 && resultadoEquipo1 < resultadoEquipo2) {
                puntosObtenidos = 2;
            }
            sql = "UPDATE prediccion SET puntosObtenidos = " + puntosObtenidos + " WHERE cedulaIdentidad = " + ci + " AND idPartido = " + idPartido;
            con.prepareStatement(sql).execute();
            actualizarPuntajeEstAlumno(ci);
        }

    }

    public void actualizarPuntajeEstAlumno(int ci) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "SELECT puntosObtenidos FROM prediccion WHERE cedulaIdentidad = " + ci;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        int puntajeTotal = 0;
        while (rs.next()) {
            puntajeTotal += rs.getInt(1);
        }
        sql = "UPDATE alumno SET puntaje = " + puntajeTotal + " WHERE cedulaIdentidad = " + ci; 
        con.prepareStatement(sql).execute();
    }

}
