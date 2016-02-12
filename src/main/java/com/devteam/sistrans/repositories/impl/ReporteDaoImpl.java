package com.devteam.sistrans.repositories.impl;

import com.devteam.sistrans.entities.Adquiriente;
import com.devteam.sistrans.entities.Autorizador;
import com.devteam.sistrans.entities.Canal;
import com.devteam.sistrans.models.ReporteAdquirienteCanal;
import com.devteam.sistrans.models.ReporteAutorizador;
import com.devteam.sistrans.models.ReporteDetallado;
import com.devteam.sistrans.repositories.ReporteDao;
import com.devteam.sistrans.repositories.mappers.*;
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
public class ReporteDaoImpl implements ReporteDao{
    private DataSource dataSource;
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Autorizador> obtenerAutorizadores() throws DataAccessException{
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("USP_OBTENER_AUTORIZADORES")
                .returningResultSet("autorizadores", new AutorizadorRowMapper());

        Map map = simpleJdbcCall.execute();

        return (List) map.get("autorizadores");
    }

    @Override
    public List<Adquiriente> obtenerAdquirientes() throws DataAccessException {
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("USP_OBTENER_ADQUIRIENTES")
                .returningResultSet("adquirientes", new AdquirienteRowMapper());

        Map map = simpleJdbcCall.execute();

        return (List) map.get("adquirientes");
    }

    @Override
    public List<Canal> obtenerCanales() throws DataAccessException {
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("USP_OBTENER_CANALES")
                .returningResultSet("canales", new CanalRowMapper());

        Map map = simpleJdbcCall.execute();

        return (List) map.get("canales");
    }

    @Override
    public List<ReporteAdquirienteCanal> reporteAdquirienetCanal(String fechaPrev, String fechaNext ,String adqFilters, String canalFilters) throws DataAccessException {
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("USP_REPORTE_TOTALES_ADQ_CANAL")
                .returningResultSet("results", new ReporteAdquirienteCanalMapper());

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("IN_FECHA_PREV", fechaPrev)
                .addValue("IN_FECHA_NEXT", fechaNext)
                .addValue("IN_ADQ_FILTERS",adqFilters)
                .addValue("IN_CANAL_FILTERS",canalFilters);

        Map map = simpleJdbcCall.execute(in);

        return (List) map.get("results");
    }

    @Override
    public List<ReporteAutorizador> reporteAutorizador(String fechaPrev, String fechaNext, String autFilters) throws DataAccessException{
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("USP_REPORTE_TOTALES_AUTORIZADOR")
                .returningResultSet("results", new ReporteAutorizadorMapper());

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("IN_FECHA_PREV", fechaPrev)
                .addValue("IN_FECHA_NEXT", fechaNext)
                .addValue("IN_AUT_FILTERS",autFilters);

        Map map = simpleJdbcCall.execute(in);

        return (List) map.get("results");
    }

    @Override
    public List<ReporteDetallado> reporteDetallado(String fechaPrev, String fechaNext,String autFilters ,String adqFilters, String canalFilters) throws DataAccessException{
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("USP_REPORTE_DETALLADO")
                .returningResultSet("results", new ReporteDetalladoMapper());

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("IN_FECHA_PREV", fechaPrev)
                .addValue("IN_FECHA_NEXT", fechaNext)
                .addValue("IN_AUT_FILTERS", autFilters)
                .addValue("IN_ADQ_FILTERS",adqFilters)
                .addValue("IN_CANAL_FILTERS",canalFilters);

        Map map = simpleJdbcCall.execute(in);

        return (List) map.get("results");
    }
}
