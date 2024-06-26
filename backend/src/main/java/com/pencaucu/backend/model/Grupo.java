package com.pencaucu.backend.model;

import java.util.ArrayList;

public class Grupo {

    private String id;
    private ArrayList<Equipo> equipos;
    
    public Grupo() {
        equipos = new ArrayList<>(4);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }
    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

}
