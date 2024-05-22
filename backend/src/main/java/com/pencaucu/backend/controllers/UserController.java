package com.pencaucu.backend.controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.pencaucu.backend.BackendApplication;
import com.pencaucu.backend.model.Alumno;
import com.pencaucu.backend.model.responses.CreateAlumnoResponse;
import com.pencaucu.backend.model.responses.LoginResponse;
import com.pencaucu.backend.model.responses.ObtenerCarrerasResponse;
import com.pencaucu.backend.model.responses.RegisterResponse;
import com.pencaucu.backend.service.impl.UserServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/alumno/crearAlumno")
    public ResponseEntity<CreateAlumnoResponse> createAlumno(@RequestBody Alumno alumno)
            throws NoSuchAlgorithmException {
        return ResponseEntity.ok(userService.createAlumno(alumno));
        }

    @GetMapping("/public/obtenerCarreras")
    public ResponseEntity<ObtenerCarrerasResponse> obtenerCarreras()
            throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

        return ResponseEntity.ok(userService.obtenerCarreras());
    }

    @GetMapping("/alumno/{CI}/obtenerAlumno")
    public ResponseEntity<CreateAlumnoResponse> obtenerAlumno(@PathVariable int CI)
            throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(userService.obtenerAlumno(CI));
    }

    @GetMapping("/alumno/getRankingAlumnos")
    public ResponseEntity<List<Alumno>> getRankingAlumnos()
            throws ClassNotFoundException, SQLException {
        return ResponseEntity.ok(userService.getRankingAlumnos());
    }

}
