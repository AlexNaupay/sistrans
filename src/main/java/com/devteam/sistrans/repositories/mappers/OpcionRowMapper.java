package com.devteam.sistrans.repositories.mappers;

import com.devteam.sistrans.entities.Opcion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OpcionRowMapper implements RowMapper<Opcion>{
    @Override
    public Opcion mapRow(ResultSet resultSet, int i) throws SQLException {
        Opcion opcion = new Opcion();
        opcion.setCodigo(resultSet.getString("COD_OPCION"))
                .setNombre(resultSet.getString("NOMBRE_OPCION"))
                .setDescripcion(resultSet.getString("DESC_OPCION"))
                .setUri(resultSet.getString("URI_OPCION"))
                .setOrden(resultSet.getInt("ORDEN_OPCION"))
                .setEstado(resultSet.getShort("ESTADO"))
                .setCodigoSistema(resultSet.getString("COD_SISTEMA"))
                .setUsuarioRegistro(resultSet.getString("USUARIO_REGISTRO"))
                .setFechaRegistro(resultSet.getString("FECHA_REGRISTRO"))
                .setHoraRegistro(resultSet.getString("HORA_REGISTRO"));

        return opcion;
    }
}
