package com.devteam.sistrans.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;

/**
 * @author alexh
 */
public class Opcion implements Serializable{
    //o.COD_OPCION,o.NOMBRE_OPCION,o.DESC_OPCION, o.URI_OPCION,o.ORDEN_OPCION,o.ESTADO,o.COD_SISTEMA,
    // o.USUARIO_REGISTRO,o.FECHA_REGRISTRO,o.HORA_REGISTRO
    private String codigo;
    private String nombre;
    private String descripcion;
    private String uri;
    private int orden;
    private short estado;
    private String codigoSistema;
    private String usuarioRegistro;
    private String fechaRegistro;
    private String horaRegistro;

    public String getCodigo() {
        return codigo;
    }

    public Opcion setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Opcion setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Opcion setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public Opcion setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public int getOrden() {
        return orden;
    }

    public Opcion setOrden(int orden) {
        this.orden = orden;
        return this;
    }

    public short getEstado() {
        return estado;
    }

    public Opcion setEstado(short estado) {
        this.estado = estado;
        return this;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public Opcion setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
        return this;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public Opcion setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
        return this;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public Opcion setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public Opcion setHoraRegistro(String horaRegistro) {
        this.horaRegistro = horaRegistro;
        return this;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
