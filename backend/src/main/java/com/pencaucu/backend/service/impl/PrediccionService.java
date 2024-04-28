package com.pencaucu.backend.service.impl;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencaucu.backend.model.Prediccion;
import com.pencaucu.backend.model.responses.CrearPrediccionResponse;
import com.pencaucu.backend.model.responses.DefaultResponse;

@Service
public class PrediccionService extends AbstractService {

    public CrearPrediccionResponse cargarPrediccion(int CI, int idPartido, int resultadoEquipo1, int resultadoEquipo2)
            throws ClassNotFoundException, SQLException {
        createConection();

        if (verificarHora(idPartido)) {
            throw new UnsupportedOperationException(
                    "No se puede realizar predicción porque falta menos de una hora para el partido");
        }

        String sql = "INSERT INTO PREDICCION (cedulaIdentidad, idPartido, resultadoEquipo1, resultadoEquipo2) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, CI);
        preparedStmt.setInt(2, idPartido);
        preparedStmt.setInt(3, resultadoEquipo1);
        preparedStmt.setInt(4, resultadoEquipo2);
        preparedStmt.execute();

        Prediccion p = consultarPrediccion(CI, idPartido).getPrediccion();

        DefaultResponse dr = new DefaultResponse("200", "Prediccion cargada correctamente");
        return new CrearPrediccionResponse(dr, p);
    }

    public CrearPrediccionResponse modificarPrediccion(int CI, int idPartido, int resultadoEquipo1,
            int resultadoEquipo2) throws SQLException, ClassNotFoundException {
        createConection();

        if (verificarHora(idPartido)) {
            throw new UnsupportedOperationException(
                    "No se puede modificar predicción porque falta menos de una hora para el partido");
        }

        String sql = "UPDATE PREDICCION SET resultadoEquipo1 = ?, resultadoEquipo2 = ? WHERE cedulaIdentidad = ? AND idPartido = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, resultadoEquipo1);
        preparedStmt.setInt(2, resultadoEquipo2);
        preparedStmt.setInt(3, CI);
        preparedStmt.setInt(4, idPartido);
        preparedStmt.execute();

        Prediccion p = consultarPrediccion(CI, idPartido).getPrediccion();

        DefaultResponse dr = new DefaultResponse("200", "Prediccion actualizada correctamente");
        return new CrearPrediccionResponse(dr, p);
    }

    public CrearPrediccionResponse consultarPrediccion(int ci, int idPartido)
            throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT * FROM PREDICCION WHERE cedulaIdentidad = " + ci + " AND idPartido = " + idPartido;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        rs.absolute(1);
        DefaultResponse dr = new DefaultResponse("200", "Prediccion cargada correctamente");
        return new CrearPrediccionResponse(dr, new Prediccion(rs));
    }

    public List<Prediccion> consultarPredicciones(int ci) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "SELECT * FROM PREDICCION WHERE cedulaIdentidad = " + ci;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        List<Prediccion> predicciones = new ArrayList<>();
        while (rs.next()) {
            predicciones.add(new Prediccion(rs));
        }
        return predicciones;
    }

    private boolean verificarHora(int idPartido) throws ClassNotFoundException, SQLException {
        createConection();

        String sql = "SELECT fecha FROM PARTIDO WHERE idPartido = " + idPartido;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        rs.absolute(1);
        long currentTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
        long partidoTime = rs.getTimestamp(1).getTime();

        long difference = partidoTime - currentTime;
        long milisegunosEnUnaHora = 3600 * 1000;

        return difference < milisegunosEnUnaHora;
    }

    public void actualizarPuntajes(int idPartido, int resultadoEquipo1, int resultadoEquipo2)
            throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT * FROM PREDICCION WHERE idPartido = " + idPartido;
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
            sql = "UPDATE PREDICCION SET puntosObtenidos = " + puntosObtenidos + " WHERE cedulaIdentidad = " + ci
                    + " AND idPartido = " + idPartido;
            con.prepareStatement(sql).execute();
            actualizarPuntajeEstAlumno(ci);
        }

    }

    public void actualizarPuntajeEstAlumno(int ci) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "SELECT puntosObtenidos FROM PREDICCION WHERE cedulaIdentidad = " + ci;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        int puntajeTotal = 0;
        while (rs.next()) {
            puntajeTotal += rs.getInt(1);
        }
        sql = "UPDATE ALUMNO SET puntaje = " + puntajeTotal + " WHERE cedulaIdentidad = " + ci;
        con.prepareStatement(sql).execute();
    }

}
