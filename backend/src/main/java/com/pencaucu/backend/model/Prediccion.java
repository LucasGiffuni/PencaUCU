package com.pencaucu.backend.model;

public class Prediccion {

    private String cedulaIdentidad;
    private String idPartido;
    private String idEquipoGanador;
    private String resultadoEquipo1;
    private String resultadoequipo2;

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public String getIdEquipoGanador() {
        return idEquipoGanador;
    }

    public void setIdEquipoGanador(String idEquipoGanador) {
        this.idEquipoGanador = idEquipoGanador;
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
