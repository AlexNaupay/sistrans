package com.devteam.sistrans.repositories.impl;

import com.devteam.sistrans.entities.Opcion;
import com.devteam.sistrans.repositories.OpcionDao;
import com.devteam.sistrans.repositories.mappers.OpcionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository("opcionDao")
public class OpcionDaoImpl implements OpcionDao{

    private DataSource dataSource;
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Opcion> opcionesPorUsuario(String username, String sistema) {
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("SP_OBTENER_OPCIONES_USUARIO")
                .returningResultSet("options", new OpcionRowMapper());

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("in_username", username)
                .addValue("in_sistema", sistema);

        Map map = simpleJdbcCall.execute(in);

        return (List) map.get("options");
    }
}
