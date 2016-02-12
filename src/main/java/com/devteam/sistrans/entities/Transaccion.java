package com.devteam.sistrans.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Transaccion {
    private long codigo;
    private String fecha;
    private String hora;
    private String tarjeta;
    private String monto;
    private String canal;
    private String adquiriente;
    private String autorizador;
    private String raw;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String rawdata) {
        this.raw = rawdata;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

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

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getAdquiriente() {
        return adquiriente;
    }

    public void setAdquiriente(String adquiriente) {
        this.adquiriente = adquiriente;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
