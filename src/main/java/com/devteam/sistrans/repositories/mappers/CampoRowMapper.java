package com.devteam.sistrans.repositories.mappers;

import com.devteam.sistrans.entities.Campo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CampoRowMapper implements RowMapper<Campo> {

    @Override
    public Campo mapRow(ResultSet resultSet, int i) throws SQLException {
        Campo campo = new Campo();
        campo.setCodigo(resultSet.getString("COD_CAMPO"));
        campo.setNombre(resultSet.getString("NOMBRE_CAMPO"));
        campo.setDescripcion(resultSet.getString("DESC_CAMPO"));
        campo.setLongitud(resultSet.getInt("LONGITUD_CAMPO"));
        campo.setTipo(resultSet.getString("TIPO_CAMPO"));
        campo.setOrden(resultSet.getInt("ORDEN_CAMPO"));
        campo.setEditable(resultSet.getBoolean("EDITABLE"));
        campo.setLongitudMinama(resultSet.getInt("LONGITUD_MINIMA"));
        campo.setLongitudMaxima(resultSet.getInt("LONGITUD_MAXIMA"));
        return campo;
    }
}
