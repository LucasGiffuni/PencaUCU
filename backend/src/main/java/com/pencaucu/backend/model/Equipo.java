package com.pencaucu.backend.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipo {

    private String id;
    private String nombre;
    private String urlBandera;
    private String status;

    public Equipo(ResultSet rs) throws SQLException {
        this.id = Integer.toString(rs.getInt(1));
        this.nombre = rs.getString(2);
        this.urlBandera = rs.getString(3);
        this.status = Boolean.toString(rs.getBoolean(4));
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

}
