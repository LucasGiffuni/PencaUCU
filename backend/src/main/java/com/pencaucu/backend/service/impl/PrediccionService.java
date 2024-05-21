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

    public CrearPrediccionResponse cargarPrediccion(String userId, int idPartido, int resultadoEquipo1,
            int resultadoEquipo2)
            throws ClassNotFoundException, SQLException {
        createConection();

        verificarPartido(idPartido);

        String sql = "INSERT INTO PREDICCION (userId, idPartido, resultadoEquipo1, resultadoEquipo2) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, userId);
        preparedStmt.setInt(2, idPartido);
        preparedStmt.setInt(3, resultadoEquipo1);
        preparedStmt.setInt(4, resultadoEquipo2);
        preparedStmt.execute();

        Prediccion p = consultarPrediccion(userId, idPartido).getPrediccion();

        DefaultResponse dr = new DefaultResponse("200", "Prediccion cargada correctamente");
        return new CrearPrediccionResponse(dr, p);
    }

    public CrearPrediccionResponse modificarPrediccion(String userId, int idPartido, int resultadoEquipo1,
            int resultadoEquipo2) throws SQLException, ClassNotFoundException {
        createConection();

        verificarPartido(idPartido);        

        String sql = "UPDATE PREDICCION SET resultadoEquipo1 = ?, resultadoEquipo2 = ? WHERE userId = ? AND idPartido = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, resultadoEquipo1);
        preparedStmt.setInt(2, resultadoEquipo2);
        preparedStmt.setString(3, userId);
        preparedStmt.setInt(4, idPartido);
        preparedStmt.execute();

        Prediccion p = consultarPrediccion(userId, idPartido).getPrediccion();

        DefaultResponse dr = new DefaultResponse("200", "Prediccion actualizada correctamente");
        return new CrearPrediccionResponse(dr, p);
    }

    private void verificarPartido(int idPartido) throws SQLException, ClassNotFoundException {
        if (verificarJugado(idPartido)) {
            throw new UnsupportedOperationException(
                    "No se puede realizar predicción porque el partido ya se jugó");
        }
        
        if (verificarHora(idPartido)) {
            throw new UnsupportedOperationException(
                    "No se puede realizar predicción porque falta menos de una hora para el partido");
        }
    }

    /*
     * Devuelve true si falta menos de una hora
     */
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

    /*
     * Devuelve true si el partido ya se jugó
     */
    private boolean verificarJugado(int idPartido) throws SQLException {
        String sql = "SELECT jugado FROM PARTIDO WHERE idPartido = " + idPartido;
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
        rs.first();
        return rs.getBoolean(1);
    }

    public CrearPrediccionResponse consultarPrediccion(String userId, int idPartido)
            throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT * FROM PREDICCION WHERE userId = ? AND idPartido = ?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, userId);
        p.setInt(2, idPartido);
        ResultSet rs = p.executeQuery();
        rs.absolute(1);
        DefaultResponse dr = new DefaultResponse("200", "Prediccion cargada correctamente");
        return new CrearPrediccionResponse(dr, new Prediccion(rs));
    }

    public List<Prediccion> consultarPredicciones(String userId) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "SELECT * FROM PREDICCION WHERE userId = ?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, userId);
        ResultSet rs = p.executeQuery();
        List<Prediccion> predicciones = new ArrayList<>();
        while (rs.next()) {
            predicciones.add(new Prediccion(rs));
        }
        return predicciones;
    }

    public void actualizarPuntajes(int idPartido, int resultadoEquipo1, int resultadoEquipo2)
            throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "SELECT * FROM PREDICCION WHERE idPartido = " + idPartido;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            String userId = rs.getString(1);
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
            sql = "UPDATE PREDICCION SET puntosObtenidos = " + puntosObtenidos + " WHERE userId = ? AND idPartido = "
                    + idPartido;
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, userId);
            p.execute();
            actualizarPuntajeAlumno(userId);
        }

    }

    public void actualizarPuntajeAlumno(String userId) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "SELECT puntosObtenidos FROM PREDICCION WHERE userId = ?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, userId);
        ResultSet rs = p.executeQuery();
        int puntajeTotal = 0;
        while (rs.next()) {
            puntajeTotal += rs.getInt(1);
        }
        sql = "UPDATE USUARIO SET puntaje = " + puntajeTotal + " WHERE userId = ?";
        p = con.prepareStatement(sql);
        p.setString(1, userId);
        p.execute();
    }

}
