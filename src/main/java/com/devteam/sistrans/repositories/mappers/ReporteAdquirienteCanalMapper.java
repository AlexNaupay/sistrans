package com.devteam.sistrans.repositories.mappers;


import com.devteam.sistrans.models.ReporteAdquirienteCanal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteAdquirienteCanalMapper implements RowMapper<ReporteAdquirienteCanal> {

    @Override
    public ReporteAdquirienteCanal mapRow(ResultSet resultSet, int i) throws SQLException {
        ReporteAdquirienteCanal reporteAdquirienteCanal = new ReporteAdquirienteCanal();

        reporteAdquirienteCanal.setFecha(resultSet.getString("FECHA_TRANSACCION"));
        reporteAdquirienteCanal.setAdquiriente(resultSet.getString("ADQUIRIENTE"));
        reporteAdquirienteCanal.setCanal(resultSet.getString("CANAL"));
        reporteAdquirienteCanal.setNumeroTx(resultSet.getString("NUMERO_TRANSACCIONES"));
        reporteAdquirienteCanal.setMonto(resultSet.getString("MONTO"));
        return  reporteAdquirienteCanal;
    }
}
