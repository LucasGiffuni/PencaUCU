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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    Map<Integer, Integer> siguientesPartidos = new HashMap<Integer, Integer>() {
        {
            put(25, 29);
            put(26, 29);
            put(27, 30);
            put(28, 30);
            put(29, 32);
            put(30, 32);
        }
    };

    Map<String, Integer> armadoCuartos = new HashMap<String, Integer>() {
        {
            put("1A", 25);
            put("2B", 25);
            put("1B", 26);
            put("2A", 26);
            put("1D", 28);
            put("2C", 28);
            put("1C", 27);
            put("2D", 27);
        }
    };

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

            GetEquipoResponse equipo1 = equipoService.getEquipoById(Integer.parseInt(p.getIdEquipo1()));
            p.setNombreEquipo1(equipo1.getEquipo().getNombre());
            p.setUrlBanderaEquipo1(equipo1.getEquipo().getUrlBandera());

            GetEquipoResponse equipo2 = equipoService.getEquipoById(Integer.parseInt(p.getIdEquipo2()));
            p.setNombreEquipo2(equipo2.getEquipo().getNombre());
            p.setUrlBanderaEquipo2(equipo2.getEquipo().getUrlBandera());

            partidos.add(p);
        }
        return partidos;
    }

    public GetProximosPartidosResponse getProximosPartidos() throws ClassNotFoundException, SQLException {
        createConection();

        GetProximosPartidosResponse response = new GetProximosPartidosResponse();
        DefaultResponse DR = new DefaultResponse("200", "OK");
        response.setDefaultResponse(DR);

        List<Partido> proximosPartidos = getPartidos().stream()
                .filter(p -> Timestamp.valueOf(p.getFecha()).after(Timestamp.valueOf(LocalDateTime.now())))
                .collect(Collectors.toList());

        response.setPartidos(proximosPartidos.toArray(new Partido[0]));
        return response;
    }

    public List<Partido> getPartidosJugados() throws ClassNotFoundException, SQLException {
        return getPartidos().stream()
                .filter(p -> Boolean.getBoolean(p.getJugado()) == true)
                .collect(Collectors.toList());
    }

    public List<Partido> getPartidosByEquipo(int idEquipo) throws ClassNotFoundException, SQLException {
        return getPartidos().stream()
                .filter(p -> Integer.parseInt(p.getIdEquipo1()) == idEquipo
                        || Integer.parseInt(p.getIdEquipo2()) == idEquipo)
                .collect(Collectors.toList());
    }

    public CrearPartidoResponse cargarResultadoPartido(int idPartido, int resultadoEquipo1, int resultadoEquipo2)
            throws SQLException, ClassNotFoundException {
        createConection();

        // controlar que si no es fase de grupo, no se pueda cargar empate

        String sql = "UPDATE PARTIDO SET resultadoEquipo1 = ?, resultadoEquipo2 = ?, jugado = true WHERE idPartido = ?";

        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, resultadoEquipo1);
        preparedStmt.setInt(2, resultadoEquipo2);
        preparedStmt.setInt(3, idPartido);
        preparedStmt.execute();
        prediccionService.actualizarPuntajes(idPartido, resultadoEquipo1, resultadoEquipo2);
        Partido p = getPartidoById(idPartido).getPartido();
        if (p.getEtapa().equals("FASE DE GRUPOS")) {
            actualizarPuntajesGrupos(p);
            actualizarCuartos(p);
        } else {
            actualizarEliminatorias(p);
        }
        DefaultResponse DR = new DefaultResponse("200", "Resultado cargado correctamente");
        return new CrearPartidoResponse(DR, p);
    }

    private void actualizarPuntajesGrupos(Partido p) throws NumberFormatException, SQLException {
        String sql = "UPDATE EQUIPO SET puntaje = puntaje + ? WHERE idEquipo = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setInt(1, Integer.parseInt(p.getPuntajeEquipo1()));
        ps.setInt(2, Integer.parseInt(p.getIdEquipo1()));
        ps.execute();

        ps.setInt(1, Integer.parseInt(p.getPuntajeEquipo2()));
        ps.setInt(2, Integer.parseInt(p.getIdEquipo2()));
        ps.execute();
    }

    private void actualizarCuartos(Partido p) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idGrupo FROM EQUIPO WHERE idEquipo = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, Integer.parseInt(p.getIdEquipo1()));
        ResultSet rs = preparedStmt.executeQuery();
        rs.first();

        String grupo = rs.getString(1);

        sql = "SELECT * FROM PARTIDO p, EQUIPO e1, EQUIPO e2 WHERE p.idEquipo1 = e1.idEquipo AND p.idEquipo2 = e2.idEquipo AND p.jugado = false AND e1.idGrupo = ?";
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, grupo);
        rs = preparedStmt.executeQuery();
        
        if (rs.first() == true) {
            return;
        }

        sql = "SELECT * FROM EQUIPO WHERE idGrupo = ? ORDER BY puntaje DESC";
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, grupo);
        rs = preparedStmt.executeQuery();

        for (int i = 1; i <= 4; i++) {
            rs.next();
            if (i <= 2) {
                sql = "UPDATE PARTIDO SET idEquipo" + i + " = ? WHERE idPartido = ?";
                preparedStmt = con.prepareStatement(sql);
                preparedStmt.setInt(1, rs.getInt(1));
                preparedStmt.setInt(2, armadoCuartos.get(Integer.toString(i) + grupo));
                preparedStmt.execute();
                
                equipoService.actualizarEtapa(rs.getInt(1), "CUARTOS DE FINAL");
                equipoService.habilitarEquipo(rs.getInt(1));
                
            } else {
                equipoService.deshabilitarEquipo(rs.getInt(1));
            }
        }
    }

    private void actualizarEliminatorias(Partido p) throws ClassNotFoundException, SQLException {
        int partidoSiguiente = siguientesPartidos.get(Integer.parseInt(p.getId()));
        int idGanador = obtenerIdEquipoGanador(p);
        String columna = (Integer.parseInt(p.getId()) % 2 != 0) ? "idEquipo1" : "idEquipo2";
        String sql = "UPDATE PARTIDO SET " + columna + " = ? WHERE idPartido = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, idGanador);
        preparedStmt.setInt(2, partidoSiguiente);
        preparedStmt.execute();

        // Hay que actualizar la etapa del equipo
        equipoService.actualizarEtapa(idGanador, "SEMIFINAL");

        // Controlar que cuando es semifinal, se cargue el tercer puesto
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

    public int obtenerIdEquipoGanador(Partido p)
            throws ClassNotFoundException, SQLException {
        createConection();
        int idPartido = Integer.parseInt(p.getId());
        int resultadoEquipo1 = Integer.parseInt(p.getPuntajeEquipo1());
        int resultadoEquipo2 = Integer.parseInt(p.getPuntajeEquipo2());

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
