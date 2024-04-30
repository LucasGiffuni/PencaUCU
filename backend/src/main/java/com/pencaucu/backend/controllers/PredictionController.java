package com.pencaucu.backend.controllers;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PredictionController {

    @GetMapping("/alumno/get")
    public ResponseEntity<String> list() {
        System.out.println("PruebaController");
        return new ResponseEntity("1", HttpStatus.OK);
    }
}