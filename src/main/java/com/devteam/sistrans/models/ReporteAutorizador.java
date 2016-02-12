package com.devteam.sistrans.models;


public class ReporteAutorizador {

    private String fecha;
    private String autorizador;
    private String numeroTx;
    private String monto;


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
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
