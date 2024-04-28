package com.pencaucu.backend.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipo {

    private String id;
    private String nombre;
    private String urlBandera;
    private String status;
    private String idGrupo;
    private String puntaje;
    private String etapaActual;

    public Equipo(ResultSet rs) throws SQLException {
        this.id = Integer.toString(rs.getInt(1));
        this.nombre = rs.getString(2);
        this.urlBandera = rs.getString(3);
        this.status = rs.getString(4);
        this.idGrupo = rs.getString(5);
        this.puntaje = Integer.toString(rs.getInt(6));
        this.etapaActual = rs.getString(7);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlBandera() {
        return urlBandera;
    }

    public void setUrlBandera(String urlBandera) {
        this.urlBandera = urlBandera;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

    public String getEtapaActual() {
        return etapaActual;
    }

    public void setEtapaActual(String etapaActual) {
        this.etapaActual = etapaActual;
    }

}
