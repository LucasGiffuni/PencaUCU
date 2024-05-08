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
import com.pencaucu.backend.model.responses.GetEquipoResponse;
import com.pencaucu.backend.model.responses.GetProximosPartidosResponse;

@Service
public class PartidoService extends AbstractService {

    @Autowired
    EquipoService equipoService;

    @Autowired
    PrediccionService prediccionService;

    public CrearPartidoResponse crearPartido(Partido partido)
            throws SQLException, ParseException, ClassNotFoundException {

        createConection();

        // Verificar que ambos equipos esten en la misma etapa y habilitados, y
        // actualizarles la etapa
        int idEquipo1 = Integer.parseInt(partido.getIdEquipo1());
        int idEquipo2 = Integer.parseInt(partido.getIdEquipo2());
        String etapa = partido.getEtapa();

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

        String sql = "INSERT INTO PARTIDO (idEquipo1, idEquipo2, fecha, etapa, idEstadio) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);

        preparedStmt.setInt(1, idEquipo1);
        preparedStmt.setInt(2, idEquipo2);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parsedDate = dateFormat.parse(partido.getFecha());
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

        preparedStmt.setTimestamp(3, timestamp);
        preparedStmt.setString(4, etapa);
        preparedStmt.setInt(5, Integer.parseInt(partido.getIdEstadio()));

        try {
            preparedStmt.execute();
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            DefaultResponse DR = new DefaultResponse("400",
                    "Ya existe partido ingresado para estos equipos en esta fase");
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

        String sql = "SELECT * FROM PARTIDO";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();

        List<Partido> partidos = new ArrayList<Partido>();

        while (rs.next()) {
            Partido p = new Partido(rs);
            partidos.add(p);
        }
        return partidos;
    }

    public GetProximosPartidosResponse getProximosPartidos() throws ClassNotFoundException, SQLException {
        createConection();

        GetProximosPartidosResponse response = new GetProximosPartidosResponse();

        String sql = "SELECT * FROM PARTIDO";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();

        List<Partido> partidos = new ArrayList<Partido>();

        while (rs.next()) {
            Partido p = new Partido(rs);

            GetEquipoResponse equipo1 = equipoService.getEquipoById(Integer.parseInt(p.getIdEquipo1()));
            p.setNombreEquipo1(equipo1.getEquipo().getNombre());
            p.setUrlBanderaEquipo1(equipo1.getEquipo().getUrlBandera());

            GetEquipoResponse equipo2 = equipoService.getEquipoById(Integer.parseInt(p.getIdEquipo2()));
            p.setNombreEquipo2(equipo2.getEquipo().getNombre());
            p.setUrlBanderaEquipo2(equipo2.getEquipo().getUrlBandera());

            partidos.add(p);
        }

        DefaultResponse DR = new DefaultResponse("200", "OK");

        response.setDefaultResponse(DR);
        response.setPartidos(partidos.toArray(new Partido[0]));

        return response;
    }

    public List<Partido> getPartidosJugados() throws ClassNotFoundException, SQLException {
        return getPartidos().stream()
                .filter(p -> Boolean.getBoolean(p.getJugado()) == true)
                .collect(Collectors.toList());
    }

    public CrearPartidoResponse cargarResultadoPartido(int idPartido, int resultadoEquipo1, int resultadoEquipo2)
            throws SQLException, ClassNotFoundException {
        createConection();

        // int idGanador = calcularGanador(idPartido, resultadoEquipo1,
        // resultadoEquipo2);

        String sql = "UPDATE PARTIDO SET resultadoEquipo1 = ?, resultadoEquipo2 = ?, jugado = true WHERE idPartido = ?";

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
        String sql = "SELECT * FROM PARTIDO WHERE idPartido = " + idPartido;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        rs.absolute(1);
        Partido p = new Partido(rs);
        DefaultResponse DR = new DefaultResponse("200", "Partido obtenido correctamente");
        return new CrearPartidoResponse(DR, p);
    }

    private Partido getPartido(int idEquipo1, int idEquipo2, String etapa) throws SQLException {

        String sql = "SELECT * FROM PARTIDO WHERE idEquipo1 = ? AND idEquipo2 = ? AND etapa = ?";
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

        String sql = "SELECT " + columnaGanador + " FROM PARTIDO WHERE idPartido = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, idPartido);
        ResultSet rs = preparedStmt.executeQuery();
        rs.absolute(1); // hay que controlar cuando no se encuentra el partido
        return rs.getInt(1);
    }
}
