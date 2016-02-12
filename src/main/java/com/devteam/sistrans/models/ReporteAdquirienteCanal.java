package com.devteam.sistrans.models;

/**
 * Created by Alex on 30/11/2015
 */
public class ReporteAdquirienteCanal {
    String fecha;
    String adquiriente;
    String canal;
    String numeroTx;
    String monto;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAdquiriente() {
        return adquiriente;
    }

    public void setAdquiriente(String adquiriente) {
        this.adquiriente = adquiriente;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getNumeroTx() {
        return numeroTx;
    }

    public void setNumeroTx(String numeroTx) {
        this.numeroTx = numeroTx;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
