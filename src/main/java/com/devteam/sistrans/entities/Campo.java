package com.devteam.sistrans.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author alexh
 */
public class Campo {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int longitud;
    private String tipo;
    private int orden;
    private boolean editable;
    private int longitudMinama;
    private int longitudMaxima;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getLongitudMinama() {
        return longitudMinama;
    }

    public void setLongitudMinama(int longitudMinama) {
        this.longitudMinama = longitudMinama;
    }

    public int getLongitudMaxima() {
        return longitudMaxima;
    }

    public void setLongitudMaxima(int longitudMaxima) {
        this.longitudMaxima = longitudMaxima;
    }

    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
