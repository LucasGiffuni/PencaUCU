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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pencaucu.backend.BackendApplication;
import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.Partido;
import com.pencaucu.backend.model.responses.CrearPartidoResponse;
import com.pencaucu.backend.model.responses.DefaultResponse;

@Service
public class PartidoService extends AbstractService {

    @Autowired
    EquipoService equipoService;
    
    @Autowired
    PrediccionService prediccionService;

    public CrearPartidoResponse crearPartido(int idEquipo1, int idEquipo2, String fecha, String etapa, int idEstadio)
            throws SQLException, ParseException, ClassNotFoundException {

        createConection();

        // Verificar que ambos equipos esten en la misma etapa y habilitados, y
        // actualizarles la etapa

        String statusEquipo1 = equipoService.getEquipoById(idEquipo1).getEquipo().getEtapaActual();
        String statusEquipo2 = equipoService.getEquipoById(idEquipo2).getEquipo().getEtapaActual();

        if (statusEquipo1.equals("DESCALIFICADO") || statusEquipo2.equals("DESCALIFICADO")) {
            throw new UnsupportedOperationException("Uno de los equipos está descalificado");
        }

        String etapaEquipo1 = equipoService.getEquipoById(idEquipo1).getEquipo().getEtapaActual();
        String etapaEquipo2 = equipoService.getEquipoById(idEquipo2).getEquipo().getEtapaActual();

        if (!etapaEquipo1.equals(etapaEquipo2)) {
            throw new UnsupportedOperationException("Los equipos no están en la misma etapa");
        }

        String sql = "INSERT INTO partido(idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);

        preparedStmt.setInt(1, idEquipo1);
        preparedStmt.setInt(2, idEquipo2);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parsedDate = dateFormat.parse(fecha);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

        preparedStmt.setTimestamp(3, timestamp);
        preparedStmt.setString(4, etapa);
        preparedStmt.setInt(5, idEstadio);

        try {
            preparedStmt.execute();
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            DefaultResponse DR = new DefaultResponse("400", "Parametros invalidos");
            return new CrearPartidoResponse(DR, null);
        }

        // Actualizo la etapa de los equipos con la nueva etapa en la que se encuentran
        equipoService.actualizarEtapa(idEquipo1, etapa);
        equipoService.actualizarEtapa(idEquipo2, etapa);
        Partido p = getPartido(idEquipo1, idEquipo2, etapa);
        DefaultResponse DR = new DefaultResponse("200", "Partido creado correctamente");
        return new CrearPartidoResponse(DR, p);

    }

    public List<Partido> getPartidos() throws ClassNotFoundException, SQLException {
        createConection();

        String sql = "SELECT * FROM partido";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();

        List<Partido> partidos = new ArrayList<Partido>();

        while (rs.next()) {
            Partido p = new Partido(rs);
            partidos.add(p);
        }
        return partidos;
    }

    public List<Partido> getProximosPartidos() throws ClassNotFoundException, SQLException {
        return getPartidos().stream()
                .filter(p -> Timestamp.valueOf(p.getFecha()).after(Timestamp.valueOf(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    public List<Partido> getPartidosJugados() throws ClassNotFoundException, SQLException {
        return getPartidos().stream()
                .filter(p -> Boolean.getBoolean(p.getJugado()) == true)
                .collect(Collectors.toList());
    }

    public CrearPartidoResponse cargarResultadoPartido(int idPartido, int resultadoEquipo1, int resultadoEquipo2)
            throws SQLException, ClassNotFoundException {
        createConection();

        // int idGanador = calcularGanador(idPartido, resultadoEquipo1, resultadoEquipo2);

        String sql = "UPDATE partido SET resultadoEquipo1 = ?, resultadoEquipo2 = ?, jugado = true WHERE idPartido = ?";

        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, resultadoEquipo1);
        preparedStmt.setInt(2, resultadoEquipo2);
        preparedStmt.setInt(3, idPartido);
        preparedStmt.execute();
        prediccionService.actualizarPuntajes(idPartido, resultadoEquipo1, resultadoEquipo2);
        Partido p = getPartidoById(idPartido).getPartido();
        DefaultResponse DR = new DefaultResponse("200", "Resultado cargado correctamente");
        return new CrearPartidoResponse(DR, p);
    }

    public CrearPartidoResponse getPartidoById(int idPartido) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "SELECT * FROM partido WHERE idPartido = " + idPartido;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        rs.absolute(1);
        Partido p = new Partido(rs);
        DefaultResponse DR = new DefaultResponse("200", "Partido obtenido correctamente");
        return new CrearPartidoResponse(DR, p);
    }

    private Partido getPartido(int idEquipo1, int idEquipo2, String etapa) throws SQLException {

        String sql = "SELECT * FROM partido WHERE idEquipo1 = ? AND idEquipo2 = ? AND etapa = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, idEquipo1);
        preparedStmt.setInt(2, idEquipo2);
        preparedStmt.setString(3, etapa);

        ResultSet rs = preparedStmt.executeQuery();
        rs.absolute(1);

        Partido p = new Partido(rs);
        return p;
    }

    public int calcularGanador(int idPartido, int resultadoEquipo1, int resultadoEquipo2)
            throws ClassNotFoundException, SQLException {
        createConection();
        if (resultadoEquipo1 == resultadoEquipo2) {
            return 0;
        }

        String columnaGanador = (resultadoEquipo1 > resultadoEquipo2) ? "idEquipo1" : "idEquipo2";

        String sql = "SELECT " + columnaGanador + " FROM partido WHERE idPartido = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, idPartido);
        ResultSet rs = preparedStmt.executeQuery();
        rs.absolute(1); // hay que controlar cuando no se encuentra el partido
        return rs.getInt(1);
    }
}
