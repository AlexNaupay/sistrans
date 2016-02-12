package com.devteam.sistrans.repositories.mappers;

import com.devteam.sistrans.entities.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRowMapper implements RowMapper<Usuario> {

    public Usuario mapRow(ResultSet resultSet, int i) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsername(resultSet.getString("USERNAME"));
        usuario.setPassword(resultSet.getString("PASSWORD"));
        usuario.setNombre(resultSet.getString("NOMBRE_USUARIO"));
        usuario.setEstado((short) resultSet.getInt("ESTADO_USUARIO"));

        usuario.setFechaRegistro(resultSet.getString("FECHA_REGISTRO"));
        usuario.setHoraRegistro(resultSet.getString("HORA_REGISTRO"));
        return usuario;
    }
}
