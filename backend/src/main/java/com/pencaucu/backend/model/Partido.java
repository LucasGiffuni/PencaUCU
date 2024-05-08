package com.pencaucu.backend.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Partido {

    private String id;
    private String idEquipo1;
    private String nombreEquipo1;
    private String urlBanderaEquipo1;
    private String puntajeEquipo1;

    private String idEquipo2;
    private String nombreEquipo2;
    private String urlBanderaEquipo2;
    private String puntajeEquipo2;

    private String fecha;
    private String idEstadio;
    private String etapa;
    private String jugado;

    public Partido() {
    }

    public Partido(ResultSet rs) throws SQLException {
        this.id = Integer.toString(rs.getInt(1));
        this.idEquipo1 = Integer.toString(rs.getInt(2));
        this.puntajeEquipo1 = Integer.toString(rs.getInt(3));
        this.idEquipo2 = Integer.toString(rs.getInt(4));
        this.puntajeEquipo2 = Integer.toString(rs.getInt(5));
        this.fecha = rs.getTimestamp(6).toString();
        this.idEstadio = rs.getString(7);
        this.etapa = rs.getString(8);
        this.jugado = Boolean.toString(rs.getBoolean(9));
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

    public String getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(String idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getNombreEquipo1() {
        return nombreEquipo1;
    }

    public void setNombreEquipo1(String nombreEquipo1) {
        this.nombreEquipo1 = nombreEquipo1;
    }

    public String getUrlBanderaEquipo1() {
        return urlBanderaEquipo1;
    }

    public void setUrlBanderaEquipo1(String urlBanderaEquipo1) {
        this.urlBanderaEquipo1 = urlBanderaEquipo1;
    }

    public String getNombreEquipo2() {
        return nombreEquipo2;
    }

    public void setNombreEquipo2(String nombreEquipo2) {
        this.nombreEquipo2 = nombreEquipo2;
    }

    public String getUrlBanderaEquipo2() {
        return urlBanderaEquipo2;
    }

    public void setUrlBanderaEquipo2(String urlBanderaEquipo2) {
        this.urlBanderaEquipo2 = urlBanderaEquipo2;
    }

}
