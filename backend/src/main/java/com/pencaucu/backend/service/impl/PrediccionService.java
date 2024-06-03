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
import com.pencaucu.backend.model.responses.ConsultarPrediccionesPorUsuarioResponse;
import com.pencaucu.backend.model.responses.CrearPrediccionResponse;
import com.pencaucu.backend.model.responses.DefaultResponse;
import com.pencaucu.backend.model.responses.DetallePrediccionUsuario;

@Service
public class PrediccionService extends AbstractService {

    public CrearPrediccionResponse cargarPrediccion(String userId, int idPartido, int resultadoEquipo1,
            int resultadoEquipo2)
            throws ClassNotFoundException, SQLException {
        createConection();

        CrearPrediccionResponse verificarPartido = verificarPartido(idPartido);
        if (verificarPartido != null) {
            return verificarPartido;
        }

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

        CrearPrediccionResponse verificarPartido = verificarPartido(idPartido);
        if (verificarPartido != null) {
            return verificarPartido;
        }

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

    private CrearPrediccionResponse verificarPartido(int idPartido) throws SQLException, ClassNotFoundException {
        if (verificarJugado(idPartido)) {
            DefaultResponse dr = new DefaultResponse("405", "No se puede cargar predicción debido a que el partido ya se jugó");
            return new CrearPrediccionResponse(dr, null);
        }

        if (verificarHora(idPartido)) {
            DefaultResponse dr = new DefaultResponse("405", "No se puede cargar predicción debido a que falta menos de una hora para el partido");
            return new CrearPrediccionResponse(dr, null);
        }

        return null;
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
        DefaultResponse dr = new DefaultResponse("200", "OK");

        String sql = "SELECT * FROM PREDICCION WHERE userId = ? AND idPartido = ?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, userId);
        p.setInt(2, idPartido);
        try {

            ResultSet rs = p.executeQuery();

            rs.absolute(1);

            return new CrearPrediccionResponse(dr, new Prediccion(rs));
        } catch (Exception e) {
            return new CrearPrediccionResponse(dr, null);
        }

    }

    public ConsultarPrediccionesPorUsuarioResponse consultarPredicciones(String userId) throws SQLException, ClassNotFoundException {
        createConection();
        DefaultResponse dr = new DefaultResponse("200", "OK");

        String sql = "SELECT P.idPartido, P.idEquipo1, Pr.resultadoEquipo1, E1.nombre, E1.urlBandera, P.idEquipo2, Pr.resultadoEquipo2, E2.nombre, E2.urlBandera from PARTIDO as P join PREDICCION as Pr on Pr.idPartido = P.idPartido join EQUIPO as E1 on E1.idEquipo = P.idEquipo1 join EQUIPO E2 on E2.idEquipo = P.idEquipo2 where Pr.userId = ?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, userId);

        ResultSet rs = p.executeQuery();
        try {
         

            return new ConsultarPrediccionesPorUsuarioResponse(dr, new DetallePrediccionUsuario(rs));
        } catch (Exception e) {
            return new ConsultarPrediccionesPorUsuarioResponse(dr, null);
        }
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

    public void actualizarPuntosPorCampeones(int idPrimerLugar, int idSegundoLugar) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE rol = \"ALUMNO\"";
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            sql = "UPDATE USUARIO SET puntaje = puntaje + ? where userId = \"" + rs.getString(1) + "\"";
            PreparedStatement ps = con.prepareStatement(sql);
            if (rs.getInt(11) == idPrimerLugar) {
                ps.setInt(1, 10); // acierta campeon, gana 10 puntos
                ps.execute();
            }
            if (rs.getInt(13) == idSegundoLugar) {
                ps.setInt(1, 5); // acierta campeon, gana 10 puntos
                ps.execute();
            }
        }
    }
}
