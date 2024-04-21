package com.pencaucu.backend.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipoConPuntaje extends Equipo {

    private String puntaje;
    
    public EquipoConPuntaje(ResultSet rs) throws SQLException {
        super(rs);
        this.puntaje = Integer.toString(rs.getInt(5));
    }
    
    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

}
