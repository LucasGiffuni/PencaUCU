package com.pencaucu.backend.model.responses;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetallePrediccionUsuario {

    private String idPartido;
    private String idEquipo1;
    private String resultadoEquipo1;
    private String nombreEquipo1;
    private String urlBanderaEquipo1;
    private String idEquipo2;
    private String resultadoEquipo2;
    private String nombreEquipo2;
    private String urlBanderaEquipo2;

    public DetallePrediccionUsuario(ResultSet rs) throws SQLException {
        this.idPartido = rs.getString(1);
        this.idEquipo1 = Integer.toString(rs.getInt(2));
        this.resultadoEquipo1 = Integer.toString(rs.getInt(3));
        this.nombreEquipo1 = rs.getString(4);
        this.urlBanderaEquipo1 = rs.getString(5);

        this.idEquipo2 = Integer.toString(rs.getInt(6));
        this.resultadoEquipo2 = Integer.toString(rs.getInt(7));
        this.nombreEquipo1 = rs.getString(8);
        this.urlBanderaEquipo1 = rs.getString(9);

    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public String getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(String idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public String getResultadoEquipo1() {
        return resultadoEquipo1;
    }

    public void setResultadoEquipo1(String resultadoEquipo1) {
        this.resultadoEquipo1 = resultadoEquipo1;
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

    public String getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(String idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public String getResultadoEquipo2() {
        return resultadoEquipo2;
    }

    public void setResultadoEquipo2(String resultadoEquipo2) {
        this.resultadoEquipo2 = resultadoEquipo2;
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
