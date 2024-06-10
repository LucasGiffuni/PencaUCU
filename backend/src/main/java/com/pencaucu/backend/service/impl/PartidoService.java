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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
import com.pencaucu.backend.model.responses.GetPartidosResponse;
import com.pencaucu.backend.model.responses.ObtenerPartidosPorEquipoResponse;

@Service
public class PartidoService extends AbstractService {

    @Autowired
    EquipoService equipoService;

    @Autowired
    PrediccionService prediccionService;

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

        String sql = "SELECT * FROM PARTIDO ORDER BY fecha ASC";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();

        List<Partido> partidos = new ArrayList<Partido>();

        while (rs.next()) {
            Partido p = armarJSONPartido(rs);
            partidos.add(p);
        }
        return partidos;
    }

    private Partido armarJSONPartido(ResultSet rs) throws SQLException, ClassNotFoundException {
        Partido p = new Partido(rs);

        if (!p.getIdEquipo1().equals("0")) {
            GetEquipoResponse equipo1 = equipoService.getEquipoById(Integer.parseInt(p.getIdEquipo1()));
            p.setNombreEquipo1(equipo1.getEquipo().getNombre());
            p.setUrlBanderaEquipo1(equipo1.getEquipo().getUrlBandera());
        }
        if (!p.getIdEquipo2().equals("0")) {
            GetEquipoResponse equipo2 = equipoService.getEquipoById(Integer.parseInt(p.getIdEquipo2()));
            p.setNombreEquipo2(equipo2.getEquipo().getNombre());
            p.setUrlBanderaEquipo2(equipo2.getEquipo().getUrlBandera());
        }
        return p;
    }

    public GetPartidosResponse getProximosPartidos() throws ClassNotFoundException, SQLException {
        createConection();

        GetPartidosResponse response = new GetPartidosResponse();
        DefaultResponse DR = new DefaultResponse("200", "OK");
        response.setDefaultResponse(DR);

        String sql = "select * from PARTIDO where fecha > date_sub(now(), INTERVAL 1 HOUR) and jugado = false and etapa = \"FASE DE GRUPOS\"";
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

        response.setPartidos(partidos.toArray(new Partido[0]));
        return response;
    }

    public GetPartidosResponse getPartidosDelDia() throws ClassNotFoundException, SQLException {
        createConection();

        GetPartidosResponse response = new GetPartidosResponse();
        DefaultResponse DR = new DefaultResponse("200", "OK");
        response.setDefaultResponse(DR);

        String sql = "select * from PARTIDO where fecha = curdate() and jugado = false";
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

        response.setPartidos(partidos.toArray(new Partido[0]));
        return response;
    }

    public List<Partido> getPartidosJugados() throws ClassNotFoundException, SQLException {
        return getPartidos().stream()
                .filter(p -> Boolean.getBoolean(p.getJugado()) == true)
                .collect(Collectors.toList());
    }

    public GetPartidosResponse getPartidosNoJugados() throws ClassNotFoundException, SQLException {
        createConection();

        GetPartidosResponse response = new GetPartidosResponse();
        DefaultResponse DR = new DefaultResponse("200", "OK");
        response.setDefaultResponse(DR);

        String sql = "select * from PARTIDO where fecha > date_sub(now(), INTERVAL 1 HOUR) and jugado = false and etapa = \"FASE DE GRUPOS\"";
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

        response.setPartidos(partidos.toArray(new Partido[0]));
        return response;
    }

    public ObtenerPartidosPorEquipoResponse getPartidosByEquipo(int idEquipo)
            throws ClassNotFoundException, SQLException {

        ObtenerPartidosPorEquipoResponse response = new ObtenerPartidosPorEquipoResponse();

        DefaultResponse df = new DefaultResponse("200", "OK");

        List<Partido> partidos = getPartidos().stream()
                .filter(p -> Integer.parseInt(p.getIdEquipo1()) == idEquipo
                        || Integer.parseInt(p.getIdEquipo2()) == idEquipo)
                .collect(Collectors.toList());

        response.setDefaultResponse(df);
        response.setPartidos(partidos.toArray(new Partido[0]));

        return response;
    }

    public CrearPartidoResponse cargarResultadoPartido(int idPartido, int resultadoEquipo1, int resultadoEquipo2)
            throws SQLException, ClassNotFoundException {
        createConection();

        // Validar que no se hayan cargado los resultados del partido ya
        Partido p = getPartidoById(idPartido).getPartido();
        if (Boolean.valueOf(p.getJugado())) {
            DefaultResponse DR = new DefaultResponse("405",
                    "Operación no permitida. Los resultados del partido ya han sido cargados anteriormente");
            return new CrearPartidoResponse(DR, p);
        }
        if (p.getIdEquipo1().equals("0") || p.getIdEquipo2().equals("0")) {
            DefaultResponse DR = new DefaultResponse("405", "Los equipos del partido no se han definido");
            return new CrearPartidoResponse(DR, p);
        }
        // controlar que si no es fase de grupo, no se pueda cargar empate
        if (resultadoEquipo1 == resultadoEquipo2 && !p.getEtapa().equals("FASE DE GRUPOS")) {
            DefaultResponse DR = new DefaultResponse("400", "No se puede cargar empate en partidos de eliminatorias");
            return new CrearPartidoResponse(DR, p);
        }

        String sql = "UPDATE PARTIDO SET resultadoEquipo1 = ?, resultadoEquipo2 = ?, jugado = true WHERE idPartido = ?";

        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, resultadoEquipo1);
        preparedStmt.setInt(2, resultadoEquipo2);
        preparedStmt.setInt(3, idPartido);
        preparedStmt.execute();

        int puntosEquipo1 = 0;
        int puntosEquipo2 = 0;

        prediccionService.actualizarPuntajes(idPartido, resultadoEquipo1, resultadoEquipo2);
        p = getPartidoById(idPartido).getPartido(); // traigo de nuevo el partido con los datos actualizados

        if (Integer.parseInt(p.getPuntajeEquipo1()) == Integer.parseInt(p.getPuntajeEquipo2())
                && Integer.parseInt(p.getPuntajeEquipo1()) != 0
                && Integer.parseInt(p.getPuntajeEquipo2()) != 0) {
            puntosEquipo1 = 1;
            puntosEquipo2 = 1;

        } else if (Integer.parseInt(p.getPuntajeEquipo1()) > Integer.parseInt(p.getPuntajeEquipo2())) {
            puntosEquipo1 = 3;
            puntosEquipo2 = 0;

        } else if (Integer.parseInt(p.getPuntajeEquipo2()) > Integer.parseInt(p.getPuntajeEquipo1())) {
            puntosEquipo1 = 0;
            puntosEquipo2 = 3;

        }
        if (p.getEtapa().equals("FASE DE GRUPOS")) {
            actualizarPuntajesEquipo(p.getIdEquipo1(), puntosEquipo1);
            actualizarPuntajesEquipo(p.getIdEquipo2(), puntosEquipo2);

            actualizarCuartos(p);
        } else if (!p.getEtapa().equals("FINAL") && !p.getEtapa().equals("TERCER PUESTO")) {
            actualizarEliminatorias(p);
        } else { // Es final
            // Actualizar puntos por predicciones de campeon y sub campeon
            int idPrimerLugar = obtenerIdEquipoGanador(p);
            int idSegundoLugar = obtenerIdEquipoPerdedor(p);
            prediccionService.actualizarPuntosPorCampeones(idPrimerLugar, idSegundoLugar);
        }

        DefaultResponse DR = new DefaultResponse("200", "Resultado cargado correctamente");
        return new CrearPartidoResponse(DR, p);
    }

    private void actualizarPuntajesEquipo(String idEquipo, int puntos)
            throws NumberFormatException, SQLException, ClassNotFoundException {

        String sql = "UPDATE EQUIPO SET puntaje = puntaje + ? WHERE idEquipo = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, puntos);
        ps.setInt(2, Integer.parseInt(idEquipo));

        ps.execute();

    }

    private void actualizarCuartos(Partido p) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idGrupo FROM EQUIPO WHERE idEquipo = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, Integer.parseInt(p.getIdEquipo1()));
        ResultSet rs = preparedStmt.executeQuery();
        rs.first();

        String grupo = rs.getString(1);

        sql = "SELECT * FROM PARTIDO p, EQUIPO e1, EQUIPO e2 WHERE p.idEquipo1 = e1.idEquipo AND p.idEquipo2 = e2.idEquipo "
                +
                "AND p.jugado = false AND e1.idGrupo = ? AND p.etapa = \"FASE DE GRUPOS\"";
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
        int idPerdedor = obtenerIdEquipoPerdedor(p);
        String columna = (Integer.parseInt(p.getId()) % 2 != 0) ? "idEquipo1" : "idEquipo2";
        String sql = "UPDATE PARTIDO SET " + columna + " = ? WHERE idPartido = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, idGanador);
        preparedStmt.setInt(2, partidoSiguiente);
        preparedStmt.execute();

        // Actualizar la etapa del equipo
        if (p.getEtapa().equals("CUARTOS DE FINAL")) {
            equipoService.actualizarEtapa(idGanador, "SEMIFINAL");
            equipoService.deshabilitarEquipo(idPerdedor);
        } else if (p.getEtapa().equals("SEMIFINAL")) {
            equipoService.actualizarEtapa(idGanador, "FINAL");
            preparedStmt.setInt(1, idPerdedor);
            preparedStmt.setInt(2, 31); // 31 es el partido de tercer puesto
            preparedStmt.execute();
            equipoService.actualizarEtapa(idPerdedor, "TERCER PUESTO");
        }
    }

    public CrearPartidoResponse getPartidoById(int idPartido) throws SQLException, ClassNotFoundException {
        createConection();
        String sql = "SELECT * FROM PARTIDO WHERE idPartido = " + idPartido;
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        rs.absolute(1);
        Partido p = armarJSONPartido(rs);
        DefaultResponse DR = new DefaultResponse("200", "Partido obtenido correctamente");
        return new CrearPartidoResponse(DR, p);
    }

    private Partido getPartido(int idEquipo1, int idEquipo2, String etapa) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM PARTIDO WHERE idEquipo1 = ? AND idEquipo2 = ? AND etapa = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, idEquipo1);
        preparedStmt.setInt(2, idEquipo2);
        preparedStmt.setString(3, etapa);

        ResultSet rs = preparedStmt.executeQuery();
        rs.absolute(1);

        Partido p = armarJSONPartido(rs);
        return p;
    }

    private int obtenerIdEquipoGanador(Partido p) throws ClassNotFoundException, SQLException {
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

    private int obtenerIdEquipoPerdedor(Partido p) throws ClassNotFoundException, SQLException {
        createConection();
        int idPartido = Integer.parseInt(p.getId());
        int resultadoEquipo1 = Integer.parseInt(p.getPuntajeEquipo1());
        int resultadoEquipo2 = Integer.parseInt(p.getPuntajeEquipo2());

        if (resultadoEquipo1 == resultadoEquipo2) {
            return 0;
        }

        String columnaPerdedor = (resultadoEquipo1 < resultadoEquipo2) ? "idEquipo1" : "idEquipo2";

        String sql = "SELECT " + columnaPerdedor + " FROM PARTIDO WHERE idPartido = ?";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, idPartido);
        ResultSet rs = preparedStmt.executeQuery();
        rs.absolute(1);
        return rs.getInt(1);
    }
}
