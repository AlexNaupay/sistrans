package com.devteam.sistrans.repositories.mappers;


import com.devteam.sistrans.entities.Canal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CanalRowMapper implements RowMapper<Canal> {
    @Override
    public Canal mapRow(ResultSet resultSet, int i) throws SQLException {
        Canal canal = new Canal();

        canal.setCodigo(resultSet.getString("CODIGO"));
        canal.setNombre(resultSet.getString("NOMBRE"));

        return canal;
    }
}
