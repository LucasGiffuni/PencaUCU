package com.pencaucu.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.Partido;
import com.pencaucu.backend.model.responses.CrearPartidoResponse;
import com.pencaucu.backend.model.responses.GetProximosPartidosResponse;
import com.pencaucu.backend.service.impl.PartidoService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController()
@RequestMapping("/partido")
@CrossOrigin(origins = "*")
public class PartidoController {

    @Autowired
    private PartidoService service;

    @PostMapping("/crearPartido")
    public ResponseEntity<CrearPartidoResponse> crearPartido(@RequestBody Partido partido)
            throws SQLException, ParseException, ClassNotFoundException {
        return ResponseEntity.ok(service.crearPartido(partido));
    }

    @GetMapping("/{idPartido}/getPartido")
    public ResponseEntity<CrearPartidoResponse> getPartido(@PathVariable int idPartido) throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.getPartidoById(idPartido));
    }

    @GetMapping("/getPartidos")
    public ResponseEntity<List<Partido>> getPartidos() throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.getPartidos());
    }

    @GetMapping("/getProximosPartidos")
    public ResponseEntity<GetProximosPartidosResponse> getProximosPartidos() throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.getProximosPartidos());
    }

    @GetMapping("/getPartidosJugados")
    public ResponseEntity<List<Partido>> getPartidosJugados() throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.getPartidosJugados());
    }

    @PutMapping("/{idPartido}/cargarResultadoPartido")
    public ResponseEntity<CrearPartidoResponse> cargarResultadoPartido(@PathVariable int idPartido,
            @RequestParam int puntajeEquipo1, @RequestParam int puntajeEquipo2)
            throws SQLException, ClassNotFoundException {
        return ResponseEntity.ok(service.cargarResultadoPartido(idPartido, puntajeEquipo1, puntajeEquipo2));
    }

}
