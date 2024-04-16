package com.pencaucu.backend.model;

import java.time.LocalDateTime;

public class Partido {

    private String id;
    private String etapa;
    private Equipo equipo1;
    private int puntajeEquipo1;
    private Equipo equipo2;
    private int puntajeEquipo2;
    private LocalDateTime fecha;
    private String camino;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public int getPuntajeEquipo1() {
        return puntajeEquipo1;
    }

    public void setPuntajeEquipo1(int puntajeEquipo1) {
        this.puntajeEquipo1 = puntajeEquipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public int getPuntajeEquipo2() {
        return puntajeEquipo2;
    }

    public void setPuntajeEquipo2(int puntajeEquipo2) {
        this.puntajeEquipo2 = puntajeEquipo2;
    }

    public String getCamino() {
        return camino;
    }

    public void setCamino(String camino) {
        this.camino = camino;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

}
