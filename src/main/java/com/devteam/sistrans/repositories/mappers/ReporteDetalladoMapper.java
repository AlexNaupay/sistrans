package com.devteam.sistrans.repositories.mappers;


import com.devteam.sistrans.models.ReporteDetallado;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteDetalladoMapper implements RowMapper<ReporteDetallado> {

    @Override
    public ReporteDetallado mapRow(ResultSet resultSet, int i) throws SQLException {
        ReporteDetallado reporteDetallado = new ReporteDetallado();
        reporteDetallado.setFecha(resultSet.getString("FECHA_TRANSACCION"));
        reporteDetallado.setHora(resultSet.getString("HORA_TRANSACCION"));
        reporteDetallado.setTarjeta(resultSet.getString("NUMERO_TARJETA"));
        reporteDetallado.setAutorizador(resultSet.getString("AUTORIZADOR"));
        reporteDetallado.setAdquiriente(resultSet.getString("ADQUIRIENTE"));
        reporteDetallado.setCanal(resultSet.getString("CANAL"));
        reporteDetallado.setMonto(resultSet.getString("MONTO"));

        return  reporteDetallado;
    }
}
