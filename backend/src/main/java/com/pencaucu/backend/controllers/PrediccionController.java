package com.pencaucu.backend.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pencaucu.backend.model.Prediccion;
import com.pencaucu.backend.model.responses.ConsultarPrediccionesPorUsuarioResponse;
import com.pencaucu.backend.model.responses.CrearPrediccionResponse;
import com.pencaucu.backend.service.impl.PrediccionService;

@RestController()
@RequestMapping("/prediccion")
@CrossOrigin(origins = "*")
public class PrediccionController {

    @Autowired
    private PrediccionService service;

    @PostMapping("/{idPartido}/cargarPrediccion")
    public ResponseEntity<CrearPrediccionResponse> cargarPredicccion(@PathVariable int idPartido,
            @RequestParam String userId, @RequestParam int resultadoEquipo1, @RequestParam int resultadoEquipo2)
            throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.cargarPrediccion(userId, idPartido, resultadoEquipo1, resultadoEquipo2));
    }

    @PutMapping("/{idPartido}/modificarPrediccion")
    public ResponseEntity<CrearPrediccionResponse> modificarPredicccion(@PathVariable int idPartido,
            @RequestParam String userId, @RequestParam int resultadoEquipo1, @RequestParam int resultadoEquipo2)
            throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.modificarPrediccion(userId, idPartido, resultadoEquipo1, resultadoEquipo2));
    }

    @GetMapping("/{userId}/consultarPredicciones")
    public ResponseEntity<ConsultarPrediccionesPorUsuarioResponse> consultarPredicciones(@PathVariable String userId)
            throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.consultarPredicciones(userId));
    }

    @GetMapping("{userId}/consultarPrediccion")
    public ResponseEntity<CrearPrediccionResponse> consultarPrediccion(@PathVariable String userId,
            @RequestParam int idPartido)
            throws ClassNotFoundException, SQLException {

        return ResponseEntity.ok(service.consultarPrediccion(userId, idPartido));
    }

}
