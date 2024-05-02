package com.pencaucu.backend.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Prediccion {

    private String userId;
    private String idPartido;
    private String resultadoEquipo1;
    private String resultadoequipo2;
    private String puntajeObtenido;

    public String getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(String puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    public Prediccion(ResultSet rs) throws SQLException {
        this.userId = rs.getString(1);
        this.idPartido = Integer.toString(rs.getInt(2));
        this.resultadoEquipo1 = Integer.toString(rs.getInt(3));
        this.resultadoequipo2 = Integer.toString(rs.getInt(4));
        this.puntajeObtenido = Integer.toString(rs.getInt(5));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public String getResultadoEquipo1() {
        return resultadoEquipo1;
    }

    public void setResultadoEquipo1(String resultadoEquipo1) {
        this.resultadoEquipo1 = resultadoEquipo1;
    }

    public String getResultadoequipo2() {
        return resultadoequipo2;
    }

    public void setResultadoequipo2(String resultadoequipo2) {
        this.resultadoequipo2 = resultadoequipo2;
    }

}
