package com.example.sebapls.hiremeaplicacion;

import java.util.Date;

/**
 * Created by sebapls on 30/11/2017.
 */

public class Solicitudes {

    private int idSolicitud;
    private String NombreProyecto;

    public Solicitudes(){

    }

    public Solicitudes(int idSolicitud, String nombreProyecto) {
        this.idSolicitud = idSolicitud;
        NombreProyecto = nombreProyecto;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNombreProyecto() {
        return NombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        NombreProyecto = nombreProyecto;
    }
    @Override
    public String toString(){
        return NombreProyecto;
    }
}
