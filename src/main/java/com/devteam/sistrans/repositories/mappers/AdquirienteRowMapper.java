package com.devteam.sistrans.repositories.mappers;


import com.devteam.sistrans.entities.Adquiriente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdquirienteRowMapper implements RowMapper<Adquiriente> {
    @Override
    public Adquiriente mapRow(ResultSet resultSet, int i) throws SQLException {
        Adquiriente adquirienete = new Adquiriente();

        adquirienete.setCodigo(resultSet.getString("CODIGO"));
        adquirienete.setNombre(resultSet.getString("NOMBRE"));

        return adquirienete;
    }
}
