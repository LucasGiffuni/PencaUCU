package com.pencaucu.backend.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.responses.GetEquipoResponse;
import com.pencaucu.backend.service.impl.EquipoService;

@RestController()
@RequestMapping("/equipo")
@CrossOrigin(origins = "*")
public class EquipoController {

    @Autowired
    private EquipoService service;

    @GetMapping("/{idEquipo}/getEquipo")
    public ResponseEntity<GetEquipoResponse> getEquipo(@PathVariable int idEquipo) throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.getEquipoById(idEquipo));
    }

    @GetMapping("/getEquipos")
    public ResponseEntity<List<Equipo>> getEquipos() throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.getEquipos());
    }

}
