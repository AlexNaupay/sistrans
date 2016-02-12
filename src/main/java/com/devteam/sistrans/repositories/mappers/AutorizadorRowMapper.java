package com.devteam.sistrans.repositories.mappers;


import com.devteam.sistrans.entities.Autorizador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorizadorRowMapper implements RowMapper<Autorizador> {
    @Override
    public Autorizador mapRow(ResultSet resultSet, int i) throws SQLException {
        Autorizador autorizador = new Autorizador();

        autorizador.setCodigo(resultSet.getString("CODIGO"));
        autorizador.setNombre(resultSet.getString("NOMBRE"));

        return autorizador;
    }
}
