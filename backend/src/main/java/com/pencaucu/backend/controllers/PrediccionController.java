package com.pencaucu.backend.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam int resultadoEquipo1, @RequestParam int resultadoEquipo2,
            @RequestParam int CI)
            throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.cargarPrediccion(CI, idPartido, resultadoEquipo1, resultadoEquipo2));
    }

}
