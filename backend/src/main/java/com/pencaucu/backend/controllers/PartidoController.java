package com.pencaucu.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.Partido;
import com.pencaucu.backend.model.responses.CrearPartidoResponse;
import com.pencaucu.backend.services.PartidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController()
@CrossOrigin(origins = "*")
public class PartidoController {
    
    @Autowired
    private PartidoService service;

    @PostMapping("/crearPartido")
    public ResponseEntity<CrearPartidoResponse> crearPartido(@RequestBody List<Equipo> equipos) {
        return ResponseEntity.ok(service.crearPartido(equipos.get(0), equipos.get(1)));
    }

    @GetMapping("/getPartidos")
    public ResponseEntity<List<Partido>> getPartidos() {
        return ResponseEntity.ok(service.getPartidos());
    }

    @GetMapping("/getProximosPartidos")
    public ResponseEntity<List<Partido>> getProximosPartidos() {
        return ResponseEntity.ok(service.getProximosPartidos());
    }

    @GetMapping("/getPartidosJugados")
    public ResponseEntity<List<Partido>> getPartidosJugados() {
        return ResponseEntity.ok(service.getPartidosJugados());
    }

    @PutMapping("partido/{id}/cargarResultadoPartido")
    public ResponseEntity<CrearPartidoResponse> cargarResultadoPartido(String idPartido, int puntajeEquipo1, int puntajeEquipo2) {
        return ResponseEntity.ok(service.cargarResultadoPartido(idPartido, puntajeEquipo1, puntajeEquipo2));
    }
}
