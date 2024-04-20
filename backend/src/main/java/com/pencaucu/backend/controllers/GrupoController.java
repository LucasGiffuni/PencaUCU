package com.pencaucu.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pencaucu.backend.model.responses.GetGrupoResponse;
import com.pencaucu.backend.service.impl.GrupoService;

@RestController()
@CrossOrigin(origins = "*")
public class GrupoController {

    @Autowired
    private GrupoService service;

    @GetMapping("/group/{groupId}/getGrupo")
    public ResponseEntity<GetGrupoResponse> getGrupo(@PathVariable String groupId) {
        return ResponseEntity.ok(service.getGrupoById(groupId));
    }

}
