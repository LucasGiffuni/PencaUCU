package com.pencaucu.backend.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.pencaucu.backend.model.Equipo;
import com.pencaucu.backend.model.Partido;
import com.pencaucu.backend.model.responses.CrearPartidoResponse;
import com.pencaucu.backend.model.responses.DefaultResponse;

@Service
public class PartidoService {

    public List<Partido> partidos = new ArrayList<Partido>();

    public CrearPartidoResponse crearPartido(Equipo e1, Equipo e2) {
        DefaultResponse DR = new DefaultResponse("200", "OK");
        Partido partido = new Partido();

        partido.setId("A1");
        partido.setEtapa("Fase de grupos");
        partido.setEquipo1(e1);
        partido.setEquipo2(e2);
        partido.setFecha(LocalDateTime.of(2024, 01, 01, 12, 00, 00));

        partidos.add(partido);

        return new CrearPartidoResponse(DR, partido);
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public List<Partido> getProximosPartidos() {
        List<Partido> proximosPartidos = new ArrayList<Partido>();
        for (Partido partido : this.partidos) {
            if (partido.getFecha().isAfter(LocalDateTime.now())) {
                proximosPartidos.add(partido);
            }
        }
        return proximosPartidos;
    }

    public List<Partido> getPartidosJugados() {
        // List<Partido> proximosPartidos = new ArrayList<Partido>();
        // for (Partido partido : this.partidos) {
        //     if (partido.getFecha().isBefore(LocalDateTime.now())) {
        //         proximosPartidos.add(partido);
        //     }
        // }
        List<Partido> proximosPartidos = this.partidos.stream().filter(p -> p.getFecha().isBefore(LocalDateTime.now())).collect(Collectors.toList());
        return proximosPartidos;
    }

    public CrearPartidoResponse cargarResultadoPartido(String id, int puntajeEquipo1, int puntajeEquipo2) {
        Partido partido = this.partidos.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        partido.setPuntajeEquipo1(puntajeEquipo1);
        partido.setPuntajeEquipo2(puntajeEquipo2);

        DefaultResponse DR = new DefaultResponse("200", "OK");

        return new CrearPartidoResponse(DR, partido);
    }
}
