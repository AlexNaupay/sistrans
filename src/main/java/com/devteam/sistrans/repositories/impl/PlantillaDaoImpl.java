package com.devteam.sistrans.repositories.impl;

import com.devteam.sistrans.entities.Campo;
import com.devteam.sistrans.entities.Usuario;
import com.devteam.sistrans.repositories.PlantillaDao;
import com.devteam.sistrans.repositories.mappers.CampoRowMapper;
import com.devteam.sistrans.repositories.mappers.UsuarioRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class PlantillaDaoImpl implements PlantillaDao{

    private DataSource dataSource;
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Campo actualizarCampo(Campo campo) throws DataAccessException{
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("USP_ACTUALIZAR_CAMPO")
                .returningResultSet("fields",new CampoRowMapper());

        SqlParameterSource sqlParameterSourceIn = new MapSqlParameterSource()
                .addValue("IN_CODIGO",campo.getCodigo())
                .addValue("IN_ORDEN", campo.getOrden())
                .addValue("IN_LONGITUD", campo.getLongitud())
                .addValue("IN_DESCRIPCION", campo.getDescripcion());

        Map map = simpleJdbcCall.execute(sqlParameterSourceIn);

        List list = (List) map.get("fields");
        if (list.size() == 0)
            return null;
        return (Campo)list.get(0);
    }

    @Override
    public List<Campo> obtenerCampos() throws DataAccessException {
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("SP_OBTENER_CAMPOS")
                .returningResultSet("fields", new CampoRowMapper());

        Map map = simpleJdbcCall.execute();

        return (List) map.get("fields");
    }

    @Override
    public Campo obtenerCampo(String codigoCampo) throws DataAccessException{
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("USP_OBTENER_CAMPO")
                .returningResultSet("fields",new CampoRowMapper());

        SqlParameterSource sqlParameterSourceIn = new MapSqlParameterSource()
                .addValue("IN_CODIGO", codigoCampo);

        Map map = simpleJdbcCall.execute(sqlParameterSourceIn);

        List list = (List) map.get("fields");
        if (list.size() == 0)
            return null;
        return (Campo)list.get(0);
    }
}
