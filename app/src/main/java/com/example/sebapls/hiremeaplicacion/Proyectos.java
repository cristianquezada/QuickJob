package com.example.sebapls.hiremeaplicacion;

/**
 * Created by sebapls on 10-07-2017.
 */

public class Proyectos {

    private String nombrep, fechainicio,fechatermino,descrip;
    private int cantper;

    public Proyectos(String nombrep,String fechainicio,String fechatermino, int cantper, String descrip ){
        this.setNombrep(nombrep);
        this.setFechainicio(fechainicio);
        this.setFechatermino(fechatermino);
        this.setCantper(cantper);
        this.setDescrip(descrip);
    }

    public String getNombrep() {
        return nombrep;
    }

    public void setNombrep(String nombrep) {
        this.nombrep = nombrep;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechatermino() {
        return fechatermino;
    }

    public void setFechatermino(String fechatermino) {
        this.fechatermino = fechatermino;
    }

    public int getCantper() {
        return cantper;
    }

    public void setCantper(int cantper) {
        this.cantper = cantper;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
