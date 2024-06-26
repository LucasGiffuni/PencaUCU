package com.pencaucu.backend.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pencaucu.backend.model.responses.GetEquipoResponse;
import com.pencaucu.backend.model.responses.GetGrupoResponse;
import com.pencaucu.backend.service.impl.GrupoService;

@RestController()
@RequestMapping("/grupo")
@CrossOrigin(origins = "*")
public class GrupoController {

    @Autowired
    private GrupoService service;

    @GetMapping("/{groupId}/getGrupo")
    public ResponseEntity<GetGrupoResponse> getGrupo(@PathVariable String groupId) throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(service.getGrupoById(groupId));
    }   

}
