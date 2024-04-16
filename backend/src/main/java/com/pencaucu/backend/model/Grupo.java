package com.pencaucu.backend.model;

public class Grupo {

    private String id;
    private Equipo[] equipos;
    
    public Grupo() {
        equipos = new Equipo[4];
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Equipo[] getEquipos() {
        return equipos;
    }
    public void setEquipos(Equipo[] equipos) {
        this.equipos = equipos;
    }

}
