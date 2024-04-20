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
public class PartidoService {

    private Connection con;

    @Value("${spring.datasource.connectionString}")
    private String connectionString;
    @Value("${spring.datasource.databaseName}")
    private String databaseName;
    @Value("${spring.datasource.databaseUsername}")
    private String databaseUser;
    @Value("${spring.datasource.databasePassword}")
    private String databasePassword;

    private final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    private void createConection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.con = DriverManager.getConnection(

                connectionString + databaseName, databaseUser, databasePassword);
    }

    public List<Partido> partidos = new ArrayList<Partido>();

    public CrearPartidoResponse crearPartido(int e1, int e2, String fecha) throws SQLException, ParseException {
        DefaultResponse DR = new DefaultResponse("200", "OK");

        String sql = "INSERT INTO partido(idPartido, idEquipo1, resultadoEquipo1, idEquipo2, resultadoEquipo2, fecha, etapa, idEquipoGanador) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);

        preparedStmt.setInt(1, 4);
        preparedStmt.setInt(2, e1);
        preparedStmt.setInt(3, 0);
        preparedStmt.setInt(4, e2);
        preparedStmt.setInt(5, 0);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse(fecha);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        
        preparedStmt.setTimestamp(6, timestamp);
    
        preparedStmt.execute();

        return new CrearPartidoResponse(DR, new Partido());
    }

    public List<Partido> getPartidos() throws ClassNotFoundException, SQLException {
        createConection();

        String sql = "SELECT * FROM partido";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
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
        Partido partido = this.partidos.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        // partido.setPuntajeEquipo1(puntajeEquipo1);
        // partido.setPuntajeEquipo2(puntajeEquipo2);

        DefaultResponse DR = new DefaultResponse("200", "OK");

        return new CrearPartidoResponse(DR, partido);
    }
}
