package com.devteam.sistrans.repositories.mappers;


import com.devteam.sistrans.models.ReporteAutorizador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteAutorizadorMapper implements RowMapper<ReporteAutorizador> {

    @Override
    public ReporteAutorizador mapRow(ResultSet resultSet, int i) throws SQLException {
        ReporteAutorizador reporteAutorizador = new ReporteAutorizador();
        reporteAutorizador.setFecha(resultSet.getString("FECHA_TRANSACCION"));
        reporteAutorizador.setAutorizador(resultSet.getString("AUTORIZADOR"));
        reporteAutorizador.setNumeroTx(resultSet.getString("NUMERO_TRANSACCIONES"));
        reporteAutorizador.setMonto(resultSet.getString("SUMA_MONTO"));
        return  reporteAutorizador;
    }
}
