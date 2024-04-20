package com.pencaucu.backend.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pencaucu.backend.BackendApplication;
import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.Partido;
import com.pencaucu.backend.model.responses.CrearPartidoResponse;
import com.pencaucu.backend.model.responses.DefaultResponse;

@Service
public class PartidoService extends AbstractService {

    
    public CrearPartidoResponse crearPartido(int e1, int e2, String fecha) throws SQLException, ParseException, ClassNotFoundException {
        
        createConection();

        String sql = "INSERT INTO partido(idEquipo1, resultadoEquipo1, idEquipo2, resultadoEquipo2, fecha, etapa) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);

        preparedStmt.setInt(1, e1);
        preparedStmt.setInt(2, 0);
        preparedStmt.setInt(3, e2);
        preparedStmt.setInt(4, 0);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parsedDate = dateFormat.parse(fecha);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        
        preparedStmt.setTimestamp(5, timestamp);
        preparedStmt.setString(6, "FASE DE GRUPOS");
        
        try {
            preparedStmt.execute();
            DefaultResponse DR = new DefaultResponse("200", "OK");
            return new CrearPartidoResponse(DR, new Partido());
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            DefaultResponse DR = new DefaultResponse("400", "El equipo no existe");
            return new CrearPartidoResponse(DR, null);
        }
    }

    public List<Partido> getPartidos() throws ClassNotFoundException, SQLException {
        createConection();

        String sql = "SELECT * FROM partido";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();

        List<Partido> partidos = new ArrayList<Partido>();

        while (rs.next()) {
            Partido p = new Partido();
            p.setId(Integer.toString(rs.getInt(1)));
            p.setIdEquipo1(Integer.toString(rs.getInt(2)));
            p.setPuntajeEquipo1(Integer.toString(rs.getInt(3)));
            p.setIdEquipo2(Integer.toString(rs.getInt(4)));
            p.setPuntajeEquipo2(Integer.toString(rs.getInt(5)));
            p.setFecha(rs.getTimestamp(6).toString());
            p.setEtapa(rs.getString(7));
            p.setIdGanador(Integer.toString(rs.getInt(8)));
            p.setJugado(Boolean.toString(rs.getBoolean(9)));
            partidos.add(p);
        }
        return partidos;
    }

    public List<Partido> getProximosPartidos() {
        List<Partido> proximosPartidos = new ArrayList<Partido>();
        // for (Partido partido : this.partidos) {
        // if (partido.getFecha().isAfter(LocalDateTime.now())) {
        // proximosPartidos.add(partido);
        // }
        // }
        return proximosPartidos;
    }

    public List<Partido> getPartidosJugados() {
        List<Partido> proximosPartidos = new ArrayList<Partido>();
        // for (Partido partido : this.partidos) {
        // if (partido.getFecha().isBefore(LocalDateTime.now())) {
        // proximosPartidos.add(partido);
        // }
        // }
        // List<Partido> proximosPartidos = this.partidos.stream().filter(p ->
        // p.getFecha().isBefore(LocalDateTime.now())).collect(Collectors.toList());
        return proximosPartidos;
    }

    public CrearPartidoResponse cargarResultadoPartido(String id, int puntajeEquipo1, int puntajeEquipo2) {
        List<Partido> partidos = new ArrayList<Partido>();
        Partido partido = partidos.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        // partido.setPuntajeEquipo1(puntajeEquipo1);
        // partido.setPuntajeEquipo2(puntajeEquipo2);

        DefaultResponse DR = new DefaultResponse("200", "OK");

        return new CrearPartidoResponse(DR, partido);
    }
}
