package com.devteam.sistrans.models;

/**
 * Created by Alex on 30/11/2015
 */
public class ReporteDetallado {

    String fecha;
    String hora;
    String tarjeta;
    String autorizador;
    String adquiriente;
    String canal;
    String monto;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
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

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
