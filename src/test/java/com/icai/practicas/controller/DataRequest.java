package com.icai.practicas.controller;

import java.io.Serializable;

public class DataRequest implements Serializable {
    private String fullName;
    private String dni;
    private String telefono;

    public DataRequest(String fullName) {
        this.setFullName(fullName);
    }

    public DataRequest(String fullName, String dni, String telefono) {
        this.setFullName(fullName);
        this.setDni(dni);
        this.setTelefono(telefono);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
