package com.pencaucu.backend.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {

    String cedulaIdentidad;
    String nombre;
    String apellido;
    String rol;
    String fechaNacimiento;
    String email;
    String idCarrera;
    String userId;
    String puntaje;
    String idCampeon;
    String puntosPorCampeon;
    String idSubcampeon;
    String puntosPorSubcampeon;

    public Usuario(ResultSet rs) throws SQLException {
        this.cedulaIdentidad = String.valueOf(rs.getInt(4));
        this.nombre = rs.getString(5);
        this.apellido = rs.getString(6);
        this.rol = rs.getString(3);
        this.fechaNacimiento = String.valueOf(rs.getDate(7));
        this.email = rs.getString(8);
        this.idCarrera = String.valueOf(rs.getInt(9));
        this.userId = rs.getString(1);
        this.puntaje = Integer.toString(rs.getInt(10));
        this.idCampeon = Integer.toString(rs.getInt(11));
        this.puntosPorCampeon = Integer.toString(rs.getInt(12));
        this.idSubcampeon = Integer.toString(rs.getInt(13));
        this.puntosPorSubcampeon = Integer.toString(rs.getInt(14));
    }

    public Usuario(String cedulaIdentidad, String nombre, String apellido, String rol, String fechaNacimiento, String email,
            String idCarrera, String userId, String idCampeon, String puntosPorCampeon, String idSubcampeon,
            String puntosPorSubcampeon) {
        this.cedulaIdentidad = cedulaIdentidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.idCarrera = idCarrera;
        this.userId = userId;
        this.puntaje = "0";
        this.idCampeon = idCampeon;
        this.puntosPorCampeon = puntosPorCampeon;
        this.idSubcampeon = idSubcampeon;
        this.puntosPorSubcampeon = puntosPorSubcampeon;
    }

    public Usuario() {
    }

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdCampeon() {
        return idCampeon;
    }

    public void setIdCampeon(String idCampeon) {
        this.idCampeon = idCampeon;
    }

    public String getPuntosPorCampeon() {
        return puntosPorCampeon;
    }

    public void setPuntosPorCampeon(String puntosPorCampeon) {
        this.puntosPorCampeon = puntosPorCampeon;
    }

    public String getIdSubcampeon() {
        return idSubcampeon;
    }

    public void setIdSubcampeon(String idSubcampeon) {
        this.idSubcampeon = idSubcampeon;
    }

    public String getPuntosPorSubcampeon() {
        return puntosPorSubcampeon;
    }

    public void setPuntosPorSubcampeon(String puntosPorSubcampeon) {
        this.puntosPorSubcampeon = puntosPorSubcampeon;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return "Alumno [cedulaIdentidad=" + cedulaIdentidad + ", nombre=" + nombre + ", apellido=" + apellido
                + ", fechaNacimiento=" + fechaNacimiento + ", email=" + email + ", idCarrera=" + idCarrera + ", userId="
                + userId + ", puntaje=" + puntaje + ", idCampeon=" + idCampeon + ", puntosPorCampeon="
                + puntosPorCampeon + ", idSubcampeon=" + idSubcampeon + ", puntosPorSubcampeon=" + puntosPorSubcampeon
                + ", getCedulaIdentidad()=" + getCedulaIdentidad() + ", getNombre()=" + getNombre() + ", getApellido()="
                + getApellido() + ", getFechaNacimiento()=" + getFechaNacimiento() + ", getEmail()=" + getEmail()
                + ", getIdCarrera()=" + getIdCarrera() + ", getUserId()=" + getUserId() + ", getIdCampeon()="
                + getIdCampeon() + ", getPuntosPorCampeon()=" + getPuntosPorCampeon() + ", getIdSubcampeon()="
                + getIdSubcampeon() + ", getPuntosPorSubcampeon()=" + getPuntosPorSubcampeon() + ", getPuntaje()="
                + getPuntaje() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    
}
