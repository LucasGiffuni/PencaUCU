package com.pencaucu.backend.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Partido {

    private String id;
    private String idEquipo1;
    private String puntajeEquipo1;
    private String idEquipo2;
    private String puntajeEquipo2;
    private String fecha;
    private String etapa;
    private String camino;
    private String idGanador;
    private String jugado;

    public Partido() {
    }

    public Partido(ResultSet rs) throws SQLException {
        setId(Integer.toString(rs.getInt(1)));
        setIdEquipo1(Integer.toString(rs.getInt(2)));
        setPuntajeEquipo1(Integer.toString(rs.getInt(3)));
        setIdEquipo2(Integer.toString(rs.getInt(4)));
        setPuntajeEquipo2(Integer.toString(rs.getInt(5)));
        setFecha(rs.getTimestamp(6).toString());
        setEtapa(rs.getString(7));
        setIdGanador(Integer.toString(rs.getInt(8)));
        setJugado(Boolean.toString(rs.getBoolean(9)));
    }

    public String getJugado() {
        return jugado;
    }

    public void setJugado(String jugado) {
        this.jugado = jugado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getPuntajeEquipo1() {
        return puntajeEquipo1;
    }

    public void setPuntajeEquipo1(String puntajeEquipo1) {
        this.puntajeEquipo1 = puntajeEquipo1;
    }


    public String getPuntajeEquipo2() {
        return puntajeEquipo2;
    }

    public void setPuntajeEquipo2(String puntajeEquipo2) {
        this.puntajeEquipo2 = puntajeEquipo2;
    }

    public String getCamino() {
        return camino;
    }

    public void setCamino(String camino) {
        this.camino = camino;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(String idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public String getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(String idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdGanador() {
        return idGanador;
    }

    public void setIdGanador(String idGanador) {
        this.idGanador = idGanador;
    }

}
