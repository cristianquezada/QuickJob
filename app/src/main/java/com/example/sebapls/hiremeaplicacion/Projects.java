package com.example.sebapls.hiremeaplicacion;

/**
 * Created by sebapls on 24-07-2017.
 */

public class Projects {

    private int id;
    private String Nombre, Descripcion, FechaIni, FechaTerm, Cant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFechaIni() {
        return FechaIni;
    }

    public void setFechaIni(String fechaIni) {
        FechaIni = fechaIni;
    }

    public String getFechaTerm() {
        return FechaTerm;
    }

    public void setFechaTerm(String fechaTerm) {
        FechaTerm = fechaTerm;
    }

    public String getCant() {
        return Cant;
    }

    public void setCant(String cant) {
        Cant = cant;
    }

    public Projects(int idProyecto, String nombreProyecto) {}

    public Projects (int id, String nombre, String descripcion, String fechaIni, String fechaTerm, String cant){
        this.setId(id);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setFechaIni(fechaIni);
        this.setFechaTerm(fechaTerm);
        this.setCant(cant);

    }
}
