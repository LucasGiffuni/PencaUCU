package com.pencaucu.backend.controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pencaucu.backend.BackendApplication;
import com.pencaucu.backend.model.responses.GetEquiposResponse;
import com.pencaucu.backend.model.responses.LoginResponse;
import com.pencaucu.backend.model.responses.RegisterResponse;
import com.pencaucu.backend.service.impl.EquipoService;
import com.pencaucu.backend.service.impl.UserServiceImpl;

@RestController()
@CrossOrigin(origins = "*")
public class PublicController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EquipoService equipoService;
    
    private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    @GetMapping("/public/getEquipos")
    public ResponseEntity<GetEquiposResponse> getEquipos() throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(equipoService.getEquipos());
    }

    @PostMapping("/public/login")
    public ResponseEntity<LoginResponse> validateUser(@RequestParam String user, @RequestParam String clave)
            throws NoSuchAlgorithmException {

        LoginResponse response = userService.login(user, clave);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/public/register")
    public ResponseEntity<RegisterResponse> createUser(@RequestParam String user, @RequestParam String clave)
            throws NoSuchAlgorithmException {
        System.out.println(user);
        System.out.println(clave);
        return ResponseEntity.ok(userService.register(user, clave));
    }
}
